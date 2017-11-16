package com.camelot.im.controller;

import com.camelot.im.config.shiro.ShiroUtils;
import com.camelot.im.domain.LoginLog;
import com.camelot.im.domain.User;
import com.camelot.im.service.LogService;
import com.camelot.im.service.UserService;
import com.camelot.im.util.WebUtils;
import com.camelot.im.vo.ImResponse;
import com.camelot.im.vo.UserVo;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@Slf4j
public class LoginRestController {


  @Autowired
  private UserService userService;

  @Autowired
  private LogService logService;

  @PostMapping("/login")
  public ImResponse login(String loginAccount, String password, HttpServletRequest request) {
    UsernamePasswordToken token = new UsernamePasswordToken(loginAccount, password);
    SecurityUtils.getSubject().login(token);
    ImResponse<UserVo> imResponse = new ImResponse<>();
    imResponse.setMsg("登录成功");
    User loginUser = ShiroUtils.getLoginUser();
    UserVo userVo = new UserVo();
    userVo.setId(Long.toString(loginUser.getId()));
    userVo.setLoginAccount(loginAccount);
    imResponse.setData(userVo);
    //保存登录日志
    saveLoginLog(request, loginUser);
    return imResponse;
  }


  /**
   * . 保存登录日志
   */
  private void saveLoginLog(HttpServletRequest request, User user) {
    LoginLog log = new LoginLog();
    log.setUserId(user.getId());
    log.setLoginIp(WebUtils.getRemoteHost(request));
    log.setLoginAccount(user.getLoginAccount());
    logService.addLoginLog(log);
  }


}
