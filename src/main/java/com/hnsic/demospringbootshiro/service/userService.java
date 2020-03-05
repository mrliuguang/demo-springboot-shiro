package com.hnsic.demospringbootshiro.service;

import com.hnsic.demospringbootshiro.bean.user;

import java.util.List;
import java.util.Set;

public interface userService {
    public user selectUser(String name);

    public Integer findTotalNum();

    public List<user> findAll();

    Set<String> selectRnamesByUserName(String username);
}
