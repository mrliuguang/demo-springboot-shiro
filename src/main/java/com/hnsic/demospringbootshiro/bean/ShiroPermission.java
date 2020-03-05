package com.hnsic.demospringbootshiro.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShiroPermission implements Serializable {
    private Long id;
    private String description;
    private String value;
    private String key;
    private Long parentId;
    private Integer sortnum;
    private Integer status;

}