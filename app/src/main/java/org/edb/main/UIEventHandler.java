package org.edb.main;

public class UIEventHandler {
    private ServerRequester serverRequester;
    private UIManipulator uiManipulator;

    public void setUiManipulator(UIManipulator uiManipulator) {
        this.uiManipulator = uiManipulator;
    }

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
//        data = user.getUserExternalServices
//        uiManipulator.뭐시기(data)
    }

    public void onExternalServiceDetailRequested(int externalIdx) {
        serverRequester.requestExternalServiceDetails(externalIdx);
//        data = user.getUserExternalDetails
//        uiManipulator.뭐시기(data)
    }

}
