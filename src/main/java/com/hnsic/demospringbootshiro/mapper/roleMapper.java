package com.hnsic.demospringbootshiro.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface roleMapper {
    //查询所有可用角色
    @Select(" select rname from roles where `status`=1 ")
    Set<String> selectRolesAllRnames();
}
