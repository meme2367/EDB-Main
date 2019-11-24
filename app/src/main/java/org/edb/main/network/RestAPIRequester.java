package org.edb.main.network;

import com.google.gson.JsonObject;
import org.edb.main.*;
import org.edb.main.UI.ImprovedAvailableExternalServiceListController;
import org.edb.main.network.get.getAvailableExternalServiceResponse;
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
        String token;
        try {
            token = User.getUser().getToken();
        }
        catch(RuntimeException runtimeException){
            token="dummy";
        }

        Call<getAvailableExternalServiceResponse> getAvailableExternalServiceResponseCall =
                RestApiConnector.getExternalServiceNetworkService().getAvailableExternalServiceListAPI();

        getAvailableExternalServiceResponseCall.enqueue(new Callback<getAvailableExternalServiceResponse>() {

            @Override
            public void onResponse(Call<getAvailableExternalServiceResponse> call, Response<getAvailableExternalServiceResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        int status = response.body().getStatus();
                        if (status == 200) {
                            System.out.print("\navilable service\n");

                            serverResponseHandler.handleAvailableExernalServiceResponse(response.body().getData());
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<getAvailableExternalServiceResponse> call, Throwable throwable) {
                System.out.print("error\n");
                System.out.println(throwable);
            }
        });

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

                boolean sucessful=response.isSuccessful();
                if (sucessful) {
                    System.out.print("db connect success\n");
                    String tempToken=response.body().getToken();
                    User.login(id,tempToken);
                }
                serverResponseHandler.handleLoginResponse(sucessful,id);
            }

            @Override
            public void onFailure(Call<postLoginResponse> call, Throwable throwable) {
                System.out.print("error\n");
                System.out.println(throwable);
            }
        });

    }
}
