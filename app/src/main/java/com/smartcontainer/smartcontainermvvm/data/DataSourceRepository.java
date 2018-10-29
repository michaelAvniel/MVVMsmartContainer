package com.smartcontainer.smartcontainermvvm.data;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smartcontainer.smartcontainermvvm.data.model.Product;
import java.util.List;
import io.reactivex.Single;

public class DataSourceRepository implements StatusDataSource {
    FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    public static  final String COL_DEVICES = "devices";
    private final CollectionReference mCollectionReference;

    public DataSourceRepository() {
        mCollectionReference = FirebaseFirestore.getInstance().getInstance().collection(COL_DEVICES);

    }

    @Override
    public Single<List<Product>> getProductList() {

        return null;
    }
}
