package org.edb.main;

import com.google.gson.JsonObject;
import org.edb.main.network.JsonConverter;

public interface ServerRequester {

    public void requestAvailableExternalServices();
    public void requestUserExternalServices();
    public void requestExternalServiceDetails(int externalIdx);
    public void requestLogin(String id, String pw);

    public void requestUserPlugins();//load user plugin list
    public void requestAvailablePlugins();//load available plugin list
    public void requestPluginDetails(int pluginIdx);
    public void requestPostUserPlugin(int pluginIdx, JsonConverter jsonConverter);


}
