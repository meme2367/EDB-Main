package org.edb.main.UI;

import javafx.application.Platform;
import org.edb.main.UIManipulator;

public class FXManipulator implements UIManipulator {
    private MainUIController mainUIController;
    private LoginDialogController loginDialogController;

    @Override
    public void onResponseLogin(String id) {
        Platform.runLater(()->{
            mainUIController.closeLoginDialog();
            mainUIController.setUILoggedIn(id);
        });
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public <T> void setLoginDialogController(LoginDialogController loginDialogController) {
        this.loginDialogController = loginDialogController;
    }

    public LoginDialogController getLoginUIController() {
        return loginDialogController;
    }
}
