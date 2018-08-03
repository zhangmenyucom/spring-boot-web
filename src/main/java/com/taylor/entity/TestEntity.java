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
    @Id
    private Integer id;

    private String name;

}
