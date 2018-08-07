package com.taylor.repository;

import com.taylor.entity.TestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


/**
 * @author Taylor
 */
public interface TestRepository extends MongoRepository<TestEntity, String> {
    /**
     * @param id
     * @return
     * @Desc 查询单个实体
     */
    Optional<TestEntity> findOneById(String id);
}
