package org.edb.main;

import org.edb.main.model.PluginModel;
import org.edb.main.network.JsonConverter;

import java.util.ArrayList;

public interface UIManipulator {
    void onLoginSuccessful(String id);

    void onLoginUnsuccessful();

    void onResponseAvailableExternalServices(ArrayList<ExternalService> data);

    void onResponseUserExternalServices(Iterable<ExternalService> data);

    void onResponseExternalServiceDetails(int externalIdx, ArrayList<ExternalServiceDetail> data);

    void onResponseExternalServiceDetail(int externalIdx, Iterable<ExternalServiceDetail> externalServiceDetail);

    void onResponseUserPlugins(ArrayList<PluginModel> data);

    void onResponseAvailablePlugins(ArrayList<PluginModel> data);

    void onResponsePluginDetails(int pluginIdx, ArrayList<PluginModel> data);

    void onPostUserPlugin(int pluginIdx,JsonConverter jsonConverter);
}
