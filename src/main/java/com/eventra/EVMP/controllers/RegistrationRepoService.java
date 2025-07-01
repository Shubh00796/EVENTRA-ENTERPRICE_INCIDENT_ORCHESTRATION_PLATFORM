package com.eventra.EVMP.controllers;


import com.eventra.EVMP.domain_entities.RegistrationsEIOP;
import com.eventra.EVMP.domain_entities.RegistrationStatus;
import com.eventra.EVMP.domain_entities.TicketType;
import com.eventra.EVMP.exceptions.ResourceNotFoundException;
import com.eventra.EVMP.reposiotries.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationRepoService {

    private final RegistrationRepository registrationRepository;

    public RegistrationsEIOP findById(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with ID: " + id));
    }

    public RegistrationsEIOP findByEventId(Long eventId) {
        return registrationRepository.findByEventId(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with ID: " + eventId));
    }

    public RegistrationsEIOP findByRegistrationNumber(String registrationNumber) {
        return registrationRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with number: " + registrationNumber));
    }

    public Page<RegistrationsEIOP> findAll(Pageable pageable) {
        return registrationRepository.findAll(pageable);
    }

    public Page<RegistrationsEIOP> findAllByEventId(Long eventId, Pageable pageable) {
        return registrationRepository.findAllByEventId(eventId, pageable);
    }

    public Page<RegistrationsEIOP> findAllByStatus(RegistrationStatus status, Pageable pageable) {
        return registrationRepository.findAllByStatus(status, pageable);
    }

    public Page<RegistrationsEIOP> findByRegistrationDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return registrationRepository.findByRegistrationDateRange(start, end, pageable);
    }

    public Page<RegistrationsEIOP> findAllBySourceChannel(String sourceChannel, Pageable pageable) {
        return registrationRepository.findAllBySourceChannel(sourceChannel, pageable);
    }

    public Page<RegistrationsEIOP> findAllByTicketType(TicketType ticketType, Pageable pageable) {
        return registrationRepository.findAllByTicketType(ticketType, pageable);
    }

    public Page<RegistrationsEIOP> findAllCancelled(Pageable pageable) {
        return registrationRepository.findAllCancelled(pageable);
    }

    public boolean existsByRegistrationNumber(String registrationNumber) {
        return registrationRepository.existsByRegistrationNumber(registrationNumber);
    }

    public RegistrationsEIOP save(RegistrationsEIOP registration) {
        return registrationRepository.save(registration);
    }

    public RegistrationsEIOP update(RegistrationsEIOP registration) {
        return registrationRepository.save(registration);
    }

    public void delete(Long registrationId) {
        registrationRepository.deleteById(registrationId);
    }
}
