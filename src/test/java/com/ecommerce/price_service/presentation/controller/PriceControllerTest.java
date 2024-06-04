package com.ecommerce.price_service.presentation.controller;

import com.ecommerce.price_service.application.dto.ApplicablePriceResponseDTO;
import com.ecommerce.price_service.application.dto.ProductDTO;
import com.ecommerce.price_service.application.usecase.GetPriceUseCase;
import com.ecommerce.price_service.domain.repository.PriceRepository;
import com.ecommerce.price_service.infrastructure.mappers.PriceMapper;
import com.ecommerce.price_service.presentation.api.controller.PriceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@WebFluxTest(controllers = PriceController.class)
public class PriceControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private GetPriceUseCase getPriceUseCase;

    @MockBean
    private PriceRepository priceRepository;

    @MockBean
    private PriceMapper priceMapper;

    @Test
    public void testInvalidParameters() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/prices")
                        .queryParam("date", date.toString())
                        .queryParam("product", "")
                        .queryParam("brand", "ZARA")
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testPriceNotFound() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        given(priceRepository.findByProductIdAndBrandIdAndDate(anyString(), anyString(), any()))
                .willReturn(Flux.empty());

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/prices")
                        .queryParam("date", date.toString())
                        .queryParam("product", "non-existing-product")
                        .queryParam("brand", "ZARA")
                        .build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testSuccessResult() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        ProductDTO productDTO = new ProductDTO("CAMISA 100 % LINO", "3090/470");
        String priceListName = "Price List 1";
        ApplicablePriceResponseDTO expectedPrice = new ApplicablePriceResponseDTO(
                "ZARA",
                date.minusHours(10),
                date.plusMonths(6),
                priceListName,
                productDTO,
                new BigDecimal("35.50")
        );

        doReturn(Mono.just(expectedPrice))
                .when(getPriceUseCase)
                .execute(any(), anyString(), anyString());

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/prices")
                        .queryParam("date", date.toString())
                        .queryParam("product", "3090/470")
                        .queryParam("brand", "ZARA")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ApplicablePriceResponseDTO.class)
                .isEqualTo(expectedPrice);
    }
}
