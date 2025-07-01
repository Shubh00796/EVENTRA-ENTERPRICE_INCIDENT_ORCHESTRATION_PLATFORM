package com.eventra.EVMP.controllers;


import com.eventra.EVMP.domain_entities.RegistrationStatus;
import com.eventra.EVMP.domain_entities.TicketType;
import com.eventra.EVMP.dtos.ApiResponse;
import com.eventra.EVMP.dtos.CreateRegistrationDTO;
import com.eventra.EVMP.dtos.RegistrationResponseDTO;
import com.eventra.EVMP.dtos.UpdateRegistrationDTO;
import com.eventra.EVMP.service_interface.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<ApiResponse<RegistrationResponseDTO>> createRegistration(@RequestBody CreateRegistrationDTO dto) {
        RegistrationResponseDTO response = registrationService.createRegistration(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registration created successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RegistrationResponseDTO>> updateRegistration(
            @PathVariable Long id,
            @RequestBody UpdateRegistrationDTO dto) {
        RegistrationResponseDTO response = registrationService.updateRegistration(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registration updated successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RegistrationResponseDTO>> getRegistrationById(@PathVariable Long id) {
        RegistrationResponseDTO response = registrationService.getRegistrationById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registration fetched successfully", response));
    }

    @GetMapping("/number/{registrationNumber}")
    public ResponseEntity<ApiResponse<RegistrationResponseDTO>> getByRegistrationNumber(@PathVariable String registrationNumber) {
        RegistrationResponseDTO response = registrationService.getRegistrationByNumber(registrationNumber);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registration fetched successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<RegistrationResponseDTO>>> getAllRegistrations(Pageable pageable) {
        Page<RegistrationResponseDTO> page = registrationService.getAllRegistrations(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "All registrations fetched", page));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<ApiResponse<Page<RegistrationResponseDTO>>> getByEventId(@PathVariable Long eventId, Pageable pageable) {
        Page<RegistrationResponseDTO> page = registrationService.getRegistrationsByEventId(eventId, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registrations by event fetched", page));
    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<Page<RegistrationResponseDTO>>> getByStatus(
            @RequestParam RegistrationStatus status, Pageable pageable) {
        Page<RegistrationResponseDTO> page = registrationService.getRegistrationsByStatus(status, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registrations by status fetched", page));
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<Page<RegistrationResponseDTO>>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            Pageable pageable) {
        Page<RegistrationResponseDTO> page = registrationService.getRegistrationsByDateRange(start, end, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registrations by date range fetched", page));
    }

    @GetMapping("/source-channel")
    public ResponseEntity<ApiResponse<Page<RegistrationResponseDTO>>> getBySourceChannel(
            @RequestParam String sourceChannel, Pageable pageable) {
        Page<RegistrationResponseDTO> page = registrationService.getRegistrationsBySourceChannel(sourceChannel, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registrations by source channel fetched", page));
    }

    @GetMapping("/ticket-type")
    public ResponseEntity<ApiResponse<Page<RegistrationResponseDTO>>> getByTicketType(
            @RequestParam TicketType ticketType, Pageable pageable) {
        Page<RegistrationResponseDTO> page = registrationService.getRegistrationsByTicketType(ticketType, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registrations by ticket type fetched", page));
    }

    @GetMapping("/cancelled")
    public ResponseEntity<ApiResponse<Page<RegistrationResponseDTO>>> getCancelledRegistrations(Pageable pageable) {
        Page<RegistrationResponseDTO> page = registrationService.getCancelledRegistrations(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cancelled registrations fetched", page));
    }

    @GetMapping("/exists/{registrationNumber}")
    public ResponseEntity<ApiResponse<Boolean>> checkRegistrationNumberExists(@PathVariable String registrationNumber) {
        boolean exists = registrationService.registrationNumberExists(registrationNumber);
        return ResponseEntity.ok(new ApiResponse<>(true, "Existence check completed", exists));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Registration deleted successfully", null));
    }
}
