package com.eventra.EVMP.data_accesslayer;


import com.eventra.EVMP.domain_entities.Events_EIOP;
import com.eventra.EVMP.exceptions.ResourceNotFoundException;
import com.eventra.EVMP.reposiotries.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventRepoService {

    private final EventRepository eventRepository;

    public Events_EIOP findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));
    }

    public Optional<Events_EIOP> findByEventCode(String eventCode) {
        return eventRepository.findByEventCode(eventCode);
    }

    public Page<Events_EIOP> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public Page<Events_EIOP> findAllByStatus(String status, Pageable pageable) {
        return eventRepository.findAllByStatus(status, pageable);
    }

    public Page<Events_EIOP> findEventsInDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return eventRepository.findEventsInDateRange(start, end, pageable);
    }

    public Page<Events_EIOP> findActiveEventsByType(String eventType, Pageable pageable) {
        return eventRepository.findActiveEventsByType(eventType, pageable);
    }

    public Page<Events_EIOP> searchByOrganizer(String organizer, Pageable pageable) {
        return eventRepository.searchByOrganizer(organizer, pageable);
    }

    public Page<Events_EIOP> searchByTitle(String title, Pageable pageable) {
        return eventRepository.searchByTitle(title, pageable);
    }

    public boolean existsByEventCode(String eventCode) {
        return eventRepository.existsByEventCode(eventCode);
    }

    public Events_EIOP save(Events_EIOP event) {
        return eventRepository.save(event);
    }

    public Events_EIOP update(Events_EIOP event) {
        return eventRepository.save(event);
    }


    public void delete(Events_EIOP event) {
        eventRepository.delete(event);
    }
}
