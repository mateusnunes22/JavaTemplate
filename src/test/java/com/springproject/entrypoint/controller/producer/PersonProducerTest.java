package com.springproject.entrypoint.controller.producer;

import com.springproject.entrypoint.controller.consumer.PersonConsumer;
import com.springproject.entrypoint.controller.producer.impl.SendPersonProducerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonProducerTest {

    @InjectMocks
    public SendPersonProducerImpl sendPersonProducer;

    @Test
    @DisplayName("Send message to Kafka's queue")
    void sendTest(){
    }
}
