package com.springproject.entrypoint.controller.consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonConsumerTest {

    public PersonConsumer personConsumer = mock(PersonConsumer.class);

    @Test
    @DisplayName("Read queue of Kafka")
    void consumeTest(){
    }
}
