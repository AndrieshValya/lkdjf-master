package com.example.user.lkdjf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetInterface {
    @GET("/api/v2/ticker/{currency_pair}")
    Call<Bitstamp> getData(@Path("currency_pair") String currency_pair);
}