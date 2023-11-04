package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import test.dto.RequestBody;
import test.dto.Restaurant;
import test.enums.RequestType;
import test.execution.Execution;
import test.execution.ExecutionFactory;

import java.util.Map;

@Service
public class RestaurantService {
    @Autowired
    ExecutionFactory executionFactory;

    public static Map<String, Restaurant> restaurantList;

    public String processRequest(RequestType requestType, RequestBody requestBody) {
        executionFactory = new ExecutionFactory();
        Execution execution = executionFactory.getExecution(requestType);

        if(execution.validateRequest(requestBody)) {
            return execution.executeRequest(requestBody);
        } else {
            return "Invalid request";
        }
    }
}
