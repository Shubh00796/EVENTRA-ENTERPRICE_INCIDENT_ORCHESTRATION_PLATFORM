package com.eventra.EVMP.mappers;


import com.eventra.EVMP.domain_entities.Events_EIOP;
import com.eventra.EVMP.dtos.CreateEventDTO;
import com.eventra.EVMP.dtos.EventResponseDTO;
import com.eventra.EVMP.dtos.UpdateEventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventResponseDTO toDto(Events_EIOP event);

    Events_EIOP toEntity(CreateEventDTO createDto);

    void updateEntity(@MappingTarget Events_EIOP event, UpdateEventDTO updateDto);
}
