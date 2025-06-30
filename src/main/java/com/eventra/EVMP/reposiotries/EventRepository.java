package com.eventra.EVMP.reposiotries;


import com.eventra.EVMP.domain_entities.Events_EIOP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Events_EIOP, Long> {

    Optional<Events_EIOP> findByEventCode(String eventCode);


    Page<Events_EIOP> findAllByStatus(String status, Pageable pageable);

    @Query("SELECT e FROM Events_EIOP e WHERE e.startDateTime BETWEEN :start AND :end")
    Page<Events_EIOP> findEventsInDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable);

    @Query("SELECT e FROM Events_EIOP e WHERE e.eventType = :eventType AND e.status = 'ACTIVE'")
    Page<Events_EIOP> findActiveEventsByType(String eventType, Pageable pageable);

    @Query("SELECT e FROM Events_EIOP e WHERE e.organizerDetails LIKE %:organizer%")
    Page<Events_EIOP> searchByOrganizer(String organizer, Pageable pageable);

    @Query("SELECT e FROM Events_EIOP e WHERE e.title LIKE %:title%")
    Page<Events_EIOP> searchByTitle(String title, Pageable pageable);


    boolean existsByEventCode(String eventCode);
}
