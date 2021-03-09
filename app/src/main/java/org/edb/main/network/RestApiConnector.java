package org.edb.main.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiConnector {

    private static final String BASE_URL = "http://localhost:3000/api/";

    public static userNetworkService getUserNetworkService(){
        return createRetrofitInstance().create(userNetworkService.class);
    }

    public static externalServiceNetworkService getExternalServiceNetworkService(){
        return createRetrofitInstance().create(externalServiceNetworkService.class);
    }

    public static pluginNetworkService getPluginService(){
        return createRetrofitInstance().create(pluginNetworkService.class);
    }

    private static Retrofit createRetrofitInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
