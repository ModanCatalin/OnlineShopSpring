package ro.sd.a2.service.mapper;

import org.springframework.stereotype.Component;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.entity.Product;

@Component
public class ProductMapper
{

    public void productMap(ProductDto productDto, Product product)
    {
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
    }


    public ProductDto mapProduct(Product product)
    {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());

        return productDto;
    }
}
