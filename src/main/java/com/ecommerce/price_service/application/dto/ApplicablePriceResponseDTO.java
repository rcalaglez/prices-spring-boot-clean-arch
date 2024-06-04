package com.ecommerce.price_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicablePriceResponseDTO {

    private String brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String priceListName;
    private ProductDTO product;
    private BigDecimal price;
}
