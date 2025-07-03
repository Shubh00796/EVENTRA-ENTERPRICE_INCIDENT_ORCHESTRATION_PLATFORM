package com.eventra.EVMP.mappers;


import com.eventra.EVMP.domain_entities.PricingRule;
import com.eventra.EVMP.dtos.CreatePricingRuleRequestDTO;
import com.eventra.EVMP.dtos.PricingRuleResponseDTO;
import com.eventra.EVMP.dtos.UpdatePricingRuleRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PricingRuleMapper {

    PricingRuleResponseDTO toDto(PricingRule pricingRule);

    PricingRule toEntity(CreatePricingRuleRequestDTO dto);

    void updateEntity(@MappingTarget PricingRule existingRule, UpdatePricingRuleRequestDTO dto);
}
