package com.ecommerce.price_service.data.jpa.query;

public class PriceQueryUtils {

    public static final String FIND_BY_PRODUCT_CODE_AND_BRAND_NAME_ORDER_BY_PRIORITY_QUERY =
        "SELECT p FROM PriceEntity p " +
                "JOIN FETCH p.product prod " +
                "JOIN FETCH p.brand brand " +
                "WHERE prod.code = :productCode " +
                "AND brand.name = :brandName " +
                "AND p.startDate <= :applicationDate " +
                "AND p.endDate >= :applicationDate " +
                "ORDER BY p.priority DESC";

}
