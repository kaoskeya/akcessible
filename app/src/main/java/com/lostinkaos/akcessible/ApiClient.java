package com.lostinkaos.akcessible;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by keya on 29/10/15.
 */
public class ApiClient {

    private static final String BASE_API_URL = "http://api.reverieinc.com/localization";

    public static ApiInterface apiService;
    public static ApiInterface getClient() {
        if( apiService == null ) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(BASE_API_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            apiService = restAdapter.create(ApiInterface.class);
        }
        return apiService;
    }

    public interface ApiInterface {

        @POST("/localizeJSON")
        void translate(
                @Body JsonObject data,
                Callback<Localise> callback
        );
    }
}
