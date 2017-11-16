package com.camelot.im.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class ChatGroupVo implements Serializable {

  private static final long serialVersionUID = 6244897682345747887L;

  private String id;//群组名
  private String groupname;//群组名
  private String avatar = "/img/logo.jpg";//群组名
}
