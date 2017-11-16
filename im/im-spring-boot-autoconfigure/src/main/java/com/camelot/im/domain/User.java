package com.camelot.im.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User extends BaseDomain implements Serializable {

  private static final long serialVersionUID = -6571188111298793009L;

  private String loginAccount;//登陆账号

  private String password;//密码

  private String name;//名称

  private String avatar = "/img/logo.jpg";//头像

  public User() {

  }

  public User(String loginAccount, String password) {
    this.loginAccount = loginAccount;
    this.password = password;
  }

}
