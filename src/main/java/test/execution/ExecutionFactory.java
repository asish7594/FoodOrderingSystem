package test.execution;

import test.enums.RequestType;

public class ExecutionFactory {

    public Execution getExecution(RequestType requestType) {
        switch (requestType) {
            case ONBOARD_RESTAURANT:
                return new OnboardingRestaurant();
            case LIST_RESTAURANTS:
                return new ListRestaurants();
            case UPDATE_MENU:
                return new UpdateMenu();
            case PLACE_ORDER:
                return new PlaceOrder();
            default :
                return new NoExecution();
        }
    }
}
