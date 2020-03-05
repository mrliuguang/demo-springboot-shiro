package com.hnsic.demospringbootshiro.mapper;

import com.hnsic.demospringbootshiro.bean.user;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * ClassName userMapper
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/10  14:56
 * Version   1.0
 **/
@Repository
public interface userMapper {
    @Select("select * from user where name=#{name}")
    public user selectByUsername(String name);

    @Select("select count(1) from user")
    public Integer findTotalNum();

    @Select("select * from user")
    public List<user> findAll();

    //根据用户查询此用户所有角色
    @Select("select r.`name` from user u LEFT JOIN shiro_user_role u_r on u.id=u_r.user_id\n" +
            "                            LEFT JOIN shiro_role r ON u_r.role_id=r.id\n" +
            " where u.`name`=#{name}")
    Set<String> selectRnamesByUserName(String name);

}
