package com.taylor.repository;

import com.taylor.entity.TestEntity;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.mongodb.core.query.NearQuery;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the TestEntity entity.
 * @author Taylor
 */
public interface TestRepositoryCustom extends RepositoryCustom<TestEntity> {


    /**
     * @param id
     * @param actor
     * @return
     */
    TestEntity addActor(String id, TestEntity actor);

    /**
     * @param id
     * @param actor
     * @param limit
     * @return
     */
    Boolean addActor(String id, TestEntity actor, Integer limit);

    TestEntity removeActor(String id, String actorId);

    TestEntity addItem(String id, TestEntity item);

    TestEntity removeItem(String id, String itemId);

    TestEntity updateItem(String id, String itemId, TestEntity dto);

    TestEntity batchAddItem(String id, List<TestEntity> list);

    TestEntity cleanItems(String id);

    Optional<GeoResult<TestEntity>> findOne(NearQuery query);

    boolean existsByIdAndActorId(String id, String actorId);

    Boolean exitsActiveByOwnerAndParentId(String ownerId, String parentId);

    List<TestEntity> findByIds(List<String> ids);
}
