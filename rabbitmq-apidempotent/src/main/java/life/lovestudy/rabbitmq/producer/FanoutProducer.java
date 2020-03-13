package life.lovestudy.rabbitmq.producer;

import life.lovestudy.utils.DateFormatUtil;
import life.lovestudy.utils.RandomUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FanoutProducer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	// 群发，给绑定这个exchange的所有队列发消息
	public void sendMsg(String exchange, String msg) {
		msg += new Date();
		amqpTemplate.convertAndSend(exchange, "" ,convertToMessage(msg));
	}
	
	// 点对点发送
	public void sendMsgByQueue(String queueName, String msg) {
		msg = msg +":"+ DateFormatUtil.format(new Date());
		amqpTemplate.convertAndSend(queueName, convertToMessage(msg));
	}
	
	private Message convertToMessage(String msg){
		return MessageBuilder
				       .withBody(msg.getBytes())
				       .setContentType(MessageProperties.CONTENT_TYPE_JSON)
				       .setContentEncoding("utf-8").setMessageId(RandomUtil.UUID32()).build();
	}
}
