package lulu.com.demo_productdatatable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "products", schema = "demo_productdatatable")
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Column(name = "product_content")
    private String productContent;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "product_spec", length = 100)
    private String productSpec;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "updated_time")
    private Instant updatedTime;

    @Column(name = "product_type")
    private Integer productType;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "address")
    private String address;

}