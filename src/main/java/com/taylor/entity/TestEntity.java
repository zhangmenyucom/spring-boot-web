package com.taylor.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Taylor
 */
@Data
@Table(name = "test")
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {

    public TestEntity(String name) {
        this.name = name;
    }

    @Id
    @NotNull
    @ApiModelProperty( value= "id", position = 1)
    private Integer id;

    @NotNull
    @ApiModelProperty(value = "姓名", position = 2)
    private String name;

}
