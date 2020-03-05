package com.hnsic.demospringbootshiro.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName user
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/10  14:53
 * Version   1.0
 **/
@Data
public class user implements Serializable {
    private Integer id;
    private String name;
    private String pwd;
    private Integer role_id;
    private Integer status;
}
