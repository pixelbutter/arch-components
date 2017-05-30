package com.archsample.data;

import com.archsample.data.model.Quote;
import com.archsample.data.remote.QuoteService;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteRepository {

    private QuoteService quoteService;

    public QuoteRepository() {
        this.quoteService = new Retrofit.Builder()
                .baseUrl("http://api.forismatic.com/api/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuoteService.class);
    }

    public void fetchRandomQuote(Callback<Quote> callback) {
        quoteService.getRandomQuote("getQuote", "json", "en").enqueue(callback);
    }
}
