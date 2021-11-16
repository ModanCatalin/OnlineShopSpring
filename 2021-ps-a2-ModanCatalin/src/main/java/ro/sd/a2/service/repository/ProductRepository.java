package ro.sd.a2.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>
{

    Product findByProductName(String name);

    long deleteByProductName(String name);
}
