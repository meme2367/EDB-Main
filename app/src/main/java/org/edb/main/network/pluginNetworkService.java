package org.edb.main.network;

import com.google.gson.JsonObject;
import org.edb.main.network.get.getAvailablePluginResponse;
import org.edb.main.network.get.getPluginDetailListResponse;
import org.edb.main.network.get.getPluginListResponse;
import org.edb.main.network.post.postPluginDetailResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface pluginNetworkService {

//load user plugin list
    @GET("locks/")
    Call<getPluginListResponse> getPluginListAPI(@Header("token") String token);

    @POST("locks/detail/{lockIdx}")
    Call<postPluginDetailResponse> postPluginDetailAPI(@Path("lockIdx") int externalDetailIdx ,
                                                       @Header("token") String token,
                                                       @Body() JsonObject body);
    //load AvailablePlugin list
    @GET("locks/available")
    Call<getAvailablePluginResponse> getAvailablePluginListAPI();

    //load detail plugin configuration
    @GET("locks/detail/{pluginIdx}")
    Call<getPluginDetailListResponse> getPluginDetailListAPI(@Path("pluginIdx") int pluginIdx,
                                                             @Header("token") String token);
}
