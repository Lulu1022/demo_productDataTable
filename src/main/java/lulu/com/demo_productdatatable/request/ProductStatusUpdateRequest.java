package lulu.com.demo_productdatatable.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatusUpdateRequest {
    private List<Integer> productIds;
    private Integer status;
}
