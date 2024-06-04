package com.ecommerce.price_service.application.usecase;

import com.ecommerce.price_service.application.dto.ApplicablePriceResponseDTO;
import com.ecommerce.price_service.domain.repository.PriceRepository;
import com.ecommerce.price_service.infrastructure.mappers.PriceMapper;
import com.ecommerce.price_service.application.validator.PriceUseCasesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class GetPriceUseCase {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    PriceMapper priceMapper;

    public Mono<ApplicablePriceResponseDTO> execute(LocalDateTime date,
                                                    String product,
                                                    String brand) {

        PriceUseCasesValidator.validateGetApplicablePriceInputParams(date, product, brand);

        return priceRepository.findByProductIdAndBrandIdAndDate(product, brand, date)
                .map(priceMapper::toApplicablePriceDTO)
                .next();
    }


}
