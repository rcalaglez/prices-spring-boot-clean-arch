package com.ecommerce.price_service.data.jpa.entities;

import com.ecommerce.price_service.domain.enums.CurrencyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price", schema = "ecommerce", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"brand_id", "price_list_id", "product_id", "start_date", "end_date"})
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "price_list_id", nullable = false)
    private PriceListEntity priceList;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyEnum curr;

}