package com.zlh.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  消息服务端
 * @author Sam
 */
public class MessageServer implements MessageListener {
    
    /**
     * 日志句柄
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * ActiveMQ连接地址
     */
    private String url = "failover:(tcp://47.105.36.94:61617)";
    
    /**
     * 消息队列名
     */
    private String queueName = "test.gwi.queue";
    
    /**
     * 用户名
     */
    private String userName = "admin";
    
    /**
     * 密码
     */
    private String password = "admin";
    
    /**
     * 监听器
     */
    private Listener listener;
    
    /**
     * MQ session
     */
    private Session session;
    
    /**
     * 自定义构造方法
     */
    public MessageServer(Listener listener) {
        this.listener = listener;
        init();
    }
    
    /**
     *  自定义构造方法
     * @param url       mq链接
     * @param userName  用户名
     * @param password  密码
     */
    public MessageServer(String url, String userName, String password, Listener listener) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.listener = listener;
        init();
    }
    
    private void init() {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, url);
            
            Connection connection = connectionFactory.createConnection();
            connection.start();
            
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            Destination destination = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(this);
        }
        catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onMessage(Message message) {
        logger.info("ActiveMQ receives the message, the contents of the message is {}.", message);
        
        MessageProducer producer = null;
        
        try {
            if (message instanceof TextMessage) {
                String text = listener.onMessage(((TextMessage) message).getText());
                Message response = session.createTextMessage(text);
                Destination destination = message.getJMSReplyTo();
                if (null != destination) {
                    producer = session.createProducer(destination);
                    String correlationId = message.getJMSCorrelationID();
                    response.setJMSCorrelationID(correlationId);
                    producer.send(response);
                    logger.info("The ActiveMQ request responds successfully and the message content is {}.", response);
                }
            }
        }
        catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            if (null != producer) {
                try {
                    producer.close();
                }
                catch (JMSException e) {
                    logger.warn("Message producer failed to close!", e);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new MessageServer(new Listener() {
            @Override
            public String onMessage(String message) {
                System.out.println("收到消息:" + message);
                return "应答响应消息:" + message;
            }
        });
    }
}
