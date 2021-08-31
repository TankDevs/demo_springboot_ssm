package com.zlh.mq;

import java.util.UUID;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  消息客户端
 * @author Sam
 */
public class MessageClient {

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
     * 消息接收超时时间
     */
    private long timeout = 15000L;

    /**
     * MQ session
     */
    private Session session;

    /**
     * MQ producer
     */
    private MessageProducer producer;

    private Connection connection;

    /**
     * 自定义构造方法
     */
    public MessageClient() {
        init();
    }

    /**
     * 自定义构造方法
     *
     * @param url      mq链接
     * @param userName 用户名
     * @param password 密码
     * @param timeout  超时时间,单位毫秒
     */
    public MessageClient(String url, String userName, String password, long timeout) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.timeout = timeout;
        init();
    }

    private void init() {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, url);
            connection = connectionFactory.createConnection();

            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);

            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送和接收消息
     *
     * @param text 请求内容
     * @return 消息响应
     */
    public synchronized String sendAndReceive(String text) {
        MessageConsumer consumer = null;

        try {
            TextMessage message = session.createTextMessage(text);
            logger.info("Create a message of type text, message is {}.", message);

            String correlationId = createUUID();
            message.setJMSCorrelationID(correlationId);

            Destination destination = session.createQueue(queueName);
            logger.info("Create a temporary destination, destination is {}.", destination);
            message.setJMSReplyTo(destination);

            consumer = session.createConsumer(destination);
            logger.info("Create a consumer, consumer is {}.", consumer);

            producer.send(message);
            logger.info("ActiveMQ message sent successfully! The message is {}.", message);

            long startTime = System.currentTimeMillis();
            Message response = consumer.receive(timeout);
            long endTime = System.currentTimeMillis();

            logger.info("ActiveMQ message reception is complete, the message is {}.", response);

            long costTime = endTime - startTime;
            if (costTime >= timeout) {
                throw new JMSException(String.format(
                        "The MQ receive message has timed out and the timeout period is %s millisecond! Actually takes %s milliseconds",
                        timeout, costTime));
            }

            String result = response instanceof TextMessage ? ((TextMessage) message).getText() : String.valueOf(message);

            return result;
        } catch (JMSException e) {
            throw new RuntimeException("ActiveMQ message sent or received failed!", e);
        } finally {
            if (null != consumer) {
                try {
                    consumer.close();
                } catch (JMSException e) {
                    logger.warn("Message producer failed to close!", e);
                }
            }
        }
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

    private void close() {
        try {
            producer.close();
            session.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        MessageClient client = new MessageClient();
        for (int i = 0; i < 5; i++) {
            try {
                long start = System.currentTimeMillis();
                String message = client.sendAndReceive("阿尔解放军奥兰多解放啦额和埃及怕染发剂" + i);
                long cost = System.currentTimeMillis() - start;
                System.out.println("Cost time " + cost + "ms for:   " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        client.close();

       System.out.println("bye");
    }

}
