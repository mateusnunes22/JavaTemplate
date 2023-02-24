package com.springproject.entrypoint.controller.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproject.core.domain.PersonDomain;
import com.springproject.core.usecase.PersonUseCase;
import com.springproject.entrypoint.controller.consumer.recive.PersonMessageRecive;
import com.springproject.exception.InvalidGenericException;
import com.springproject.mapper.PersonMapper;

@Component
public class RecivePersonConsumer {

	@Autowired
	private PersonUseCase personUseCase;

	@Autowired
	private PersonMapper mapper;

	@KafkaListener(topics = "tp-person-new", groupId = "${spring.kafka.consumer.group-id}")
	public void recive(ConsumerRecord<?, ?> consumerRecord) {
		try {
			PersonMessageRecive personMessageRecive = new ObjectMapper().readValue(consumerRecord.value().toString(),
					PersonMessageRecive.class);
			personUseCase.save(mapper.map(personMessageRecive, PersonDomain.class));
		} catch (JsonProcessingException e) {
			throw new InvalidGenericException(e.getMessage());
		}
	}
}
