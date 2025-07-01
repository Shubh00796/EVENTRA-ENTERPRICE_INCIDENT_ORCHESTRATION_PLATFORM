package com.eventra.EVMP.reposiotries;

import com.eventra.EVMP.domain_entities.RegistrationsEIOP;
import com.eventra.EVMP.domain_entities.RegistrationStatus;
import com.eventra.EVMP.domain_entities.TicketType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<RegistrationsEIOP, Long> {

    Optional<RegistrationsEIOP> findByRegistrationNumber(String registrationNumber);

    Page<RegistrationsEIOP> findAllByEventId(Long eventId, Pageable pageable);

    Optional<RegistrationsEIOP> findByEventId(Long eventId);

    Page<RegistrationsEIOP> findAllByStatus(RegistrationStatus status, Pageable pageable);

    @Query("SELECT r FROM RegistrationsEIOP r WHERE r.registrationDate BETWEEN :start AND :end")
    Page<RegistrationsEIOP> findByRegistrationDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<RegistrationsEIOP> findAllBySourceChannel(String sourceChannel, Pageable pageable);

    Page<RegistrationsEIOP> findAllByTicketType(TicketType ticketType, Pageable pageable);

    @Query("SELECT r FROM RegistrationsEIOP r WHERE r.cancellationReason IS NOT NULL")
    Page<RegistrationsEIOP> findAllCancelled(Pageable pageable);

    boolean existsByRegistrationNumber(String registrationNumber);
}
