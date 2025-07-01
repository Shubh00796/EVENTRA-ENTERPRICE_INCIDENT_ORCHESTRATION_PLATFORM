package com.eventra.EVMP.mappers;


import com.eventra.EVMP.domain_entities.RegistrationsEIOP;
import com.eventra.EVMP.dtos.CreateRegistrationDTO;
import com.eventra.EVMP.dtos.RegistrationResponseDTO;
import com.eventra.EVMP.dtos.UpdateRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    RegistrationResponseDTO toDto(RegistrationsEIOP registration);

    RegistrationsEIOP toEntity(CreateRegistrationDTO createDto);

    void updateEntity(@MappingTarget RegistrationsEIOP registration, UpdateRegistrationDTO updateDto);
}
