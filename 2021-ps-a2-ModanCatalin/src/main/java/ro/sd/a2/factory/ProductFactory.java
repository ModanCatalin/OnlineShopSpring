package ro.sd.a2.factory;

import ro.sd.a2.entity.Product;

import java.util.Date;
import java.util.UUID;

public class ProductFactory
{

    public static Product generateProduct()
    {

        Product product = new Product();
        product.setCreationDate(new Date());

        product.setProductId(UUID.randomUUID().toString());

        product.setPrice(product.getPrice());
        product.setStock(product.getStock());

        return product;
    }
}
