package org.edb.main.network;

import org.edb.main.network.get.getAvailableExternalServiceResponse;
import org.edb.main.network.get.getExternalServiceListResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface externalServiceNetworkService {

    @GET("external/")
    Call<getExternalServiceListResponse> getExternalServiceListAPI(@Header("token") String token);

    @GET("external/available")
    Call<getAvailableExternalServiceResponse> getAvailableExternalServiceListAPI();



}
