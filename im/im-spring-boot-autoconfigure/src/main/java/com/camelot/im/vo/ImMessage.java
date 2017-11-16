package com.camelot.im.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ImMessage implements Serializable {

  private static final long serialVersionUID = -3002442549290797186L;

  private String id;//消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
  private String type;//聊天类型，一般分friend和group两种，group即群聊
  private String content;//聊天内容


  //---用户消息开始---
  private String username;//消息来源用户名
  private String avatar = "/img/logo.jpg";//头像
  private int cid = 0;
  private boolean mine = false;//是否我发送的消息，如果为true，则会显示在右方
  private String fromId;//消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
  private Long timestamp = new Date().getTime();
  private String toUserId;//发送给的USER的ID
  //---用户消息结束---


  //---系统消息开始---
  private boolean system = false;//系统消息
  //---系统消息结束---
}
