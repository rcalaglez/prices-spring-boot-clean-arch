package com.ecommerce.price_service.presentation.api.controller;

import com.ecommerce.price_service.application.dto.ApplicablePriceResponseDTO;
import com.ecommerce.price_service.application.usecase.GetPriceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
@Validated
public class PriceController {

    @Autowired
    private GetPriceUseCase getPriceUseCase;

    @GetMapping
    public Mono<ResponseEntity<ApplicablePriceResponseDTO>> getApplicablePrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("product") String product,
            @RequestParam("brand") String brand) {

        return getPriceUseCase.execute(date, product, brand)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
