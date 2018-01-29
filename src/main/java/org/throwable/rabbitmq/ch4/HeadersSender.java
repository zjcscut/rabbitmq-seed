package org.throwable.rabbitmq.ch4;

import com.rabbitmq.client.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/1/29 17:49
 */
public class HeadersSender {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("queue-headers", true, false, false, null);
		channel.exchangeDeclare("exchange-headers", "headers", true, false, null);
		Map<String, Object> headers = new HashMap<>(3);
		headers.put("x-match", "any");
		headers.put("name", "doge");
		headers.put("age", 24);
		//其实这里的'key-headers'随便设置即可,对于headers类似的exchange没有关系
		channel.queueBind("queue-headers", "exchange-headers", "key-headers", headers);
		AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
		builder.headers(headers);
		AMQP.BasicProperties basicProperties = builder.build();
		channel.basicPublish("exchange-headers", "key-headers", basicProperties, "headers-message".getBytes());
		channel.close();
		connection.close();
	}
}
