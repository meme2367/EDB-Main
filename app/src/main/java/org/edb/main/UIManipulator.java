package org.edb.main;

import java.util.ArrayList;

public interface UIManipulator {
    void onLoginSuccessful(String id);

    void onLoginUnsuccessful();

    void onResponseAvailableExternalServices(ArrayList<tempExternalService> data);

    void onResponseUserExternalServices(ArrayList<tempExternalService> data);

    void onResponseExternalServiceDetails(int externalIdx, ArrayList<tempExternalServiceDetail> data);
}
