package lulu.com.demo_productdatatable.repository;

import lulu.com.demo_productdatatable.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // 查詢上架且產品名稱符合的產品
    List<Product> findByProductNameAndProductType(String productName, Integer productType);

    // 查詢已上架的產品
    @Query("SELECT p FROM Product p WHERE p.productType = 1")
    List<Product> findAllAvailableProducts();
}
