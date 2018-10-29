package com.smartcontainer.smartcontainermvvm.inventory_status;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.smartcontainer.smartcontainermvvm.common.BaseViewModel;
import com.smartcontainer.smartcontainermvvm.data.StatusDataSource;
import com.smartcontainer.smartcontainermvvm.data.model.ProductResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StatusViewModel extends BaseViewModel {

    private MutableLiveData<ProductResponse> mProductResponse;
    private StatusDataSource mStatusDataSource;

    protected StatusViewModel(CompositeDisposable disposables, StatusDataSource statusDataSource) {
        super(disposables);
        mStatusDataSource = statusDataSource;
    }

    public LiveData<ProductResponse> ProductResponse() {
        if (mProductResponse == null) {
            mProductResponse = new MutableLiveData<>();
        }
        return mProductResponse;
    }

    public void onProsuct() {
        Disposable subscribe = mStatusDataSource.getProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        addDisposable(subscribe);
    }

    public void start() {
    }
}
