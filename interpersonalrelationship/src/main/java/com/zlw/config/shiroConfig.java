package com.zlw.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class shiroConfig {

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiro(@Qualifier("securityManage") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String,String> filterMap=new HashMap<>();

        /*
        shiro的一些认证规则
        anon表示所有人都可以访问
        authc表示只有认证过的用户才可以访问
        perms[vip]表示只有拥有vip权限的用户才可以访问
         */

        filterMap.put("/","anon");
        filterMap.put("/Login","anon");
        filterMap.put("/index.html","anon");
        filterMap.put("/toqueryRelation","authc");
        filterMap.put("/toAdd","authc");
        filterMap.put("/addRelation","authc");
        filterMap.put("/Register","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/toLogin");//设置登录的url


        return shiroFilterFactoryBean;
    }

    @Bean("securityManage")
    public DefaultWebSecurityManager getsecurityManage(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager security=new DefaultWebSecurityManager();

        security.setRealm(userRealm);

        return security;
    }

//    创建UserRealm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

//    整合thymeleaf引擎
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
