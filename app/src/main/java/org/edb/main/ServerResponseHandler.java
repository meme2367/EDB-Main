package org.edb.main;

import org.edb.main.model.tempPlugin;
import org.edb.main.network.JsonConverter;

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

    // load plugin user list
    public void handleUserPluginResponse(ArrayList<tempPlugin> data){uiManipulator.onResponseUserPlugins(data);}
    // load Available plugin  list
    public void handleAvailablePluginResponse(ArrayList<tempPlugin> data) { uiManipulator.onResponseAvailablePlugins(data);}

    public void handlePluginDetailsResponse(int pluginIdx, ArrayList<tempPlugin> data) {
        uiManipulator.onResponsePluginDetails(pluginIdx, data);
    }

    //serverResponseHandler.handlePostUserPluginResponse(sucessful,jsonConverter);
    //post configuration
    public void handlePostUserPluginResponse(int pluginIdx, JsonConverter jsonConverter){

        uiManipulator.onPostUserPlugin(pluginIdx,jsonConverter);
    }
}
