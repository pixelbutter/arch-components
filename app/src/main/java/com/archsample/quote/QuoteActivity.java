package com.archsample.quote;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.archsample.R;
import com.archsample.databinding.QuoteActivityBinding;

public class QuoteActivity extends LifecycleActivity {

    private QuoteActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_activity);
        binding = DataBindingUtil.setContentView(this, R.layout.quote_activity);
        final QuoteViewModel viewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);
        binding.setViewmodel(viewModel);
        subscribe(viewModel);
    }

    private void subscribe(final QuoteViewModel viewModel) {
        viewModel.getObservableQuote().observe(this, quote -> {
            viewModel.updateState();
        });
    }
}
