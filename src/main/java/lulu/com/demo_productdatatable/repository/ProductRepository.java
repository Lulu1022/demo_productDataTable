package lulu.com.demo_productdatatable.repository;

import lulu.com.demo_productdatatable.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // 透過產品名稱進行模糊搜尋
    List<Product> findByProductNameContaining(String productName);
}
