package com.hnsic.demospringbootshiro.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName Resources
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/13  17:55
 * Version   1.0
 **/
@Data
public class Resources implements Serializable {
    private Integer id;
    private String description;
    private String name;

}
