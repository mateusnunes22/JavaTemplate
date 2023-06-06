package com.springproject.entrypoint.controller.producer;

import com.springproject.entity.mock.PersonBase;
import com.springproject.entrypoint.controller.producer.impl.SendPersonProducerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class SendPersonProducerTest extends PersonBase {

    @InjectMocks
    public SendPersonProducerImpl sendPersonProducer;

    @Mock
    public KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void sendTest() {
        sendPersonProducer.send(personMessageSend);
        Mockito.verify(kafkaTemplate).send("tp-person-new", personMessageSend);
    }
}
