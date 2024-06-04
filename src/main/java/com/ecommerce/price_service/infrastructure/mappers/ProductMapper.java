package com.ecommerce.price_service.infrastructure.mappers;

import com.ecommerce.price_service.application.dto.ProductDTO;
import com.ecommerce.price_service.data.jpa.entities.ProductEntity;
import com.ecommerce.price_service.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getCode(),
                entity.getName()
        );
    }

    public ProductDTO toDTO(Product domain) {
        return new ProductDTO(
                domain.getName(),
                domain.getCode()
        );
    }

}
