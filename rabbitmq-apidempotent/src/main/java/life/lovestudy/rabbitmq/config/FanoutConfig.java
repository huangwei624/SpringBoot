package life.lovestudy.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class FanoutConfig {
	private final String EMAIL_QUEUE_NAME="boot_email_queue";
	private final String SMS_QUEUE_NAME="boot_sms_queue";
	private final String EXCHANGE_NAME = "boot_exchange";
	// 死信队列
	private final String DEAL_QUEUE_NAME = "deal_queue";
	// 死信邮件交换机
	private final String DEAN_MAIL_EXCHANGE_NAME = "deal_mail_exchange";
	
	/**
	 * 死信队列 交换机标识符
	 */
	public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
	/**
	 * 死信队列交换机绑定键标识符
	 */
	public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
	
	
	// email 队列
	@Bean
	public Queue fanoutEmailQueue(){
		HashMap<String, Object> map = new HashMap<>();
		map.put(DEAD_LETTER_QUEUE_KEY, DEAN_MAIL_EXCHANGE_NAME);
		return new Queue(EMAIL_QUEUE_NAME, true, false, false, map);
	}
	
	// 短信队列
	@Bean
	public Queue fanoutSmsQueue(){
		return new Queue(SMS_QUEUE_NAME);
	}
	
	// fanout 交换机
	@Bean
	public FanoutExchange fanoutExchange(){
		return new FanoutExchange(EXCHANGE_NAME);
	}
	
	// 将email队列绑定到fanout交换机上
	@Bean
	public Binding bindFanoutEmailQueue(Queue fanoutEmailQueue, FanoutExchange fanoutExchange){
		return BindingBuilder.bind(fanoutEmailQueue).to(fanoutExchange);
	}
	
	// 将sms队列绑定到fanout交换机上
	@Bean
	public Binding bindFanoutSmsQueue(Queue fanoutSmsQueue, FanoutExchange fanoutExchange){
		return BindingBuilder.bind(fanoutSmsQueue).to(fanoutExchange);
	}
	
	// 给邮件队列配置死信队列和死信交换机
	@Bean
	public Queue fanoutEmailDealQueue(){
		return new Queue(DEAL_QUEUE_NAME);
	}
	
	@Bean
	public FanoutExchange fanoutEmailDealExchange(){
		return new FanoutExchange(DEAN_MAIL_EXCHANGE_NAME);
	}
	
	@Bean
	public Binding bindEmailDealQueue(Queue fanoutEmailDealQueue, FanoutExchange fanoutEmailDealExchange){
		return BindingBuilder.bind(fanoutEmailDealQueue).to(fanoutEmailDealExchange);
	}
}
