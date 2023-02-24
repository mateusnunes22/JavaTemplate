package com.springproject.entrypoint.controller.producer;

import com.springproject.entrypoint.controller.producer.send.PersonMessageSend;

public interface SendPersonProducer {
	void send(PersonMessageSend personMessageSend);
}
