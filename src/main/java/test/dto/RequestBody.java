package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBody {
    private Restaurant restaurantDetails;
    private OrderDetails orderDetails;
}
