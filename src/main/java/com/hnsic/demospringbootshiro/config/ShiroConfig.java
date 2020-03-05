package com.hnsic.demospringbootshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.hnsic.demospringbootshiro.web.shiro.FilterChainDefinitionMapBuilder;
import com.hnsic.demospringbootshiro.web.shiro.hiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * ClassName ShiroConfig
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/14  19:45
 * Version   1.0
 **/
@Configuration
@ConditionalOnProperty(name = "shiro.web.enabled", matchIfMissing = false)
/***
 * 以开启强制匹配配置文件中的shiro.web.enabled的值，
 * matchIfMissing设置为true，则可有可无
 * shiroconfig中主要bean为:
 * ShiroFilterFactoryBean 主要管理shiro的SecrityManager、过滤器链、登录页面、无权页面、成功页面,
 * SecurityManager        shiro核心管理，由SecurityManager操作其他对象,包括session、缓存、自定义的realm
 * AuthorizingRealm              自定义的Realm去操作认证和授权
 */
public class ShiroConfig {
    @ConfigurationProperties(prefix = "shiro")
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier(value = "SecurityManager") DefaultWebSecurityManager securityManager,
            @Qualifier(value = "filterChainDefinitionMap") Map<String, String> filterChainDefinitionMap) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "SecurityManager")//SecurityManager管理realm，ehCahce
    public DefaultWebSecurityManager securityManager(@Qualifier(value = "jdbcRealm")
                                                             AuthorizingRealm jdbcRealm, @Qualifier(value = "ehCacheManager") EhCacheManager ehCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jdbcRealm);
        securityManager.setRememberMeManager(cookieRememberMeManager());
//        securityManager.setCacheManager(ehCacheManager);
        return securityManager;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return ehCacheManager;
    }

    @Bean(name = "jdbcRealm")//设置自定义realm及加密方式
    public AuthorizingRealm jdbcRealm(@Qualifier(value = "hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
        AuthorizingRealm realm = new hiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }

    @ConfigurationProperties(prefix = "shiro.credentialsmatcher")
    @Bean//使用加密方法设置
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        return hashedCredentialsMatcher;
    }

    @Bean
    public FilterChainDefinitionMapBuilder filterChainDefinitionMapBuilder() {
        return new FilterChainDefinitionMapBuilder();
    }

    @Bean
    public Map<String, String> filterChainDefinitionMap(@Qualifier(value = "filterChainDefinitionMapBuilder") FilterChainDefinitionMapBuilder builder) {
        return builder.builder();
    }

    @Bean//设置cookie防止每次开启系统密码不对
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        Cookie cookie = new SimpleCookie("myCookie");
        cookie.setMaxAge(31536000);
        cookieRememberMeManager.setCookie(cookie);
        return cookieRememberMeManager;
    }

    @Bean(name = "shiroDialect")//启动shiro方言
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
