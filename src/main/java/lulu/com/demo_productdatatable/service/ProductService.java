package lulu.com.demo_productdatatable.service;

import lulu.com.demo_productdatatable.dto.ProductDTO;
import lulu.com.demo_productdatatable.entity.Product;
import lulu.com.demo_productdatatable.entity.ProductPicture;
import lulu.com.demo_productdatatable.repository.ProductPictureRepository;
import lulu.com.demo_productdatatable.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPictureRepository productPictureRepository;

    private ProductDTO convertToDTO(Product product) {
        // 查詢對應產品的圖片
        List<ProductPicture> pictures = productPictureRepository.findByProductId(product.getId());
        String pictureName = pictures.isEmpty() ? null : pictures.get(0).getPictureName(); // 假設只取第一張圖片

        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getStock(),
                pictureName
        );
    }


    public Map<String, Object> getData(int draw, int start, int length, String searchValue, Integer orderColumn, String orderDirection) {
        // 準備返回的 Map
        Map<String, Object> result = new HashMap<>();

        // 搜尋條件處理 (例如根據產品名稱進行篩選)
        List<Product> filteredProducts;
        if (searchValue != null && !searchValue.isEmpty()) {
            filteredProducts = productRepository.findByProductNameContaining(searchValue);
        } else {
            filteredProducts = productRepository.findAll();
        }

        // 排序邏輯處理
        if (orderColumn != null && orderDirection != null) {
            filteredProducts = filteredProducts.stream().sorted((p1, p2) -> {
                switch (orderColumn) {
                    case 1: // 排序依據產品編號
                        return "asc".equalsIgnoreCase(orderDirection) ?
                                p1.getId().compareTo(p2.getId()) :
                                p2.getId().compareTo(p1.getId());
                    case 3: // 排序依據價格
                        return "asc".equalsIgnoreCase(orderDirection) ?
                                p1.getPrice().compareTo(p2.getPrice()) :
                                p2.getPrice().compareTo(p1.getPrice());
                    case 4: // 排序依據庫存
                        return "asc".equalsIgnoreCase(orderDirection) ?
                                p1.getStock().compareTo(p2.getStock()) :
                                p2.getStock().compareTo(p1.getStock());
                    default:
                        return 0;
                }
            }).collect(Collectors.toList());
        }

        // 分頁處理
        int end = Math.min(start + length, filteredProducts.size());
        List<Product> paginatedProducts = filteredProducts.subList(start, end);

        // 將 Product 轉換成 ProductDTO
        List<ProductDTO> productDTOs = paginatedProducts.stream().map(this::convertToDTO).collect(Collectors.toList());

        // 組裝返回的 Map
        result.put("draw", draw);
        result.put("recordsTotal", filteredProducts.size());
        result.put("recordsFiltered", filteredProducts.size());
        result.put("data", productDTOs);

        return result;
    }
}
