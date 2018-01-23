package org.throwable.rabbitmq.ch1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/1/23 23:46
 */
public class Sender {

	private static final String QUEUE_NAME = "hello-world";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		for (int i = 0; i < 10; i++) {
			String message = "No." + (i + 1);
			TimeUnit.MILLISECONDS.sleep(1000);
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			System.out.println(String.format("[x] send content [%s] to queue [%s]", message, QUEUE_NAME));
		}
		channel.close();
		connection.close();
	}
}
