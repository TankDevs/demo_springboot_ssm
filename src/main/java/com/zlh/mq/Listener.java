package com.zlh.mq;

/**
 *  消息监听器
 * @author Sam
 *
 */
public interface Listener {

    String onMessage(String message);
    
}
