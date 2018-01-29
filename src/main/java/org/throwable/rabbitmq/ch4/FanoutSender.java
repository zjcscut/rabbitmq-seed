package org.throwable.rabbitmq.ch4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/1/29 16:53
 */
public class FanoutSender {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("exchange-fanout", "fanout", true, false, null);
		channel.queueDeclare("fanout-queue-one", true, false, false, null);
		channel.queueDeclare("fanout-queue-two", true, false, false, null);
		channel.queueDeclare("fanout-queue-three", true, false, false, null);
		channel.queueBind("fanout-queue-one", "exchange-fanout", "queue-one-key");
		channel.queueBind("fanout-queue-two", "exchange-fanout", "queue-two-key");
		channel.queueBind("fanout-queue-three", "exchange-fanout", "");

		channel.basicPublish("exchange-fanout", "doge-10086", null, "fanout-message".getBytes());

		channel.close();
		connection.close();
	}
}
