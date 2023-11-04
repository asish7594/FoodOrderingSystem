package test.execution;

import test.dto.Item;
import test.dto.RequestBody;
import test.dto.Restaurant;
import test.service.RestaurantService;

import java.util.Set;

public class PlaceOrder implements Execution {
    @Override
    public boolean validateRequest(RequestBody requestBody) {
        if (!RestaurantService.restaurantList.containsKey(requestBody.getOrderDetails().getRestaurantId())) {
            return false;
        }

        return true;
    }

    @Override
    public String executeRequest(RequestBody requestBody) {
        Restaurant restaurant = RestaurantService.restaurantList.get(requestBody.getOrderDetails().getRestaurantId());

        Set<String> orderedItems = requestBody.getOrderDetails().getItemNames();

        if (orderedItems.size() <= restaurant.getProcessingCapacity()) {

            long maxTime = Integer.MIN_VALUE;
            for (String s : orderedItems) {
                boolean flag = false;
                for (Item item : restaurant.getItemList()) {
                    if(item.getName().equalsIgnoreCase(s)) {
                        if(item.getPreparationTime() > maxTime) {
                            maxTime = item.getPreparationTime();
                        }
                        flag = true;
                    }
                }

                if(!flag) {
                    return "Food not found !!!";
                }
            }

            restaurant.setProcessingCapacity(restaurant.getProcessingCapacity() - orderedItems.size());

            RestaurantService.restaurantList.put(restaurant.getId(), restaurant);

            return "Order Placed Successfully";

        } else {
            return "Limited processing power !! Try again after sometime";
        }
    }
}
