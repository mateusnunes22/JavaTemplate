package com.springproject.entrypoint.controller.consumer.recive;

import java.io.Serializable;

import com.springproject.core.type.YesNoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonMessageConsume implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String email;
    
    private YesNoEnum isActive;

}
