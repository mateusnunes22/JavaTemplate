package com.springproject.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.dto.PersonDTO;
import com.springproject.entity.PersonEntity;
import com.springproject.exception.InvalidGenericException;
import com.springproject.repository.GenericRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	protected GenericRepository<PersonEntity> genericRepository;

	@Override
	public PersonDTO save(PersonDTO dto) {
		try {
			PersonEntity personEntity = genericRepository.save(new ModelMapper().map(dto, PersonEntity.class));
			return new ModelMapper().map(personEntity, PersonDTO.class);
		} catch (Exception e) {
			throw new InvalidGenericException(e.getMessage());
		}
	}

	@Override
	public List<PersonDTO> findAll() {
		try {
			return new ModelMapper().map(genericRepository.findAll(), new TypeToken<List<PersonDTO>>() {
			}.getType());
		} catch (Exception e) {
			throw new InvalidGenericException(e.getMessage());
		}
	}

	@Override
	public String delete(Long id) {
		try {
			genericRepository.deleteById(id);
		} catch (Exception e) {
			throw new InvalidGenericException(e.getMessage());
		}
		return "Data deleted!";

	}
}
