package com.springproject.entrypoint.controller.producer.send;

import java.io.Serializable;

import com.springproject.core.type.ActiveEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonMessageSend implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String email;

	private ActiveEnum isActive;
}
