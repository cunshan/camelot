package com.camelot.im.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.StompBrokerRelayRegistration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableConfigurationProperties(StompRelayProperties.class)
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

  @Autowired
  private StompRelayProperties stompRelayProperties;

  @Override
  public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
    stompEndpointRegistry.addEndpoint("/redbean-im").withSockJS();
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    StompBrokerRelayRegistration relayRegistration = registry
        .enableStompBrokerRelay("/exchange", "/queue", "/amq/queue", "/topic", "/temp-queue");
    relayRegistration.setRelayHost(stompRelayProperties.getRelayHost());
    relayRegistration.setRelayPort(stompRelayProperties.getRelayPort());
    relayRegistration.setSystemLogin(stompRelayProperties.getSystemLogin());
    relayRegistration.setSystemPasscode(stompRelayProperties.getSystemPasscode());
    relayRegistration.setClientLogin(stompRelayProperties.getClientLogin());
    relayRegistration.setClientPasscode(stompRelayProperties.getClientPasscode());
    relayRegistration.setSystemHeartbeatReceiveInterval(1000);
    relayRegistration.setSystemHeartbeatSendInterval(1000);
    registry.setApplicationDestinationPrefixes("/im-ws");
  }
}
