package org.edb.main.UI;

import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.edb.main.Authorization;
import org.edb.main.UIEventHandler;
import org.edb.main.User;
import org.edb.main.network.RestApiConnector;
import org.edb.main.network.post.postLoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        uiEventHandler.OnClickLoginBtn(idField.getText(),pwField.getText());
    }

}
