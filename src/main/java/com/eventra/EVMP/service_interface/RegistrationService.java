package com.eventra.EVMP.service_interface;


import com.eventra.EVMP.domain_entities.RegistrationStatus;
import com.eventra.EVMP.domain_entities.TicketType;
import com.eventra.EVMP.dtos.CreateRegistrationDTO;
import com.eventra.EVMP.dtos.RegistrationResponseDTO;
import com.eventra.EVMP.dtos.UpdateRegistrationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface RegistrationService {

    RegistrationResponseDTO createRegistration(CreateRegistrationDTO dto);

    RegistrationResponseDTO updateRegistration(Long registrationId, UpdateRegistrationDTO dto);

    RegistrationResponseDTO getRegistrationById(Long registrationId);

    RegistrationResponseDTO getRegistrationByNumber(String registrationNumber);

    Page<RegistrationResponseDTO> getAllRegistrations(Pageable pageable);

    Page<RegistrationResponseDTO> getRegistrationsByEventId(Long eventId, Pageable pageable);

    Page<RegistrationResponseDTO> getRegistrationsByStatus(RegistrationStatus status, Pageable pageable);

    Page<RegistrationResponseDTO> getRegistrationsByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<RegistrationResponseDTO> getRegistrationsBySourceChannel(String sourceChannel, Pageable pageable);

    Page<RegistrationResponseDTO> getRegistrationsByTicketType(TicketType ticketType, Pageable pageable);

    Page<RegistrationResponseDTO> getCancelledRegistrations(Pageable pageable);

    boolean registrationNumberExists(String registrationNumber);

    void deleteRegistration(Long registrationId);
}
