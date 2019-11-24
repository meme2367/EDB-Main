package org.edb.main.network;

import com.google.gson.JsonObject;
import org.edb.main.*;
import org.edb.main.network.post.postLoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestAPIRequester  implements ServerRequester {

    ServerResponseHandler serverResponseHandler;

    public RestAPIRequester(ServerResponseHandler serverResponseHandler) {
        this.serverResponseHandler = serverResponseHandler;
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

                        serverResponseHandler.handleLoginResponse(id, tempToken);
//                        실패시 그 결과를 UI에 주려면, 위 메소드 인터페이스가 status도 받아들일 수 있도록.
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
