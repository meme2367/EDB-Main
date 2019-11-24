package org.edb.main.UI;

import javafx.application.Platform;
import org.edb.main.UIManipulator;
import org.edb.main.tempExternalService;

import java.util.ArrayList;

public class FXManipulator implements UIManipulator {
    private MainUIController mainUIController;
    private LoginDialogController loginDialogController;
    private ImprovedAvailableExternalServiceListController availableExternalServicelListController;
    private ImprovedUserExternalServiceListController userExternalServicelListController;

    @Override
    public void onLoginSuccessful(String id) {
        Platform.runLater(()->{
            mainUIController.closeLoginDialog();
            mainUIController.setUILoggedIn(id);
        });
    }

    @Override
    public void onLoginUnsuccessful() {
        Platform.runLater(()->{
//            loginDialogController에 대해 문제상황 알리기
        });
    }

    @Override
    public void onResponseAvailableExternalServices(ArrayList<tempExternalService> data) {
        Platform.runLater(()->{
           availableExternalServicelListController.handleExternalServiceResponse(data);
        });
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public <T> void setLoginDialogController(LoginDialogController loginDialogController) {
        this.loginDialogController = loginDialogController;
    }

    public <T> void setAvailableExternalServicelListController(ImprovedAvailableExternalServiceListController availableExternalServicelListController) {
        this.availableExternalServicelListController = availableExternalServicelListController;
    }

    public <T> void setUserExternalServicelListController(ImprovedUserExternalServiceListController userExternalServicelListController) {
        this.userExternalServicelListController = userExternalServicelListController;
    }

}
