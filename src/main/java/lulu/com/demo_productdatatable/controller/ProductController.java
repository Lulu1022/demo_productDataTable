package lulu.com.demo_productdatatable.controller;

import lulu.com.demo_productdatatable.dto.ProductDTO;
import lulu.com.demo_productdatatable.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<Map<String, Object>> getData(
            @RequestParam int draw,
            @RequestParam int start,
            @RequestParam int length,
            @RequestParam(name = "search[value]", required = false) String searchValue,
            @RequestParam(name = "order[0][column]", required = false) Integer orderColumn,
            @RequestParam(name = "order[0][dir]", required = false) String orderDirection
    ){
        Map<String, Object> result = productService.getData(draw, start, length, searchValue, orderColumn, orderDirection);
        // 返回帶有結果的 ResponseEntity
        return ResponseEntity.ok(result);
    }
}