package com.eventra.EVMP.controllers;


import com.eventra.EVMP.domain_entities.EventStatus;
import com.eventra.EVMP.domain_entities.EventType;
import com.eventra.EVMP.domain_entities.Events_EIOP;
import com.eventra.EVMP.domain_entities.VenueType;
import com.eventra.EVMP.dtos.CreateEventDTO;
import com.eventra.EVMP.dtos.EventResponseDTO;
import com.eventra.EVMP.dtos.UpdateEventDTO;
import com.eventra.EVMP.service_interface.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody CreateEventDTO dto) {
        return ResponseEntity.ok(eventService.createEvent(dto));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long eventId,
                                                        @Valid @RequestBody UpdateEventDTO dto) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, dto));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    @GetMapping("/code/{eventCode}")
    public ResponseEntity<EventResponseDTO> getEventByCode(@PathVariable String eventCode) {
        return ResponseEntity.ok(eventService.getEventByCode(eventCode));
    }

    @GetMapping
    public ResponseEntity<Page<EventResponseDTO>> getAllEvents(Pageable pageable) {
        return ResponseEntity.ok(eventService.getAllEvents(pageable));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<EventResponseDTO>> getEventsByStatus(@RequestParam EventStatus status,
                                                                    Pageable pageable) {
        return ResponseEntity.ok(eventService.getEventsByStatus(status, pageable));
    }

    @GetMapping("/date-range")
    public ResponseEntity<Page<EventResponseDTO>> getEventsInDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            Pageable pageable) {
        return ResponseEntity.ok(eventService.getEventsInDateRange(start, end, pageable));
    }

    @GetMapping("/type")
    public ResponseEntity<Page<EventResponseDTO>> getActiveEventsByType(@RequestParam EventType eventType,
                                                                        Pageable pageable) {
        return ResponseEntity.ok(eventService.getActiveEventsByType(eventType, pageable));
    }

    @GetMapping("/venue")
    public ResponseEntity<Page<EventResponseDTO>> getByVenueType(@RequestParam VenueType venueType,
                                                                 Pageable pageable) {
        return ResponseEntity.ok(eventService.getByVenueType(venueType, pageable));
    }

    @GetMapping("/search/organizer")
    public ResponseEntity<Page<EventResponseDTO>> searchByOrganizer(@RequestParam String organizer,
                                                                    Pageable pageable) {
        return ResponseEntity.ok(eventService.searchEventsByOrganizer(organizer, pageable));
    }

    @GetMapping("/search/title")
    public ResponseEntity<Page<EventResponseDTO>> searchByTitle(@RequestParam String title,
                                                                Pageable pageable) {
        return ResponseEntity.ok(eventService.searchEventsByTitle(title, pageable));
    }

    @GetMapping("/exists/{eventCode}")
    public ResponseEntity<Boolean> eventCodeExists(@PathVariable String eventCode) {
        return ResponseEntity.ok(eventService.eventCodeExists(eventCode));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEvent(Events_EIOP event) {
        eventService.deleteEvent(event);
        return ResponseEntity.noContent().build();
    }
}
