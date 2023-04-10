package com.springproject.entrypoint.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springproject.entrypoint.controller.dto.PersonDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties
public class PersonResponse extends BaseResponse {
    private List<PersonDto> personDtoList;
    
    private PersonDto personDto;
}
