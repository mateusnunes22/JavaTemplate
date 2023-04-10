package com.springproject.entrypoint.controller.dto;

import com.springproject.core.type.YesNoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;

    private YesNoEnum isActive;
}
