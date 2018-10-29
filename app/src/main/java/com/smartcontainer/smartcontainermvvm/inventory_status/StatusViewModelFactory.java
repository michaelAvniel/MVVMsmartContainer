package com.smartcontainer.smartcontainermvvm.inventory_status;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.smartcontainer.smartcontainermvvm.data.StatusDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class StatusViewModelFactory implements ViewModelProvider.Factory {
    private final CompositeDisposable mDisposables;
    private final StatusDataSource mStatusDataSource;

    public StatusViewModelFactory(CompositeDisposable disposables, StatusDataSource statusDataSource) {
        mDisposables = disposables;
        mStatusDataSource = statusDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StatusViewModel.class)) {
            //noinspection unchecked
            return (T) new StatusViewModel(mDisposables, mStatusDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class!");
    }
}
