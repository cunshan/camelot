package com.camelot.im.controller;

import com.camelot.im.config.shiro.ShiroUtils;
import com.camelot.im.service.ChatService;
import com.camelot.im.vo.ChatLogVo;
import com.camelot.im.vo.ImResponse;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/rest")
@Slf4j
public class ChatRestController {

  @Resource
  private ChatService chatService;

  @PostMapping("/chat-log")
  public ImResponse<List<ChatLogVo>> chatLog(String id, String type, HttpServletRequest request) {
    return chatService.queryChatLogs(Long.toString(ShiroUtils.getLoginUser().getId()), id, type);
  }

}
