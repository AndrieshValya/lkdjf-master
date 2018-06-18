package com.example.user.lkdjf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by student on 16.06.18.
 */

public interface GetInterfaceC {
    @GET("/api/ticker/{symbol1}/{symbol2}")
    Call<CEX> getData(@Path("symbol1") String symbol1, @Path("symbol2") String symbol2);
}
