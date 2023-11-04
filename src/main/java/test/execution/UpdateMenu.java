package test.execution;

import test.dto.Item;
import test.dto.RequestBody;
import test.dto.Restaurant;
import test.service.RestaurantService;

import java.util.HashSet;
import java.util.Set;

public class UpdateMenu implements Execution{
    @Override
    public boolean validateRequest(RequestBody requestBody) {
        if(RestaurantService.restaurantList.containsKey(requestBody.getRestaurantDetails().getId())) {
            return true;
        }

        return false;
    }

    @Override
    public String executeRequest(RequestBody requestBody) {
        Restaurant restaurant = RestaurantService.restaurantList.get(requestBody.getRestaurantDetails().getId());

        Set<Item> updateItemList = requestBody.getRestaurantDetails().getItemList();

        Set<Item> newItemList = new HashSet<>();

        for(Item updateItem : updateItemList) {
            for(Item currentItem : restaurant.getItemList()) {
                if(currentItem.getName().equalsIgnoreCase(updateItem.getName())) {
                    newItemList.add(updateItem);
                } else {
                    newItemList.add(currentItem);
                }
            }
        }

        restaurant.setItemList(newItemList);

        RestaurantService.restaurantList.put(restaurant.getId(), restaurant);

        return "Menu updated successfully";
    }
}
