package com.test.client.service;

public interface WebSocketService {

    /**
     * 群发
     *
     * @param message
     */
    void groupSending(String message);

    /**
     * 指定发送
     *
     * @param name
     * @param message
     */
    void appointSending(String name, String message);
}