package com.ecommerce.price_service.infrastructure.mappers;
import com.ecommerce.price_service.application.dto.ApplicablePriceResponseDTO;
import com.ecommerce.price_service.application.dto.ProductDTO;
import com.ecommerce.price_service.data.jpa.entities.PriceEntity;
import com.ecommerce.price_service.domain.model.Brand;
import com.ecommerce.price_service.domain.model.Price;
import com.ecommerce.price_service.domain.model.PriceList;
import com.ecommerce.price_service.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    @Autowired
    BrandMapper brandMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    PriceListMapper priceListMapper;

    public Price toDomain(PriceEntity entity) {

        Brand brand = brandMapper.toDomain(entity.getBrand());
        Product product = productMapper.toDomain(entity.getProduct());
        PriceList priceList = priceListMapper.toDomain(entity.getPriceList());

        return new Price(
             brand,
             product,
             priceList,
             entity.getStartDate(),
             entity.getEndDate(),
             entity.getPriority(),
             entity.getPrice(),
             entity.getCurr()
        );
    }

    public ApplicablePriceResponseDTO toApplicablePriceDTO(Price price) {

        String brandName = price.getBrand().getName();
        ProductDTO productDTO = productMapper.toDTO(price.getProduct());
        String priceList = price.getPriceList().getDescription();

        return new ApplicablePriceResponseDTO(
               brandName,
               price.getStartDate(),
               price.getEndDate(),
               priceList,
               productDTO,
                price.getPrice()
        );
    }
}
