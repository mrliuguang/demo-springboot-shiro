package com.hnsic.demospringbootshiro.service.impl;

import com.hnsic.demospringbootshiro.bean.ShiroPermission;
import com.hnsic.demospringbootshiro.mapper.ShiroPermissionMapper;
import com.hnsic.demospringbootshiro.service.shiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName shiroServiceImpl
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/14  15:43
 * Version   1.0
 **/
@Service
public class shiroServiceImpl implements shiroService {
    @Autowired
    ShiroPermissionMapper shiroPermissionMapper;

    @Override
    public List<ShiroPermission> LoadPermission(int num) {
        return shiroPermissionMapper.PermissionAll(num);
    }
}
