package org.throwable.rabbitmq.ch2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/1/24 23:55
 */
public class QueueDeclare {

	private static final String QUEUE = "queue-declare";

	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE,true,false,false,null);
		channel.close();
		connection.close();
	}
}
