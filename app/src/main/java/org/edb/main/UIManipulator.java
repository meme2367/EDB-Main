package org.edb.main;

import java.util.ArrayList;

public interface UIManipulator {
    void onLoginSuccessful(String id);

    void onLoginUnsuccessful();

    void onResponseAvailableExternalServices(ArrayList<ExternalService> data);

    void onResponseUserExternalServices(ArrayList<ExternalService> data);

    void onResponseExternalServiceDetails(int externalIdx, ArrayList<ExternalServiceDetail> data);
}
