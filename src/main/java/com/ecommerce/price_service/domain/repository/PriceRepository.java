package com.ecommerce.price_service.domain.repository;

import com.ecommerce.price_service.domain.model.Price;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository {
    Flux<Price> findByProductIdAndBrandIdAndDate(String product, String brand, LocalDateTime applicationDate);

}
