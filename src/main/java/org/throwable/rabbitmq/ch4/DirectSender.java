package org.throwable.rabbitmq.ch4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/1/27 23:42
 */
public class DirectSender {

	private static final String QUEUE = "queue-direct";
	private static final String EXCHANGE = "exchange-direct";
	private static final String EXCHANGE_TYPE = "direct";
	private static final String KEY = "key-direct";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE, true, false, false, null);
		channel.exchangeDeclare(EXCHANGE, EXCHANGE_TYPE, true, false, null);
		channel.queueBind(QUEUE, EXCHANGE, KEY);
		channel.basicPublish(EXCHANGE, KEY, null, "direct-message".getBytes());
		channel.close();
		connection.close();
	}
}
