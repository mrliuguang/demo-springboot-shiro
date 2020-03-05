package com.hnsic.demospringbootshiro.service.impl;

import com.hnsic.demospringbootshiro.bean.user;
import com.hnsic.demospringbootshiro.mapper.userMapper;
import com.hnsic.demospringbootshiro.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * ClassName userServiceImpl
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/10  15:03
 * Version   1.0
 **/
@Service
public class userServiceImpl implements userService {
    @Autowired
    userMapper userMapper;

    @Override
    public user selectUser(String name) {
        return userMapper.selectByUsername(name);
    }

    @Override
    public Integer findTotalNum() {
        return userMapper.findTotalNum();
    }

    @Override
    public Set<String> selectRnamesByUserName(String username) {
        return userMapper.selectRnamesByUserName(username);
    }

    @Override
    public List<user> findAll() {
        return userMapper.findAll();
    }

}
