package com.smartcontainer.smartcontainermvvm.inventory_status;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.smartcontainer.smartcontainermvvm.common.BaseViewModel;
import com.smartcontainer.smartcontainermvvm.data.StatusDataSource;
import com.smartcontainer.smartcontainermvvm.data.model.Product;

import com.smartcontainer.smartcontainermvvm.data.model.StatusResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.smartcontainer.smartcontainermvvm.data.model.StatusResponse.error;
import static com.smartcontainer.smartcontainermvvm.data.model.StatusResponse.success;

public class StatusViewModel extends BaseViewModel {

    private MutableLiveData<StatusResponse> mStatusResponse;
    private StatusDataSource mSourceProductList;

    protected StatusViewModel(CompositeDisposable disposables, StatusDataSource statusDataSource) {
        super(disposables);
        mSourceProductList = statusDataSource;
    }

    public LiveData<StatusResponse> ProductResponse() {
        if (mStatusResponse == null) {
            mStatusResponse = new MutableLiveData<>();
        }
        return mStatusResponse;
    }

    public void onProduct() {
        Disposable subscribe = mSourceProductList.getProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handelSucces,
                        this::handelError);
        addDisposable(subscribe);
    }

    private void handelError(Throwable throwable) {
        mStatusResponse.setValue(error(throwable));
    }

    private void handelSucces(List<Product> products) {
        mStatusResponse.setValue(success(products));
    }

    public void start() {
        mSourceProductList.getProductList();
    }
}
