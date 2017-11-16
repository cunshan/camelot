package com.camelot.im.vo;

import java.util.List;
import lombok.Data;

/**.
 * 针对layui的初始化参数
 */
@Data
public class InitData {

  private UserVo mine;//我的信息  登录人的信息
  private List<UserGroupVo> friend;//好友列表
  private List<ChatGroupVo> group;//群组列表

}
