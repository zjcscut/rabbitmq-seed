package org.throwable.rabbitmq.ch2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/1/25 23:20
 */
public class MultiBinding {

	private static final String QUEUE = "multi-binding";
	private static final String EXCHANGE = "multi-binding-exchange";
	private static final String KEY_1 = "key1";
	private static final String KEY_2 = "key2";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE, true, false, false, null);
		channel.exchangeDeclare(EXCHANGE, "direct", true, false, null);
		channel.queueBind(QUEUE, EXCHANGE, KEY_1, null);
		channel.queueBind(QUEUE, EXCHANGE, KEY_2, null);
		channel.close();
		connection.close();
	}
}
