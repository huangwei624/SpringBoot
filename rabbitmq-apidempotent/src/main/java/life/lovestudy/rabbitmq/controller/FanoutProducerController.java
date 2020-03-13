package life.lovestudy.rabbitmq.controller;

import life.lovestudy.rabbitmq.producer.FanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanoutProducerController {
	@Autowired
	private FanoutProducer fanoutProducer;
	
	@GetMapping("/sendByExchange/{exchange}/{msg}")
	public String sendByExchange(@PathVariable String exchange, @PathVariable String msg){
		fanoutProducer.sendMsg(exchange, msg);
		return "success";
	}
	
	@GetMapping("/sendByQueue/{queueName}/{msg}")
	public String sendByQueue(@PathVariable String queueName, @PathVariable String msg ){
		fanoutProducer.sendMsgByQueue(queueName, msg);
		return "success";
	}
}
