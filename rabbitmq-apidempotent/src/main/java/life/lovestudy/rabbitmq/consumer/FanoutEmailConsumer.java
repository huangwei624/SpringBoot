package life.lovestudy.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import life.lovestudy.common.Constant;
import life.lovestudy.utils.JedisUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class FanoutEmailConsumer {
	
	@Autowired
	private JedisUtil jedisUtil;
	
	@RabbitListener(queues="boot_email_queue")
	public void process(Message msg, @Headers Map<String, Object> headers, Channel channel) throws IOException {
		String messageId = msg.getMessageProperties().getMessageId();   // 唯一标识
		String message = new String(msg.getBody(), StandardCharsets.UTF_8);
		System.out.println("邮件消费者收到了一个消息："+ message);
		// 解决消息重试导致重复消费带来的幂等性问题
		// 方法：通过messageId的唯一性来判断消息是否重复消费了
		// 具体实施：当第一次消费业务的时候，向redis数据库中set 这个messageId(设置过期时间),
		// 当再次消费时，判断redis中是否存在这个messageId，如果存在就不执行这个业务代码了
		String messageKey = Constant.Redis.MESSAGE_PREFIX+messageId;
		if(!jedisUtil.exists(messageKey)){
			jedisUtil.set(messageKey, messageKey, Constant.Redis.EXPIRE_TIME_MINUTE * 10);
			// 发邮件
			System.out.println("------------>> 成功发送邮件");
		}
		long deliverTag = (long)headers.get(AmqpHeaders.DELIVERY_TAG);
		// 将消息转发到死信队列
		try {
			throw new RuntimeException("邮件消费者处理消息时发生异常！");
		} catch (RuntimeException e) {
			e.printStackTrace();
			channel.basicNack(deliverTag, false, false);
		}
		// 手动签收
//		channel.basicAck(deliverTag, false);
	}
	
	// 监听死信队列中的消息
	@RabbitListener(queues="deal_queue")
	public void process2(Message msg, @Headers Map<String, Object> headers, Channel channel) throws IOException {
		String message = new String(msg.getBody(), StandardCharsets.UTF_8);
		String messageId = msg.getMessageProperties().getMessageId();
		System.out.println("message:"+message+"messageId"+messageId);
		channel.basicAck((long)headers.get(AmqpHeaders.DELIVERY_TAG),false);
	}
}
