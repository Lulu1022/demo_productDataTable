package lulu.com.demo_productdatatable.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int productId;
    private String productName;
    private int price;
    private int stock;
    private Integer productType; // 0:下架 1:上架

    private String picture; // 來自 product_pictures 表
}
