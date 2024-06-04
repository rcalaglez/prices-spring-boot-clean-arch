package com.ecommerce.price_service.infrastructure.mappers;

import com.ecommerce.price_service.data.jpa.entities.BrandEntity;
import com.ecommerce.price_service.domain.model.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    public Brand toDomain(BrandEntity entity) {
        return new Brand(entity.getName());
    }

}
