package com.hnsic.demospringbootshiro.web.shiro;

import com.hnsic.demospringbootshiro.bean.ShiroPermission;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName FilterChainDefinitionMapBuilder
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/10  19:57
 * Version   1.0
 **/
public class FilterChainDefinitionMapBuilder {
    @Autowired
    com.hnsic.demospringbootshiro.service.shiroService shiroService;

    public Map<String, String> builder() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        List<ShiroPermission> permissionList = shiroService.LoadPermission(9);
        for (ShiroPermission permission : permissionList) {
            filterChainDefinitionMap.put(permission.getKey(), permission.getValue());
            System.out.println(filterChainDefinitionMap.get(permission.getKey()));
        }
        String str = "users:add";
        filterChainDefinitionMap.put("/users/add", "authc,perms[" + str + "]");
        filterChainDefinitionMap.put("/**", "anon");
        return filterChainDefinitionMap;
    }
}
