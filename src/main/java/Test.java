import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import test.dto.*;
import test.enums.RequestType;
import test.service.RestaurantService;

import java.util.*;

@SpringBootApplication
@ComponentScan
public class Test {
    @Autowired
    static
    RestaurantService restaurantService;

    public static void main(String[] args) {
        restaurantService = new RestaurantService();
        RestaurantService.restaurantList = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Select option :");
            System.out.println("1. ONBOARD RESTAURANT");
            System.out.println("2. LIST ALL RESTAURANTS");
            System.out.println("3. UPDATE MENU");
            System.out.println("4. PLACE ORDER");

            int option = sc.nextInt();
            RequestBody requestBody = new RequestBody();
            switch (option) {
                case 1 : {
                    Restaurant restaurant = new Restaurant();
                    System.out.println("Enter the following details : ");
                    System.out.println("Name : ");
                    restaurant.setName(sc.next());
                    restaurant.setId(restaurant.getName());

                    Location location = new Location();
                    System.out.println("Address : ");
                    location.setAddress(sc.next());
                    System.out.println("Distance : ");
                    location.setDistance(sc.nextInt());


                    System.out.println("Processing capacity : ");
                    restaurant.setProcessingCapacity(sc.nextInt());

                    System.out.println("Item details : ");

                    String action;
                    boolean flag = true;

                    Set<Item> itemList = new HashSet<>();
                    while(flag) {

                        Item item = new Item();
                        System.out.println("Item Name : ");
                        item.setName(sc.next());

                        System.out.println("Item Preparation Time (in minutes): ");
                        item.setPreparationTime(sc.nextLong() * 60 * 1000);
                        item.setAvailable(true);

                        itemList.add(item);

                        System.out.println("To exit press X, otherwise press C to continue");

                        action = sc.next();

                        if(action.equals("X")) {
                            flag = false;
                        }
                    }
                    restaurant.setItemList(itemList);

                    requestBody.setRestaurantDetails(restaurant);
                    System.out.println(restaurantService.processRequest(RequestType.ONBOARD_RESTAURANT, requestBody));

                    break;
                }

                case 2 : {
                    System.out.println(restaurantService.processRequest(RequestType.LIST_RESTAURANTS, requestBody));
                    break;
                }

                case 3 : {
                    Restaurant restaurant = new Restaurant();
                    System.out.println("Enter the following details : ");
                    System.out.println("Restaurant Id : ");
                    restaurant.setId(sc.next());

                    Set<Item> itemList = new HashSet<>();

                    System.out.println("Item details : ");

                    String action;
                    boolean flag = true;
                    while(flag) {

                        Item item = new Item();
                        System.out.println("Item Name : ");
                        item.setName(sc.next());

                        System.out.println("Item Preparation Time (in minutes): ");
                        item.setPreparationTime(sc.nextLong() * 60 * 1000);

                        System.out.println("Add or Remove ?");
                        String addOrRemove = sc.next();

                        if(addOrRemove.equalsIgnoreCase("add")) {
                            item.setAvailable(true);
                        } else {
                            item.setAvailable(false);
                        }

                        itemList.add(item);

                        System.out.println("To exit press X, otherwise press C to continue");

                        action = sc.next();

                        if(action.equals("X")) {
                            flag = false;
                        }
                    }
                    restaurant.setItemList(itemList);

                    requestBody.setRestaurantDetails(restaurant);
                    System.out.println(restaurantService.processRequest(RequestType.UPDATE_MENU, requestBody));

                    break;
                }
                case 4 : {
                    OrderDetails orderDetails = new OrderDetails();
                    System.out.println("Enter your order: ");
                    System.out.println("Enter restaurant id : ");
                    orderDetails.setRestaurantId(sc.next());

                    System.out.println("Enter Items : ");

                    String action;
                    boolean flag = true;
                    Set<String> itemNames = new HashSet<>();
                    while(flag) {
                        System.out.println("Item Name : ");
                        itemNames.add(sc.next());

                        System.out.println("To exit press X, otherwise press C to continue");

                        action = sc.next();

                        if(action.equals("X")) {
                            flag = false;
                        }
                    }

                    orderDetails.setItemNames(itemNames);
                    requestBody.setOrderDetails(orderDetails);

                    System.out.println(restaurantService.processRequest(RequestType.PLACE_ORDER, requestBody));

                    break;
                }
            }
        }
    }
}
