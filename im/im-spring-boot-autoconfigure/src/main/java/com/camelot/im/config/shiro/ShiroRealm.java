package com.camelot.im.config.shiro;

import com.camelot.im.domain.User;
import com.camelot.im.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;

  /**
   * 获取用户资源权限.
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    return null;
  }

  /**
   * 登录认证
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
    User userParam = new User(token.getUsername(), String.valueOf(token.getPassword()));
    User user = userService.login(userParam);
    //用户名或者密码错误
    if (user == null) {
      throw new ImLoginException();
    }
    return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
  }
}
