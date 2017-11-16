package com.camelot.im.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class ChatLogVo implements Serializable {

  private static final long serialVersionUID = 1598650605457953626L;

  private String username;
  private Long id;
  private String avatar;
  private Long timestamp;
  private String content;
}
