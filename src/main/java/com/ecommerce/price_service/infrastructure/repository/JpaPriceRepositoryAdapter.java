package com.ecommerce.price_service.infrastructure.repository;

import com.ecommerce.price_service.data.jpa.entities.PriceEntity;
import com.ecommerce.price_service.data.jpa.repository.JpaPriceRepository;
import com.ecommerce.price_service.domain.model.Price;
import com.ecommerce.price_service.domain.repository.PriceRepository;
import com.ecommerce.price_service.infrastructure.mappers.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaPriceRepositoryAdapter implements PriceRepository {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public Flux<Price> findByProductIdAndBrandIdAndDate(String product, String brand, LocalDateTime applicationDate) {
        List<PriceEntity> entities = jpaPriceRepository.findByProductCodeAndBrandNameAndDate(
                product,
                brand,
                applicationDate
        );

        return Flux
                .fromIterable(entities.stream().map(priceMapper::toDomain).collect(Collectors.toList()));
    }
}
