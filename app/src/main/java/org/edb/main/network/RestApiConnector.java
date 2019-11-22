package org.edb.main.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiConnector {

    private static final String BASE_URL = "http://localhost:3000/api/";

    public static userNetworkService getUserNetworkService(){
        return getInstance().create(userNetworkService.class);
    }
    //사용시
    //https://galid1.tistory.com/617
    //Call<getUserInfoResponse> getUserInfo = RetrofitClient.getUserApiService().getUserInfoAPI(idx);
    //반환 값loginResponse    //userNetworkService useNetworkService

    public static externalServiceNetworkService getExternalServiceNetworkService(){
        return getInstance().create(externalServiceNetworkService.class);
    }

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
