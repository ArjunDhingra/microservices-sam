package com.eatza.restaurantsearch.service.kafkaListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderMsgsService {

	private static final Logger log = LogManager.getLogger(OrderMsgsService.class);

	@KafkaListener(topics = "order_placed", groupId = "order_msg", containerFactory = "kafkaListenerContainerFactory")
	public void getOrderPlaceMsg(String message) {
		log.info("Consumed message is: {}", message);
	}

	@KafkaListener(topics = "order_cancel", groupId = "order_msg", containerFactory = "kafkaListenerContainerFactory")
	public void getOrderCancelMsg(String message) {
		log.info("Consumed message is: {}", message);
	}

	@KafkaListener(topics = "update_order", groupId = "order_msg", containerFactory = "kafkaListenerContainerFactory")
	public void getOrderUpdateMsg(String message) {
		log.info("Consumed message is: {}", message);
	}
}
