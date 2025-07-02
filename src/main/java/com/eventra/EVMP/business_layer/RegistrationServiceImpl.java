package com.eventra.EVMP.business_layer;

import com.eventra.EVMP.data_accesslayer.RegistrationRepoService;
import com.eventra.EVMP.domain_entities.RegistrationStatus;
import com.eventra.EVMP.domain_entities.RegistrationsEIOP;
import com.eventra.EVMP.domain_entities.TicketType;
import com.eventra.EVMP.dtos.CreateRegistrationDTO;
import com.eventra.EVMP.dtos.RegistrationResponseDTO;
import com.eventra.EVMP.dtos.UpdateRegistrationDTO;
import com.eventra.EVMP.mappers.RegistrationMapper;
import com.eventra.EVMP.service_interface.RegistrationService;
import com.eventra.EVMP.validations_utils.RegistrationValidator;
import com.eventra.EVMP.validations_utils.SystemClock;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepoService repoService;
    private final RegistrationMapper mapper;
    private final RegistrationValidator validator;
    private final SystemClock systemClock;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public RegistrationResponseDTO createRegistration(CreateRegistrationDTO dto) {
        validator.validateCreateRequest(dto);
        RegistrationsEIOP registrationsEIOP = mapper.toEntity(dto);
        registrationsEIOP.setRegistrationNumber(UUID.randomUUID().toString());
        registrationsEIOP.setCreatedDate(systemClock.now());
        return toDto(repoService.save(registrationsEIOP));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public RegistrationResponseDTO updateRegistration(Long registrationId, UpdateRegistrationDTO dto) {
        validator.validateUpdateRequest(registrationId, dto);
        RegistrationsEIOP registrationsEIOP = getRegistrationsEIOP(registrationId);
        mapper.updateEntity(registrationsEIOP, dto);
        registrationsEIOP.setLastModifiedDate(systemClock.now());
        return toDto(repoService.save(registrationsEIOP));
    }

    @Override
    public RegistrationResponseDTO getRegistrationById(Long registrationId) {
        RegistrationsEIOP registrationsEIOP = getRegistrationsEIOP(registrationId);
        return mapper.toDto(registrationsEIOP);
    }


    @Override
    public RegistrationResponseDTO getRegistrationByNumber(String registrationNumber) {
        RegistrationsEIOP byRegistrationNumber = repoService.findByRegistrationNumber(registrationNumber);
        return mapper.toDto(byRegistrationNumber);
    }

    @Override
    public Page<RegistrationResponseDTO> getAllRegistrations(Pageable pageable) {
        return mapPage(repoService.findAll(pageable));

    }

    @Override
    public Page<RegistrationResponseDTO> getRegistrationsByEventId(Long eventId, Pageable pageable) {
        return mapPage(repoService.findAllByEventId(eventId, pageable));
    }

    @Override
    public Page<RegistrationResponseDTO> getRegistrationsByStatus(RegistrationStatus status, Pageable pageable) {
        return mapPage(repoService.findAllByStatus(status, pageable));
    }

    @Override
    public Page<RegistrationResponseDTO> getRegistrationsByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return mapPage(repoService.findByRegistrationDateRange(start, end, pageable));
    }

    @Override
    public Page<RegistrationResponseDTO> getRegistrationsBySourceChannel(String sourceChannel, Pageable pageable) {
        return mapPage(repoService.findAllBySourceChannel(sourceChannel, pageable));
    }

    @Override
    public Page<RegistrationResponseDTO> getRegistrationsByTicketType(TicketType ticketType, Pageable pageable) {
        return mapPage(repoService.findAllByTicketType(ticketType, pageable));
    }

    @Override
    public Page<RegistrationResponseDTO> getCancelledRegistrations(Pageable pageable) {
        return mapPage(repoService.findAllCancelled(pageable));
    }

    @Override
    public boolean registrationNumberExists(String registrationNumber) {
        return repoService.existsByRegistrationNumber(registrationNumber);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteRegistration(Long registrationId) {
        repoService.delete(registrationId);

    }

    private RegistrationResponseDTO toDto(RegistrationsEIOP entity) {
        return mapper.toDto(entity);
    }

    private Page<RegistrationResponseDTO> mapPage(Page<RegistrationsEIOP> page) {
        return page.map(mapper::toDto);
    }

    private RegistrationsEIOP getRegistrationsEIOP(Long registrationId) {
        RegistrationsEIOP registrationsEIOP = repoService.findById(registrationId);
        return registrationsEIOP;
    }
}
