package org.edb.main;

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

}
