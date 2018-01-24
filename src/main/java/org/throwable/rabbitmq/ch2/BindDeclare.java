package org.throwable.rabbitmq.ch2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/1/25 0:09
 */
public class BindDeclare {

	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueBind("queue-declare","exchange-declare","key", null);
		channel.close();
		connection.close();
	}
}
