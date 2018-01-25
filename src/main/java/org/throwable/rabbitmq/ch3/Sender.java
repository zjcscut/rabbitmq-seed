package org.throwable.rabbitmq.ch3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/1/25 23:49
 */
public class Sender {

	private static final String QUEUE = "send";
	private static final String EXCHANGE = "send-exchange";
	private static final String KEY = "send-key";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE,true,false,false,null);
		channel.exchangeDeclare(EXCHANGE,"direct",false,false,null);
		channel.queueBind(QUEUE,EXCHANGE, KEY);
		channel.basicPublish(EXCHANGE,KEY,null,"Hello,I am sender!".getBytes("UTF-8"));
		channel.close();
		connection.close();
	}
}
