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



    public void setStage(Stage dialog) {
        this.stage=stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void loginRequest(ActionEvent event)throws Exception{

        String tempId=idField.getText();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", tempId);
        jsonObject.addProperty("passwd", pwField.getText());


        Call<postLoginResponse> postLoginResponseCall =
                RestApiConnector.getUserNetworkService().postLoginAPI(jsonObject);

        postLoginResponseCall.enqueue(new Callback<postLoginResponse>() {

            private Stage stage;

            private Callback<postLoginResponse> init(Stage stage){
                this.stage=stage;
                return this;
            }

            @Override
            public void onResponse(Call<postLoginResponse> call, Response<postLoginResponse> response) {

                if (response.isSuccessful()) {
                    if(stage==null){
                        System.out.println("onResponse stage null\n");
                        System.out.println(this);
                    }

                    int status = response.body().getStatus();
                    if (status == 200) {
                        System.out.print("db connect success\n");
                        String tempToken=response.body().getToken();
                        User.login(tempId,tempToken);
                        Platform.runLater(()->{
                            Scene tempScene = BootApp.getPrimaryStage().getScene();
                            Button loginBtn = (Button) tempScene.lookup("#loginBtn");
                            Label userIdLbl = (Label)tempScene.lookup("#userIdLbl");
                            loginBtn.setDisable(true);
                            userIdLbl.setVisible(true);
                            userIdLbl.setText(tempId);
//                            this.stage.close();
                            /*
                            로그인 다이얼로그 닫는 이슈
                            null pointer exception...
                             */
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<postLoginResponse> call, Throwable throwable) {
                System.out.print("error\n");
                System.out.println(throwable);
            }
        }.init(stage));

    }

}
