package com.taylor.repository;

import com.google.common.collect.ImmutableMap;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.taylor.data.common.FullUpdateIgnore;
import org.jooq.lambda.Seq;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mapping.PropertyHandler;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractRepositoryCustom<T> implements RepositoryCustom<T>, InitializingBean {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected MongoMappingContext mappingContext;

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    @Autowired(required = false)
    protected AuditorAware<?> auditorAware;

    @Nullable
    protected MongoPersistentEntity<?> persistentEntity;

    @Nullable
    protected MongoPersistentProperty idProperty;

    @Nullable
    protected MongoPersistentProperty lastModifiedByProperty;

    @Nullable
    protected MongoPersistentProperty lastModifiedDateProperty;

    protected List<String> fullUpdateIgnores = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        MongoPersistentEntity<?> persistentEntity = mappingContext.getPersistentEntity(entityClass());
        if (persistentEntity != null) {
            this.persistentEntity = persistentEntity;
            persistentEntity.doWithProperties((PropertyHandler<MongoPersistentProperty>) persistentProperty -> {
                if (persistentProperty.isAnnotationPresent(FullUpdateIgnore.class)) {
                    fullUpdateIgnores.add(persistentProperty.getFieldName());
                }
            });
            this.idProperty = persistentEntity.getIdProperty();
            this.lastModifiedByProperty = persistentEntity.getPersistentProperty(LastModifiedBy.class);
            this.lastModifiedDateProperty = persistentEntity.getPersistentProperty(LastModifiedDate.class);
        }
    }

    @Override
    public boolean dec(String id, String attribute) {
        return this.dec(id, attribute, 1);
    }

    @Override
    public boolean dec(String id, String attribute, int number) {
        return this.inc(id, attribute, -number);
    }

    @Override
    public int dec(Query query, String attribute) {
        return this.dec(query, attribute, 1);
    }

    @Override
    public int dec(Query query, String attribute, int number) {
        return this.inc(query, attribute, -number);
    }

    @Override
    public boolean inc(String id, String attribute) {
        return this.inc(id, attribute, 1);
    }

    @Override
    public boolean inc(String id, String attribute, int number) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return inc(query, attribute, number) == 1;
    }

    @Override
    public int inc(Query query, String attribute) {
        return inc(query, attribute, 1);
    }

    @Override
    public int inc(Query query, String attribute, int number) {
        Update update = markModified().inc(attribute, number);
        WriteResult result = mongoTemplate.updateFirst(query, update, entityClass());
        return result.getN();
    }

    @Override
    public boolean update(String id, String attribute, Object value) {
        return this.update(id, ImmutableMap.of(attribute, value));
    }

    @Override
    public boolean update(String id, Map<String, Object> attributesMap) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return this.update(query, attributesMap) == 1;
    }

    @Override
    public int update(Query query, String attribute, Object value) {
        return this.update(query, ImmutableMap.of(attribute, value));
    }

    @Override
    public int update(Query query, Map<String, Object> attributesMap) {
        Update update = _toUpdate(attributesMap);
        WriteResult result = mongoTemplate.updateMulti(query, update, entityClass());
        return result.getN();
    }

    @Override
    public T update(T entity, String... exclude) {
        if (persistentEntity == null || idProperty == null) {
            throw new MongoException(this.getClass() + " can not support full update!");
        }
        String collectionName = _determineCollectionName(entityClass());
        eventPublisher.publishEvent(new BeforeConvertEvent<>(entity, collectionName));

        String idField = idProperty.getFieldName();
        Object idValue = persistentEntity.getPropertyAccessor(entity).getProperty(idProperty);
        Query query = Query.query(Criteria.where(idField).is(idValue));

        BasicDBObject dbObject = new BasicDBObject();
        mongoTemplate.getConverter().write(entity, dbObject);
        Seq.of(exclude).concat(idField).concat(fullUpdateIgnores).distinct().forEach(dbObject::remove);
        Update update = new Update();
        dbObject.forEach(update::set);

        eventPublisher.publishEvent(new BeforeSaveEvent<>(entity, dbObject, collectionName));
        mongoTemplate.updateFirst(query, update, entityClass());

        eventPublisher.publishEvent(new AfterSaveEvent<>(entity, dbObject, collectionName));
        return entity;
    }

    @Override
    public T upsert(Query query, T entity) {
        String collectionName = _determineCollectionName(entityClass());
        eventPublisher.publishEvent(new BeforeConvertEvent<>(entity, collectionName));

        String idField = idProperty.getFieldName();
        BasicDBObject dbObject = new BasicDBObject();
        mongoTemplate.getConverter().write(entity, dbObject);
        dbObject.remove(idField);
        Update update = new Update();
        dbObject.forEach(update::set);

        eventPublisher.publishEvent(new BeforeSaveEvent<>(entity, dbObject, collectionName));
        mongoTemplate.upsert(query, update, entityClass());
        eventPublisher.publishEvent(new AfterSaveEvent<>(entity, dbObject, collectionName));
        return entity;
    }

    @Override
    public T updateAndReturn(String id, String attribute, Object value) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = _toUpdate(ImmutableMap.of(attribute, value));
        return updateAndReturn(query, update);
    }

    @Override
    public void batchInc(List<String> ids, String attribute) {
        Query query = Query.query(Criteria.where("_id").in(ids));
        Update update = markModified().inc(attribute, 1);
        mongoTemplate.updateMulti(query, update, entityClass());
    }

    @Override
    public void batchDec(List<String> ids, String attribute) {
        Query query = Query.query(Criteria.where("_id").in(ids));
        Update update = markModified().inc(attribute, -1);
        mongoTemplate.updateMulti(query, update, entityClass());
    }

    @Override
    public boolean batchUpdate(List<String> ids, String attribute, Object value) {
        Query query = Query.query(Criteria.where("_id").in(ids));
        Update update = markModified().set(attribute, value);
        WriteResult result = mongoTemplate.updateMulti(query, update, entityClass());
        return result.getN() == ids.size();
    }

    @Override
    public boolean batchUpdate(List<String> ids, Map<String, Object> attributesMap) {
        Query query = Query.query(Criteria.where("_id").in(ids));
        Update update = markModified();
        for (Map.Entry<String, Object> entry : attributesMap.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        WriteResult result = mongoTemplate.updateMulti(query, update, entityClass());
        return result.getN() == ids.size();
    }

    @Override
    public boolean batchDelete(List<String> ids) {
        Query query = Query.query(Criteria.where("_id").in(ids));
        WriteResult result = mongoTemplate.remove(query, entityClass());
        return result.wasAcknowledged();
    }

    @Override
    public Optional<T> findOne(Query query) {
        T t = mongoTemplate.findOne(query, entityClass());
        return Optional.ofNullable(t);
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, entityClass());
    }

    @Override
    public List<T> find(Query query) {
        return mongoTemplate.find(query, entityClass());
    }

    @Override
    public Optional<T> findById(String id) {
        Assert.notNull(id, "The given id must not be null!");
        return Optional.ofNullable(mongoTemplate.findById(id, entityClass()));
    }

    @Override
    public void deleteById(String id) {
        Assert.notNull(id, "The given id must not be null!");
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), entityClass());
    }

    @Override
    public T addMetadata(String id, String key, String value) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = markModified().set(FIELD_METADATA + "." + key, value);
        return updateAndReturn(query, update);
    }

    @Override
    public T removeMetadata(String id, String key) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = markModified().unset(FIELD_METADATA + "." + key);
        return updateAndReturn(query, update);
    }

    /*-------------------------------模板方法-------------------------------*/

    protected T updateAndReturn(Query query, Update update) {
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), entityClass());
    }

    protected Update markModified() {
        Update update = new Update();
        markModified(update);
        return update;
    }

    protected void markModified(Update update) {
        if (auditorAware != null && lastModifiedByProperty != null) {
            update.set(lastModifiedByProperty.getFieldName(), auditorAware.getCurrentAuditor());
        }
        if (lastModifiedDateProperty != null) {
            update.set(lastModifiedDateProperty.getFieldName(), Instant.now());
        }
    }

    /*-------------------------------私有方法-------------------------------*/

    private Update _toUpdate(Map<String, Object> attributesMap) {
        Update update = markModified();
        for (Map.Entry<String, Object> entry : attributesMap.entrySet()) {
            String attribute = entry.getKey();
            Object value = entry.getValue();
            _set(update, attribute, value);
        }
        return update;
    }

    private void _set(Update update, String attribute, Object value) {
        if ("$inc".equals(value)) {
            update.inc(attribute, 1);
        } else if ("$dec".equals(value)) {
            update.inc(attribute, -1);
        } else {
            update.set(attribute, value);
        }
    }

    private String _determineCollectionName(Class<?> entityClass) {
        if (entityClass == null) {
            throw new InvalidDataAccessApiUsageException("No class parameter provided, entity collection can't be determined!");
        }

        MongoPersistentEntity<?> entity = mappingContext.getPersistentEntity(entityClass);
        if (entity == null) {
            throw new InvalidDataAccessApiUsageException("No Persistent Entity information found for the class " + entityClass.getName());
        }
        return entity.getCollection();
    }
}
