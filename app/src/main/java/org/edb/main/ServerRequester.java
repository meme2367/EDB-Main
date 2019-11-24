package org.edb.main;

import java.util.ArrayList;

public interface ServerRequester {

    public void requestAvailableExternalServices();
    public void requestUserExternalServices();
    public void requestExternalServiceDetails(int externalIdx);
    public void requestLogin(String id, String pw);

}
