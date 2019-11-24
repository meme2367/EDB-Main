package org.edb.main;

public class UIEventHandler {
    private ServerRequester serverRequester;

    public UIEventHandler(ServerRequester serverRequester) {
        this.serverRequester = serverRequester;
    }

    public void OnClickLoginBtn(String id, String pw) {
        serverRequester.requestLogin(id,pw);
    }
}
