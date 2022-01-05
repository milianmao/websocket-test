package com.test.config;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.net.URI;
@Slf4j
@Configuration
public class WebSocketConfig {
 
    /**
     * ServerEndpointExporter 作用
     *
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    @Bean
    public WebSocketClient webSocketClient() {
        try {
            WebSocketClient webSocketClient=null;
            WebSocketClient finalWebSocketClient = webSocketClient;
            webSocketClient = new WebSocketClient(new URI("ws://localhost:8080/websocket/test"),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    log.info("开始连接");
                }

                @Override
                public void onMessage(String message) {


                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    try {
                        finalWebSocketClient.connect();
                    }catch (Exception e){
                        e.printStackTrace();
                        log.info("连接失败");
                    }
                }

                @Override
                public void onError(Exception ex) {

                }
            };
            webSocketClient.connect();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
