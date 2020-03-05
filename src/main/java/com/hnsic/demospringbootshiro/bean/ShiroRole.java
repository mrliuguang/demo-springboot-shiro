package com.hnsic.demospringbootshiro.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShiroRole implements Serializable {
    private Long id;
    private String description;
    private String name;
    private Integer status;

}