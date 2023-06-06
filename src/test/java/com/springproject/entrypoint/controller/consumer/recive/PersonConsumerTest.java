package com.springproject.entrypoint.controller.consumer.recive;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.usecase.PersonUseCase;
import com.springproject.entity.mock.PersonBase;
import com.springproject.entrypoint.controller.consumer.PersonConsumer;
import com.springproject.mapper.PersonMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.KafkaException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonConsumerTest extends PersonBase {

    @InjectMocks
    public PersonConsumer personConsumer;
    @Mock
    public PersonUseCase personUseCase;
    @Mock
    public PersonMapper mapper;

    @Test
    @DisplayName("consume record: Success")
    void consumeSuccessTest() {
        String personJson = "{\"id\": 1, \"name\": \"levva\", \"email\": \"a@levva.com\", \"isActive\": \"YES\"}";
        ConsumerRecord<String, String> record = new ConsumerRecord<>("topic", 0, 0, "key", personJson);
        when(mapper.map(personMessageConsume, PersonDomain.class)).thenReturn(personDomain);
        personConsumer.consume(record);
        verify(personUseCase, times(1)).save(personDomain);
    }

    @Test
    @DisplayName("consume record: Exception")
    void consumeThrowTest() {
        String personJson = "{\"id\": 123, \"name\": \"Alice\", \"age\": 30}";
        ConsumerRecord<String, String> record = new ConsumerRecord<>("topic", 0, 0, "key", personJson);
        assertThrows(KafkaException.class, () -> personConsumer.consume(record));
    }

}
