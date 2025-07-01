package com.eventra.EVMP.service_interface;

import com.eventra.EVMP.domain_entities.EventStatus;
import com.eventra.EVMP.domain_entities.EventType;
import com.eventra.EVMP.domain_entities.Events_EIOP;
import com.eventra.EVMP.domain_entities.VenueType;
import com.eventra.EVMP.dtos.CreateEventDTO;
import com.eventra.EVMP.dtos.EventResponseDTO;
import com.eventra.EVMP.dtos.UpdateEventDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventService {

    EventResponseDTO createEvent(CreateEventDTO dto);

    EventResponseDTO updateEvent(Long eventId, UpdateEventDTO dto);

    EventResponseDTO getEventById(Long eventId);

    EventResponseDTO getEventByCode(String eventCode);

    Page<EventResponseDTO> getAllEvents(Pageable pageable);

    Page<EventResponseDTO> getEventsByStatus(EventStatus status, Pageable pageable);

    Page<EventResponseDTO> getEventsInDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<EventResponseDTO> getActiveEventsByType(EventType eventType, Pageable pageable);

    Page<EventResponseDTO> getByVenueType(VenueType venueType, Pageable pageable);


    Page<EventResponseDTO> searchEventsByOrganizer(String organizer, Pageable pageable);

    Page<EventResponseDTO> searchEventsByTitle(String title, Pageable pageable);

    boolean eventCodeExists(String eventCode);

    void deleteEvent(Events_EIOP eventsEiop);
}
