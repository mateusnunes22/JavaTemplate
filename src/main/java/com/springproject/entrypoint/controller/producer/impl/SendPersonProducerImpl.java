package com.springproject.entrypoint.controller.producer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.springproject.entrypoint.controller.producer.SendPersonProducer;
import com.springproject.entrypoint.controller.producer.send.PersonMessageSend;

@Service
public class SendPersonProducerImpl implements SendPersonProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void send(PersonMessageSend personMessageSend) {
		kafkaTemplate.send("tp-person-new", personMessageSend);
	}

}
