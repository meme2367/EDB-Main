package org.edb.main;

import java.util.ArrayList;

public class ServerResponseHandler {
    private UIManipulator uiManipulator;

    private ServerRequester serverRequester;

    public ServerResponseHandler(UIManipulator uiManipulator) {
        this.uiManipulator = uiManipulator;
    }

    public void setServerRequester(ServerRequester serverRequester) {
        this.serverRequester = serverRequester;
    }

    public void handleLoginResponse(boolean successful, String id, String token) {
        if(successful) {
            User.login(id,token);
            User user = User.getUser();

            serverRequester.requestUserExternalServices();
//            serverRequester.requestPluginConfigs();
            uiManipulator.onLoginSuccessful(id);
        }
        else uiManipulator.onLoginUnsuccessful();
    }

    public void handleAvailableExernalServiceResponse(ArrayList<ExternalService> data) {
        uiManipulator.onResponseAvailableExternalServices(data);
    }

    public void handleUserExternalServiceResponse(ArrayList<ExternalService> data) {
//        User.getUser().loadExternalService(data);
//        for i : data.size()
//        serverRequester.requestExternalServiceDetails(externalIdx);
        uiManipulator.onResponseUserExternalServices(data);
    }

    public void handleExternalServiceDetailsResponse(int externalIdx, ArrayList<ExternalServiceDetail> data) {
//        user.loadExternalServiceDetails(externalIdx)
        uiManipulator.onResponseExternalServiceDetails(externalIdx, data);
    }

//    public void handlePluginConfigsResponse(ArrayList<PluginConfig> data){
//        User.getUser.loadPluginConfigs(data);
//    }

}
