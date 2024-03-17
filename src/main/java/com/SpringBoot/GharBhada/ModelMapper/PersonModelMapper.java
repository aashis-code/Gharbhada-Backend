package com.SpringBoot.GharBhada.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.GharBhada.DTO.PersonDto;
import com.SpringBoot.GharBhada.Entity.Person;


public class PersonModelMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public PersonDto personToPersonDto(Person person) {
		return modelMapper.map(person, PersonDto.class);
				}
	
	public Person PersonDtoToPerson(PersonDto personDto) {
		return modelMapper.map(personDto, Person.class);
	}
	

}
