package lulu.com.demo_productdatatable.repository;

import lulu.com.demo_productdatatable.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    // 查詢上架或下架且產品名稱符合的產品
    @Query("select p from Product p where p.productName like %?1%  and p.productType = ?2")
    List<Product> findByProductNameAndProductType(String productName, Integer productType);

    // TODO: 模糊查詢其他欄未



    // 查詢上架或下架商品
    @Query("select p from Product p where p.productType = ?1")
    List<Product> findByProductType(Integer productType);


}
