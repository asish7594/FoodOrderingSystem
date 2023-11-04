package test.execution;

import test.dto.RequestBody;
import test.service.RestaurantService;

import java.util.ArrayList;
import java.util.Objects;

public class OnboardingRestaurant implements Execution{

    @Override
    public boolean validateRequest(RequestBody requestBody) {
        return true;
    }

    @Override
    public String executeRequest(RequestBody requestBody) {
        RestaurantService.restaurantList.put(requestBody.getRestaurantDetails().getId(), requestBody.getRestaurantDetails());
        return "Restaurant added successfully";
    }
}
