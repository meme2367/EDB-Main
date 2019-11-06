package org.edb.main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
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


public class RegisterUI {
    @FXML
    private TextField id;

    @FXML
    private PasswordField passwd;

    @FXML
    private Button loginButton;

    @FXML
    private Label lblStatus;



//id,pw 입력 후 로그인 버튼
// REST API 호출
    public void login(ActionEvent event) throws Exception {
//   //Call<getUserInfoResponse> getUserInfo = RetrofitClient.getUserApiService().getUserInfoAPI(idx);
//    //반환 값loginResponse    //userNetworkService useNetworkService
    //json타입의 body넣기


        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id",id);
        jsonObject.put("passwd",passwd);

//         String json = gson.toJson(jsonObject);





        Call<postLoginResponse> postLoginResponseCall =
                restApiConnector.getUserNetworkService().postLoginAPI(jsonObject);

        postLoginResponseCall.enqueue(new Callback<postLoginResponse>() {
            @Override
            public void onResponse(Call<postLoginResponse> call, Response<postLoginResponse> response) {
                System.out.print("db connect success\n");
                System.out.println(response);
            }

            @Override
            public void onFailure(Call<postLoginResponse> call, Throwable throwable) {
                System.out.print("error\n");
                System.out.println(throwable);
            }
        });

    }


    //id,pw입력

    //입력한 id,pw를 가지고 맞는지 체크

    //
}
