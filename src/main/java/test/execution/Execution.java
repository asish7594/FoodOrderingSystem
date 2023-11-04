package test.execution;

import test.dto.RequestBody;

public interface Execution {
    boolean validateRequest(RequestBody requestBody);
    String executeRequest(RequestBody requestBody);
}
