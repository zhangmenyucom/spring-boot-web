package com.taylor.dto;


import com.taylor.entity.TestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Taylor
 */
@Mapper(componentModel = "spring")
public interface TestMapper {
    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    TestEntity carToCarEntity(TestDto testDto);
}
