package life.lovestudy.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// direct 类型的交换器
@Configuration
public class DirectConfig {
	private final String TEST_QUEUE_NAME = "test_queue";
	private final String TEST_EXCHANGE_NAME = "test_exchange";
	
	@Bean
	public Queue testQueue(){
		return new Queue(TEST_QUEUE_NAME);
	}
	
	@Bean
	public DirectExchange testExchange(){
		return new DirectExchange(TEST_EXCHANGE_NAME);
	}
	
	@Bean
	public Binding bindTestQueue(Queue testQueue, DirectExchange testExchange) {
		return BindingBuilder.bind(testQueue).to(testExchange).with("test");
	}
}
