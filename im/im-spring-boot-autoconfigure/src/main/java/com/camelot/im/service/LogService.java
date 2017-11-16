package com.camelot.im.service;

import com.camelot.im.domain.LoginLog;
import com.camelot.im.vo.ImMessage;

public interface LogService {

  /**.
   * 添加聊天日志
   */
  void addChatLog(ImMessage message);

  /**.
   * 添加登录日志
   */
  void addLoginLog(LoginLog log);
}
