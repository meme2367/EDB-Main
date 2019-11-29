package org.edb.main;

import org.edb.main.network.JsonConverter;

public class UIEventHandler {
    private ServerRequester serverRequester;

    public UIEventHandler(ServerRequester serverRequester) {
        this.serverRequester = serverRequester;
    }

    public void onClickLoginBtn(String id, String pw) {
        serverRequester.requestLogin(id,pw);
    }

    public void onAvailableExternalServiceListLoaded(){
        serverRequester.requestAvailableExternalServices();
    }

    public void onUserExternalServiceListLoaded(){
        serverRequester.requestUserExternalServices();
    }

    public void onExternalServiceDetailRequested(int externalIdx) {
        serverRequester.requestExternalServiceDetails(externalIdx);
    }

    public void onUserPluginListLoaded(){ serverRequester.requestUserPlugins();}

    public void onPostUserPlugin(int pluginIdx,JsonConverter jsonConverter) {
        //requestPostUserPlugin(int pluginIdx, JsonConverter jsonConverter);
        serverRequester.requestPostUserPlugin(pluginIdx, jsonConverter);
    }
}
