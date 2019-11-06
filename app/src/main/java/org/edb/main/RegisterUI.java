package org.edb.main;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import org.edb.main.network.post.postLoginResponse;
import org.edb.main.network.restApiConnector;
import org.edb.main.network.userNetworkService;

import java.io.IOException;


public class RegisterUI {
    //logincontroller 역할
    @FXML
    private TextField txtUserId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button loginButton;


    //id,pw 입력 후 로그인 버튼
// REST API 호출
    public void login(ActionEvent event) throws Exception {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", txtUserId.getText());
        jsonObject.addProperty("passwd", txtPassword.getText());


        Call<postLoginResponse> postLoginResponseCall =
                restApiConnector.getUserNetworkService().postLoginAPI(jsonObject);

        postLoginResponseCall.enqueue(new Callback<postLoginResponse>() {


            @Override
            public void onResponse(Call<postLoginResponse> call, Response<postLoginResponse> response) {

                if (response.isSuccessful()) {
                    int status = response.body().getStatus();
                    if (status == 200) {


                        System.out.print("db connect success\n");
                        //token사용시
                        //System.out.println(response.body().getToken());

                        //로그인 성공시 메인화면+회원정보
                        //response.body().getToken();
                        Authorization.setToken(response.body().getToken());
                        // mainUserinfoSubController 와sceneMainAfterLogin.fxml띄우기




                    }
                }
            }

            @Override
            public void onFailure(Call<postLoginResponse> call, Throwable throwable) {
                System.out.print("error\n");
                System.out.println(throwable);
            }
        });

    }

};

