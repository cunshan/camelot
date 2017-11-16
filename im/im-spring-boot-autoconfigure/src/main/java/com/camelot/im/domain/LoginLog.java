package com.camelot.im.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class LoginLog extends BaseDomain implements Serializable {

  private static final long serialVersionUID = -9093960388170466404L;

  private Long userId;//用户ID
  private String loginAccount;//用户登录账户
  private String loginIp;//登录IP

}
