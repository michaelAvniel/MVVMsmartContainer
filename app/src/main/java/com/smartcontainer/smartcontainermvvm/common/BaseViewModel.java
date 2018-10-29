package com.smartcontainer.smartcontainermvvm.common;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BaseViewModel extends ViewModel {

    private final CompositeDisposable mDisposables;

    protected BaseViewModel(CompositeDisposable disposables) {
        mDisposables = disposables;
    }

    protected void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

    @Override
    protected void onCleared() {
        mDisposables.clear();
    }
}

