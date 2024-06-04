package com.ecommerce.price_service.application.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceUseCasesValidator {

    public static void validateGetApplicablePriceInputParams(
            LocalDateTime applicationDate, String product, String brand) {

        if (applicationDate == null) {
            throw new IllegalArgumentException("Date parameter is missing or invalid");
        }

        if (product == null || product.trim().isEmpty()) {
            throw new IllegalArgumentException("Product parameter is missing or empty");
        }

        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand parameter is missing or empty");
        }

    }

}
