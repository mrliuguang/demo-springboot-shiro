package com.hnsic.demospringbootshiro.web.shiro;

import com.hnsic.demospringbootshiro.bean.user;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * ClassName hiroRealm
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/10  19:56
 * Version   1.0
 **/
public class hiroRealm extends AuthorizingRealm {
    @Autowired
    com.hnsic.demospringbootshiro.service.userService userService;

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //1 得到用户名信息
//        String username= (String) principalCollection.getPrimaryPrincipal();
//        //2 查询数据库，根据用户名称，查询该用户拥有哪些角色
//        Set<String> roles=  userService.selectRnamesByUserName(username);
////        for(String rname:roles)
////        System.out.println(rname);
////        //3 管理员拥有所有的角色（特殊权限）
////        if(roles.contains("admin")){
////            roles=userService.selectRolesAllRnames();
////        }
//        AuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = userService.selectRnamesByUserName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        simpleAuthorizationInfo.addStringPermission("users:add");
        AuthorizationInfo info = simpleAuthorizationInfo;
        return info;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        /**
         *   org.apache.shiro.authc.UsernamePasswordToken - admin, rememberMe=true
         *   保存了username，password，rememberMe，host（）
         */
        String username = upToken.getUsername();
        user user = userService.selectUser(username);
        //3如果用户名不存在  UnknownAccountException
        if (user == null) {
            throw new UnknownAccountException("用户名不存在");
        }
        //4如果用户的状态 锁定  LockedAccountException   status=0  1正常
        if (user.getStatus() == 0) {
            throw new LockedAccountException("该帐户已被锁定");
        }
        Object principal = username;
        Object credentials = user.getPwd();//数据库查询出的密码
        ByteSource credentialsSalt = ByteSource.Util.bytes(username); //加盐主体
//        AuthenticationInfo info=new SimpleAuthenticationInfo(principal,credentials,super.getName());
        AuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, super.getName());
        return info;
    }

    public static void main(String[] args) {
        //注册时存入的数据
        //对数据库的密码按照相应规则加密
        /**
         * hashAlgorithmName 加密码名称
         * credentials 要加密的密码
         * hashIterations 加密的次数
         */
        Object credentials = "156165";
        String hashAlgorithmName = "MD5";
        String username = "lg";  //用户名
        Object salt = ByteSource.Util.bytes(username);
        System.out.println("salt :" + salt);
        int hashIterations = 1902;  //加密次数
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }
}
