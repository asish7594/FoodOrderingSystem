package test.execution;

import test.dto.RequestBody;

public class NoExecution implements Execution{
    @Override
    public boolean validateRequest(RequestBody requestBody) {
        return false;
    }

    @Override
    public String executeRequest(RequestBody requestBody) {
        return "Invalid Request";
    }
}
