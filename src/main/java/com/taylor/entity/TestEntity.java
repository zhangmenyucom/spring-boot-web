package com.taylor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "test")
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {

    public TestEntity(String name) {
        this.name = name;
    }

    @Id
    private Integer id;

    private String name;

}
