package test.execution;

import test.dto.RequestBody;
import test.service.RestaurantService;

import java.util.Objects;

public class ListRestaurants implements Execution{
    @Override
    public boolean validateRequest(RequestBody requestBody) {
        return true;
    }

    @Override
    public String executeRequest(RequestBody requestBody) {
        if(Objects.isNull(RestaurantService.restaurantList) || RestaurantService.restaurantList.isEmpty()) {
            return "No restaurants found";
        }

        return RestaurantService.restaurantList.values().toString();
    }
}
