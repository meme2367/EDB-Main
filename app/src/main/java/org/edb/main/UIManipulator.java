package org.edb.main;

import org.edb.main.model.tempPlugin;

import java.util.ArrayList;

public interface UIManipulator {
    void onLoginSuccessful(String id);

    void onLoginUnsuccessful();

    void onResponseAvailableExternalServices(ArrayList<tempExternalService> data);

    void onResponseUserExternalServices(ArrayList<tempExternalService> data);

    void onResponseExternalServiceDetails(int externalIdx, ArrayList<tempExternalServiceDetail> data);

    void onResponseUserPlugins(ArrayList<tempPlugin> data);

    void onResponseAvailablePlugins(ArrayList<tempPlugin> data);

    void onResponsePluginDetails(int pluginIdx, ArrayList<tempPlugin> data);
}
