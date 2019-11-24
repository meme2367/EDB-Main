package org.edb.main.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.edb.main.UIEventHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginDialogController implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private PasswordField pwField;
    @FXML
    private Button loginBtn;

    private Stage primaryStage;

    private Stage stage;

    private UIEventHandler uiEventHandler;

    public void setUiEventHandler(UIEventHandler uiEventHandler) {
        this.uiEventHandler = uiEventHandler;
    }

    public void setStage(Stage dialog) {
        this.stage=stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void closeDialog(){
        stage.close();
    }

    public void onClickLoginBtn(ActionEvent event)throws Exception{
        uiEventHandler.onClickLoginBtn(idField.getText(),pwField.getText());
    }

}
