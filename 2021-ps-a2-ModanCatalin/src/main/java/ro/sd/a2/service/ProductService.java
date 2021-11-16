package ro.sd.a2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.entity.Product;
import ro.sd.a2.factory.ProductFactory;
import ro.sd.a2.service.mapper.ProductMapper;
import ro.sd.a2.service.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService
{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public String createProduct(ProductDto productDto)
    {

        Product product = ProductFactory.generateProduct();

        productMapper.productMap(productDto, product);

        productRepository.save(product);

        try
        {

        } catch (Exception e)
        {
            throw new ArithmeticException("ceva");
        }
        log.info("Successfully created product " + product.getProductName() + "");
        return null;

    }

    public List<ProductDto> getAllProduct()
    {


        return productRepository.findAll().stream().map(productMapper::mapProduct).collect(Collectors.toList());


    }



    public String deleteByName(ProductDto productDto)
    {
        if (productRepository.deleteByProductName(productDto.getProductName()) < 1)
        {
            return "product not found";
        }

        return "product deleted";
    }

    public String updateProduct(ProductDto productDto)
    {

        Optional<Product> oldProduct = Optional.ofNullable(productRepository.findByProductName(productDto.getProductName()));

        if (oldProduct.isPresent())
        {
            productMapper.productMap(productDto, oldProduct.get());
            productRepository.save(oldProduct.get());
            return "product updated successfully";
        }

        return "product not found";

    }

}
