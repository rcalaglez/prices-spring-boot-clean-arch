package com.ecommerce.price_service.data.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "price_list", schema = "ecommerce")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

}
