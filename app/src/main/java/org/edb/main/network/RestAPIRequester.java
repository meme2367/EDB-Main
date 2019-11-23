package org.edb.main.network;

import com.google.gson.JsonObject;
import org.edb.main.*;
import org.edb.main.network.post.postLoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class RestAPIRequester  implements ServerRequester {

    ServerResponseHandler mainHandler;

    public void setMainHandler(ServerResponseHandler mainHandler) {
        this.mainHandler = mainHandler;
    }

    @Override
    public void requestAvailableExternalServices() {

    }

    @Override
    public void requestUserExternalServices() {

    }

    @Override
    public void requestExternalServiceDetails() {

    }

    @Override
    public void requestLogin(String id, String pw) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("passwd", pw);

        Call<postLoginResponse> postLoginResponseCall =
                RestApiConnector.getUserNetworkService().postLoginAPI(jsonObject);

        postLoginResponseCall.enqueue(new Callback<postLoginResponse>() {

            @Override
            public void onResponse(Call<postLoginResponse> call, Response<postLoginResponse> response) {

                if (response.isSuccessful()) {

                    int status = response.body().getStatus();
                    if (status == 200) {
                        System.out.print("db connect success\n");
                        String tempToken=response.body().getToken();

                        mainHandler.handleLoginResponse(id, tempToken);
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
}
