package com.hnsic.demospringbootshiro.mapper;

import com.hnsic.demospringbootshiro.bean.ShiroPermission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiroPermissionMapper {
    //   @Select("")
//    public List<ShiroPermission> PermissionOfRole(String RoleName);
    /*where `status`=1 ORDER BY sortnum asc limit #{num}*/
    @Select("select * from shiro_permission ")
    public List<ShiroPermission> PermissionAll(int num);

}