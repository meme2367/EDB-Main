package org.edb.main.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.edb.main.User;

public class mainUserinfoSubController   {

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lblUerId;
//extends 부모
    private String token = User.getUser().getToken();


    public void lblUserId(){
        //lblUerId.setText("test");
        System.out.print("test");
        System.out.print("userinfo");
    }
}
