package com.example.user.lkdjf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetInterfaceB {
    @GET("/v1/book/{symbol}")
    Call<Bitfinex> getData(@Path("symbol") String symbol);
}