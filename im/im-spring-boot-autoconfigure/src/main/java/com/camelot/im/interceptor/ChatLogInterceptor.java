package com.camelot.im.interceptor;

import com.alibaba.fastjson.JSON;
import com.camelot.im.service.LogService;
import com.camelot.im.vo.ImMessage;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ChatLogInterceptor {

  private static final String LOG_STR = "ChatLogInterceptor: ";

  @Resource
  private LogService chatLogService;


  /**.
   * 暂时拦截所有调用ChatService的方法，目前只记录私聊的日志记录//TODO 后面优化加入区分群聊和私聊记录
   */
  @Pointcut("execution(* com.readbean.im.service.ChatService.*(*)) && args(message)")
  public void chatLog(ImMessage message) {

  }


  @Before("chatLog(message)")
  public void beforeSend(JoinPoint joinPoint, ImMessage message) {
    log.info(LOG_STR + "beforeSend...");
    log.info(JSON.toJSONString(message));
  }

  @After(value = "chatLog(message)")
  public void after(JoinPoint joinPoint, ImMessage message) {
    log.info(LOG_STR + "after...");
    log.info(JSON.toJSONString(message));
    chatLogService.addChatLog(message);
  }

  @AfterThrowing(value = "chatLog(message)", throwing = "exception")
  public void afterThrowingException(JoinPoint joinPoint, Exception exception, ImMessage message) {
    log.info(LOG_STR + "afterThrowingException...");
    log.info(JSON.toJSONString(message));
  }


  @Around("chatLog(message)")
  public Object around(ProceedingJoinPoint joinPoint, ImMessage message) throws Throwable {
    log.info(LOG_STR + "around...before...");
    log.info(JSON.toJSONString(message));
    Object obj = joinPoint.proceed();
    log.info(LOG_STR + "around...after...");
    log.info(JSON.toJSONString(message));
    return obj;

  }

}
