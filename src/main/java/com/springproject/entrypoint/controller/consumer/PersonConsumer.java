package com.springproject.entrypoint.controller.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproject.core.domain.PersonDomain;
import com.springproject.core.usecase.PersonUseCase;
import com.springproject.entrypoint.controller.consumer.recive.PersonMessageConsume;
import com.springproject.mapper.PersonMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PersonConsumer {

	@Autowired
	private PersonUseCase personUseCase;

	@Autowired
	private PersonMapper mapper;

	@KafkaListener(topics = "${spring.kafka.consumer.topics}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(ConsumerRecord<?, ?> consumerRecord) {
		try {
			PersonMessageConsume personMessageConsume = new ObjectMapper().readValue(consumerRecord.value().toString(),
					PersonMessageConsume.class);
			personUseCase.save(mapper.map(personMessageConsume, PersonDomain.class));
		} catch (JsonProcessingException e) {
			throw new KafkaException(e.getMessage());
		}
	}
}
