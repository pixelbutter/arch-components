package com.archsample.data.remote;

import com.archsample.data.model.Quote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteService {
    @GET("?")
    Call<Quote> getRandomQuote(@Query("method") String method,
                               @Query("format") String format,
                               @Query("lang") String lang);
}