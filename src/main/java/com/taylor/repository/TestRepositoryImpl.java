package com.taylor.repository;

import com.taylor.entity.TestEntity;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ${author} on 2018/8/7.
 * @author Taylor
 */
@Repository
public class TestRepositoryImpl extends AbstractRepositoryCustom<TestEntity> implements TestRepositoryCustom{

    @Override
    public Class<TestEntity> entityClass() {
        return null;
    }

    @Override
    public <G> GroupByResults<G> groupCount(Class<G> returnType, String... keys) {
        return null;
    }

    @Override
    public <G> GroupByResults<G> groupCount(Criteria criteria, Class<G> returnType, String... keys) {
        return null;
    }

    @Override
    public <G> GroupByResults<G> groupCount(Criteria criteria, String reduceFunction, Class<G> returnType, String... keys) {
        return null;
    }

    @Override
    public TestEntity addActor(String id, TestEntity actor) {
        return null;
    }

    @Override
    public Boolean addActor(String id, TestEntity actor, Integer limit) {
        return null;
    }

    @Override
    public TestEntity removeActor(String id, String actorId) {
        return null;
    }

    @Override
    public TestEntity addItem(String id, TestEntity item) {
        return null;
    }

    @Override
    public TestEntity removeItem(String id, String itemId) {
        return null;
    }

    @Override
    public TestEntity updateItem(String id, String itemId, TestEntity dto) {
        return null;
    }

    @Override
    public TestEntity batchAddItem(String id, List<TestEntity> list) {
        return null;
    }

    @Override
    public TestEntity cleanItems(String id) {
        return null;
    }

    @Override
    public Optional<GeoResult<TestEntity>> findOne(NearQuery query) {
        return null;
    }

    @Override
    public boolean existsByIdAndActorId(String id, String actorId) {
        return false;
    }

    @Override
    public Boolean exitsActiveByOwnerAndParentId(String ownerId, String parentId) {
        return null;
    }

    @Override
    public List<TestEntity> findByIds(List<String> ids) {
        return null;
    }
}
