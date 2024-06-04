package com.ecommerce.price_service.domain.model;

import com.ecommerce.price_service.domain.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Price {
    private Brand brand;
    private Product product;
    private PriceList priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private BigDecimal price;
    private CurrencyEnum curr;

}
