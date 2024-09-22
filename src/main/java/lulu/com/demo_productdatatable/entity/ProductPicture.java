package lulu.com.demo_productdatatable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_picture", schema = "demo_productdatatable")
public class ProductPicture {
    @Id
    @Column(name = "picture_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "picture_name", length = 100)
    private String pictureName;

    @Column(name = "product")
    private byte[] product1;

}