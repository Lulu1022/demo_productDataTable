package lulu.com.demo_productdatatable.repository;

import lulu.com.demo_productdatatable.entity.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPictureRepository extends JpaRepository<ProductPicture, Integer> {
    List<ProductPicture> findByProductId(Integer productId);
}
