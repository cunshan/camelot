package com.camelot.im.service;

import com.camelot.im.vo.ImResponse;

public interface ConfigService {

  /**.
   * 获取im初始化参数
   * @param loginAccount 登录账户
   */
  ImResponse init(String loginAccount);
}
