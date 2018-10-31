package com.smartcontainer.smartcontainermvvm.inventory_status;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.smartcontainer.smartcontainermvvm.R;
import com.smartcontainer.smartcontainermvvm.data.DataSourceRepository;
import com.smartcontainer.smartcontainermvvm.data.model.StatusResponse;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {
    private StatusViewModel mStatusViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mStatusViewModel.onProduct();
    }

    private void init() {

        StatusViewModelFactory factory = new StatusViewModelFactory(new CompositeDisposable(), new DataSourceRepository());
        mStatusViewModel = ViewModelProviders.of(this, factory).get(StatusViewModel.class);
        mStatusViewModel.start();
        observe();
    }

    private void observe() {
        mStatusViewModel.ProductResponse().observe(this, this::renderProductResult);
    }

    private void renderProductResult(StatusResponse statusResponse) {
        Toast.makeText(this, statusResponse.data.toString(), Toast.LENGTH_SHORT).show();
    }
}
