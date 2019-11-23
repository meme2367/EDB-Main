package org.edb.main.UI;

import org.edb.main.UIManipulator;

public class FXManipulator implements UIManipulator {
    private MainUIController mainUIController;

    @Override
    public void onResponseLogin(String id) {
        mainUIController.closeLoginDialog();
        mainUIController.setUILoggedIn(id);
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }
}
