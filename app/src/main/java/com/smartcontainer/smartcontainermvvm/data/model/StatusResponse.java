package com.smartcontainer.smartcontainermvvm.data.model;

import android.support.annotation.IntDef;

import java.util.List;

public class StatusResponse {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    @IntDef({SUCCESS, ERROR})
    @interface Status {

    }   @Status
    public final int status;
    public final List<Product> data;
    public final Throwable error;

    public StatusResponse(int status, List<Product> data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }
    public static StatusResponse success(List<Product>data){
        return new StatusResponse(SUCCESS,data,null);
    }
    public static StatusResponse error(Throwable error) {
        return new StatusResponse(ERROR, null, error);
    }
}
