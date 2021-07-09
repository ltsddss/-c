package com.zlw.config;

import com.zlw.pojo.person;
import com.zlw.server.PersonServerImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private PersonServerImpl Personserver;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        每次需要授权时都会启用该方法
        System.out.println("执行了授权操作");

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

//        拿到当前对象
        Subject subject= SecurityUtils.getSubject();

//        拿到User的对象
        person currentUser=(person) subject.getPrincipal();

//        设置当前对象的权限
        info.addStringPermission(currentUser.getPower());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证操作");

        UsernamePasswordToken userToken=(UsernamePasswordToken) authenticationToken;

        person sysUser=Personserver.queryUser(userToken.getUsername());

        if(sysUser==null){
            return null;//自动抛出异常，用户名不存在
        }

        Subject currentsubject=SecurityUtils.getSubject();

        Session session=currentsubject.getSession();

        session.setAttribute("LoginUser",sysUser);

        return new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),"");
    }
}
