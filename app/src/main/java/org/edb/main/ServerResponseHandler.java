package org.edb.main;

import java.util.ArrayList;

public class ServerResponseHandler {
    private UIManipulator uiManipulator;

    public ServerResponseHandler(UIManipulator uiManipulator) {
        this.uiManipulator = uiManipulator;
    }

    public void handleLoginResponse(boolean successful, String id, String token) {
        if(successful) {
            User.login(id,token);
            uiManipulator.onLoginSuccessful(id);

        }
        else uiManipulator.onLoginUnsuccessful();
    }

    public void handleAvailableExernalServiceResponse(ArrayList<tempExternalService> data) {
        uiManipulator.onResponseAvailableExternalServices(data);
    }

    public void handleUserExternalServiceResponse(ArrayList<tempExternalService> data) {
        uiManipulator.onResponseUserExternalServices(data);
    }

    public void handleExternalServiceDetailsResponse(int externalIdx, ArrayList<tempExternalServiceDetail> data) {
        uiManipulator.onResponseExternalServiceDetails(externalIdx, data);
    }
}
