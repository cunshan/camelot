package com.camelot.im.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "websocket.stomp")
public class StompRelayProperties {

  private String relayHost = "127.0.0.1";//代理主机地址
  private Integer relayPort = 61613;//代理主机端口
  private String clientLogin = "guest";
  private String clientPasscode = "guest";
  private String systemLogin = "guest";
  private String systemPasscode = "guest";
  private Long systemHeartbeatSendInterval;
  private Long systemHeartbeatReceiveInterval;
  private String virtualHost;
  private Boolean autoStartUp;
  private String userDestinationBroadcast;

}
