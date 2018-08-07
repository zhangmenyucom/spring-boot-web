package com.taylor.repository;

import com.taylor.entity.TestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


/**
 * @author Taylor
 */
public interface TestRepository extends MongoRepository<TestEntity, String> {
    /**@Desc 查询单个
     * @param id
     * @return
     */
    Optional<TestEntity> findOneById(String id);
}
