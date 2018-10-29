package com.smartcontainer.smartcontainermvvm.data;

import com.smartcontainer.smartcontainermvvm.data.model.Product;

import java.util.List;

import io.reactivex.Single;

public interface StatusDataSource {
Single<List<Product>> getProductList();
}
