package com.taylor.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Menu {

    private String id;

    private String accountId;

    private String parentId;

    private String parentName;

    private String name;

    private String type;

    private String key;

    private String url;

    private Integer sort;

    private String createdBy;

    private Timestamp createdTime;
}