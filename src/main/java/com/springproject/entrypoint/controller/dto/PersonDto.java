package com.springproject.entrypoint.controller.dto;

import com.springproject.core.type.YesNoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    private String name;

    private String email;

    private YesNoEnum isActive;
}
