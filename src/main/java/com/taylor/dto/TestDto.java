package com.taylor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "test")
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {
    @Id
    private Integer id;

    private String name;
}
