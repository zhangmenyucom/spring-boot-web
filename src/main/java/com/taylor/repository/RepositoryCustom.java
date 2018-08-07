package com.taylor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RepositoryCustom<T> extends MetadataRepository<T> {
    Class<T> entityClass();

    /*-----------------------更新-----------------------*/

    boolean inc(String id, String attribute);

    boolean inc(String id, String attribute, int number);

    int inc(Query query, String attribute);

    int inc(Query query, String attribute, int number);

    boolean dec(String id, String attribute);

    boolean dec(String id, String attribute, int number);

    int dec(Query query, String attribute);

    int dec(Query query, String attribute, int number);

    boolean update(String id, String attribute, Object value);

    boolean update(String id, Map<String, Object> attributesMap);

    int update(Query query, String attribute, Object value);

    int update(Query query, Map<String, Object> attributesMap);

    /**
     * @param exclude the fields to exclude.
     */
    T update(T entity, String... exclude);

    T updateAndReturn(String id, String attribute, Object value);

    void batchInc(List<String> ids, String attribute);

    void batchDec(List<String> ids, String attribute);

    boolean batchUpdate(List<String> ids, String attribute, Object value);

    boolean batchUpdate(List<String> ids, Map<String, Object> attributesMap);

    boolean batchDelete(List<String> ids);

    /**
     * 新增或者更新实体,取决于查询条件是否能查询到记录
     * 如果存在,则更新;如果不存在就新增
     */
    T upsert(Query query, T entity);

    /*-----------------------查询-----------------------*/

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @return a Page of entities
     */

    List<T> find(Query query);

    Optional<T> findOne(Query query);

    Optional<T> findById(String id);

    long count(Query query);

    <G> GroupByResults<G> groupCount(Class<G> returnType, String... keys);

    <G> GroupByResults<G> groupCount(Criteria criteria, Class<G> returnType, String... keys);

    <G> GroupByResults<G> groupCount(Criteria criteria, String reduceFunction, Class<G> returnType, String... keys);

    /*-----------------------删除-----------------------*/

    void deleteById(String id);
}
