package lulu.com.demo_productdatatable.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vendors", schema = "demo_productdatatable")
public class Vendor {
    @Id
    @Column(name = "vendor_id", nullable = false)
    private Integer id;

    //TODO [JPA Buddy] generate columns from DB
}