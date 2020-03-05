package com.hnsic.demospringbootshiro.service;

import com.hnsic.demospringbootshiro.bean.ShiroPermission;

import java.util.List;

public interface shiroService {
    public List<ShiroPermission> LoadPermission(int num);
}
