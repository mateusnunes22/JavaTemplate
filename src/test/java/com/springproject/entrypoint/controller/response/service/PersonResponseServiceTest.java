package com.springproject.entrypoint.controller.response.service;

import com.springproject.entity.mock.PersonBase;
import com.springproject.entrypoint.controller.dto.PersonDto;
import com.springproject.entrypoint.controller.response.PersonResponse;
import com.springproject.entrypoint.controller.response.service.impl.PersonResponseServiceImpl;
import com.springproject.mapper.PersonMapper;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonResponseServiceTest extends PersonBase {

    @InjectMocks
    public PersonResponseServiceImpl personResponseService;

    @Mock
    public PersonMapper mapper;

    @Mock
    public BaseResponseService baseResponseService;

    @Test
    @DisplayName("Find all persons to response")
    void findAllTest(){
        when(mapper.toDtoList(anyList())).thenReturn(List.of(personDto));
        PersonResponse response = personResponseService.getAll(new ArrayList<>());
        Assert.assertEquals(response.getPersonDtoList(), List.of(personDto));
    }

    @Test
    @DisplayName("Find person by Id to response")
    void findByIdTest(){
        when(mapper.map(personDomain, PersonDto.class)).thenReturn(personDto);
        PersonResponse response = personResponseService.getOne(personDomain);
        Assert.assertEquals(response.getPersonDto(), personDto);
    }

}
