package com.archsample.quote;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.archsample.R;
import com.archsample.data.QuoteRepository;
import com.archsample.data.model.Quote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteViewModel extends AndroidViewModel {

    private QuoteRepository quoteRepository;
    private final MutableLiveData<Quote> obsevableQuote;

    public final ObservableField<String> content;
    public final ObservableField<String> author;
    public final ObservableInt loadingVisibility;

    public QuoteViewModel(Application application) {
        super(application);
        this.quoteRepository = new QuoteRepository();
        this.obsevableQuote = new MutableLiveData<>();
        this.content = new ObservableField<>(null);
        this.author = new ObservableField<>(null);
        this.loadingVisibility = new ObservableInt(View.INVISIBLE);
    }

    public void onButtonClick(View view) {
        obsevableQuote.setValue(null);
        fetchQuote();
    }

    public void updateState() {
        // todo add network states
        Quote quote = obsevableQuote.getValue();
        Context context = getApplication().getApplicationContext();
        if (quote != null) {
            loadingVisibility.set(View.INVISIBLE);
            content.set(context.getString(R.string.quote_content, quote.getContent()));
            author.set(context.getString(R.string.said_by_author, quote.getAuthor()));
        } else {
            loadingVisibility.set(View.VISIBLE);
            content.set(context.getString(R.string.loading_quote));
            author.set(null);
        }
    }

    public void fetchQuote() {
        quoteRepository.fetchRandomQuote(new Callback<Quote>() {
            @Override
            public void onResponse(@NonNull Call<Quote> call, @NonNull Response<Quote> response) {
                // delay to make loading more obvious
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                obsevableQuote.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Quote> call, @NonNull Throwable t) {
                obsevableQuote.setValue(null);
            }
        });
    }

    public LiveData<Quote> getObservableQuote() {
        return obsevableQuote;
    }
}
