package com.ecommerce.price_service.data.jpa.repository;

import com.ecommerce.price_service.data.jpa.entities.PriceEntity;
import com.ecommerce.price_service.data.jpa.query.PriceQueryUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(PriceQueryUtils.FIND_BY_PRODUCT_CODE_AND_BRAND_NAME_ORDER_BY_PRIORITY_QUERY)
    List<PriceEntity> findByProductCodeAndBrandNameAndDate(
            @Param("productCode") String productCode,
            @Param("brandName") String brandName,
            @Param("applicationDate") LocalDateTime applicationDate);

}
