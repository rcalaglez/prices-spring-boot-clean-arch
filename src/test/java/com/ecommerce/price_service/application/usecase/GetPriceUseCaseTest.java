package com.ecommerce.price_service.application.usecase;

import com.ecommerce.price_service.application.dto.ApplicablePriceResponseDTO;
import com.ecommerce.price_service.application.dto.ProductDTO;
import com.ecommerce.price_service.infrastructure.mappers.PriceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetPriceUseCaseTest {

    public static final String BRAND_ZARA = "ZARA";
    public static final String PRODUCT = "3090/470";
    @Autowired
    private GetPriceUseCase getPriceUseCase;

    @Autowired
    private PriceMapper priceMapper;

    private ProductDTO productDTO = new ProductDTO("CAMISA 100 % LINO", PRODUCT);

    @Test
    public void testGetPriceAt10amOn14th() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String priceListName = "Price List 1";
        ApplicablePriceResponseDTO expectedPrice = new ApplicablePriceResponseDTO(
                BRAND_ZARA,
                date.minusHours(10),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                priceListName,
                productDTO,
                new BigDecimal("35.50")
        );

        Mono<ApplicablePriceResponseDTO> actualPriceMono = getPriceUseCase.execute(date, PRODUCT, BRAND_ZARA);

        assertEquals(expectedPrice, actualPriceMono.block());
    }

    @Test
    public void testGetPriceAt4pmOn14th() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        String priceListName = "Price List 2";
        ApplicablePriceResponseDTO expectedPrice = new ApplicablePriceResponseDTO(
                BRAND_ZARA,
                date.minusHours(1),
                date.plusHours(2).plusMinutes(30),
                priceListName,
                productDTO,
                new BigDecimal("25.45")
        );

        Mono<ApplicablePriceResponseDTO> actualPriceMono = getPriceUseCase.execute(date, PRODUCT, BRAND_ZARA);

        assertEquals(expectedPrice, actualPriceMono.block());
    }

    @Test
    public void testGetPriceAt9pmOn14th() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        String priceListName = "Price List 1";
        ApplicablePriceResponseDTO expectedPrice = new ApplicablePriceResponseDTO(
                BRAND_ZARA,
                date.minusHours(21),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                priceListName,
                productDTO,
                new BigDecimal("35.50")
        );

        Mono<ApplicablePriceResponseDTO> actualPriceMono = getPriceUseCase.execute(date, PRODUCT, BRAND_ZARA);

        assertEquals(expectedPrice, actualPriceMono.block());
    }

    @Test
    public void testGetPriceAt10amOn15th() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0, 0);

        String priceListName = "Price List 3";
        ApplicablePriceResponseDTO expectedPrice = new ApplicablePriceResponseDTO(
                BRAND_ZARA,
                date.minusHours(10),
                date.plusHours(1),
                priceListName,
                productDTO,
                new BigDecimal("30.50")
        );

        Mono<ApplicablePriceResponseDTO> actualPriceMono = getPriceUseCase.execute(date, PRODUCT, BRAND_ZARA);

        assertEquals(expectedPrice, actualPriceMono.block());
    }

    @Test
    public void testGetPriceAt9pmOn16th() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        String priceListName = "Price List 4";
        ApplicablePriceResponseDTO expectedPrice = new ApplicablePriceResponseDTO(
                BRAND_ZARA,
                LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                priceListName,
                productDTO,
                new BigDecimal("38.95")
        );

        Mono<ApplicablePriceResponseDTO> actualPriceMono = getPriceUseCase.execute(date, PRODUCT, BRAND_ZARA);

        assertEquals(expectedPrice, actualPriceMono.block());
    }
}
