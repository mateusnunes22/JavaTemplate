package com.springproject.entrypoint.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String errorMessage;

    private String codeStatus;

    private String timestamp;
}
