package com.eventra.EVMP.business_layer;

import com.eventra.EVMP.data_accesslayer.EventRepoService;
import com.eventra.EVMP.domain_entities.EventStatus;
import com.eventra.EVMP.domain_entities.EventType;
import com.eventra.EVMP.domain_entities.Events_EIOP;
import com.eventra.EVMP.domain_entities.VenueType;
import com.eventra.EVMP.dtos.CreateEventDTO;
import com.eventra.EVMP.dtos.EventResponseDTO;
import com.eventra.EVMP.dtos.UpdateEventDTO;
import com.eventra.EVMP.mappers.EventMapper;
import com.eventra.EVMP.service_interface.EventService;
import com.eventra.EVMP.validations_utils.EvnetEIOP_validator;
import com.eventra.EVMP.validations_utils.SystemClock;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepoService repoService;
    private final EventMapper mapper;
    private final EvnetEIOP_validator validator;
    private final SystemClock systemClock;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public EventResponseDTO createEvent(CreateEventDTO dto) {
        validator.validateCreateRequest(dto);
        Events_EIOP entity = mapper.toEntity(dto);
        entity.setCreatedDate(systemClock.now());
        entity.setStatus(EventStatus.ACTIVE);
        return toDto(repoService.save(entity));
    }

    @Override
    public EventResponseDTO updateEvent(Long eventId, UpdateEventDTO dto) {
        validator.validateUpdateRequest(eventId, dto);
        Events_EIOP entity = getEventsById(eventId);
        entity.setLastModifiedDate(systemClock.now());
        mapper.updateEntity(entity, dto);
        return toDto(repoService.update(entity));
    }


    @Override
    public EventResponseDTO getEventById(Long eventId) {
        return toDto(repoService.findById(eventId));
    }

    @Override
    public EventResponseDTO getEventByCode(String eventCode) {
        return toDto(repoService.findByEventCode(eventCode));
    }

    @Override
    public Page<EventResponseDTO> getAllEvents(Pageable pageable) {
        return mapPage(repoService.findAll(pageable));
    }

    @Override
    public Page<EventResponseDTO> getEventsByStatus(EventStatus status, Pageable pageable) {
        return mapPage(repoService.findAllByStatus(status, pageable));
    }

    @Override
    public Page<EventResponseDTO> getEventsInDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return mapPage(repoService.findEventsInDateRange(start, end, pageable));
    }

    @Override
    public Page<EventResponseDTO> getActiveEventsByType(EventType eventType, Pageable pageable) {
        return mapPage(repoService.findActiveEventsByType(eventType, pageable));
    }

    @Override
    public Page<EventResponseDTO> getByVenueType(VenueType venueType, Pageable pageable) {
        return mapPage(repoService.findEventsByVenueType(venueType, pageable));
    }

    @Override
    public Page<EventResponseDTO> searchEventsByOrganizer(String organizer, Pageable pageable) {
        return mapPage(repoService.searchByOrganizer(organizer, pageable));
    }

    @Override
    public Page<EventResponseDTO> searchEventsByTitle(String title, Pageable pageable) {
        return mapPage(repoService.searchByTitle(title, pageable));
    }

    @Override
    public boolean eventCodeExists(String eventCode) {
        return repoService.existsByEventCode(eventCode);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteEvent(Events_EIOP eventsEiop) {
        repoService.delete(eventsEiop);
    }

    // 🔽 Private Helper Methods

    private EventResponseDTO toDto(Events_EIOP entity) {
        return mapper.toDto(entity);
    }

    private Page<EventResponseDTO> mapPage(Page<Events_EIOP> page) {
        return page.map(mapper::toDto);
    }

    private Events_EIOP getEventsById(Long eventId) {
        Events_EIOP entity = repoService.findById(eventId);
        return entity;
    }
}
