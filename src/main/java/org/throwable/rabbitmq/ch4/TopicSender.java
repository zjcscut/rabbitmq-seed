package org.throwable.rabbitmq.ch4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/1/29 16:36
 */
public class TopicSender {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("queue-one", true, false, false, null);
		channel.queueDeclare("queue-two", true, false, false, null);
		channel.exchangeDeclare("exchange-one", "direct", true, false, null);
		channel.exchangeDeclare("exchange-two", "topic", true, false, null);
		channel.queueBind("queue-one", "exchange-one", "key-one", null);
		channel.queueBind("queue-two", "exchange-two", "#.key-one.#", null);
		channel.basicPublish("exchange-one", "key-one", null, "message-one".getBytes());
		channel.basicPublish("exchange-two", "key-one", null, "message-key-one".getBytes());
		channel.basicPublish("exchange-two", "one.key-one", null, "message-one.key-one".getBytes());
		channel.basicPublish("exchange-two", "one.key-one.two", null, "message-one.one.key-one.two".getBytes());
		channel.close();
		connection.close();
	}
}
