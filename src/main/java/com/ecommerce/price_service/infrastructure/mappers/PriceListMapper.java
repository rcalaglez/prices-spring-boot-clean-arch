package com.ecommerce.price_service.infrastructure.mappers;

import com.ecommerce.price_service.data.jpa.entities.PriceListEntity;
import com.ecommerce.price_service.domain.model.PriceList;
import org.springframework.stereotype.Component;

@Component
public class PriceListMapper {

    public PriceList toDomain(PriceListEntity entity) {
        return new PriceList(entity.getDescription());
    }

}
