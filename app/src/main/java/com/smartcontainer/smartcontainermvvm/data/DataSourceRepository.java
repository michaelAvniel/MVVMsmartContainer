package com.smartcontainer.smartcontainermvvm.data;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.smartcontainer.smartcontainermvvm.data.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;

public class DataSourceRepository implements StatusDataSource {
    public static final String COL_DEVICES = "devices";
    private final CollectionReference mCollectionReference;
    private List<Product>   mProducts = Collections.emptyList();

    public DataSourceRepository() {
        mCollectionReference = FirebaseFirestore.getInstance().getInstance().collection(COL_DEVICES);
    }

    public Observable<List<Product>> getAllDevicesObservable() {
        return Observable.create(new ObservableOnSubscribe<List<Product>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<Product>> emitter) throws Exception {
                mCollectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDs, @Nullable FirebaseFirestoreException e) {
                        if (queryDs != null) {
                            emitter.onNext(convertSnapshotToDevices(queryDs));
                        } else {
                            emitter.onError(e);
                        }
                    }
                });
            }
        });
    }

    private List<Product> convertSnapshotToDevices(QuerySnapshot queryDs) {


        List<DocumentSnapshot> documents = queryDs.getDocuments();

        if (!documents.isEmpty()) {
            mProducts = new ArrayList<>();
            for (DocumentSnapshot snapshot : documents
                    ) {
                Product product = snapshot.toObject(Product.class);
                product.setDeviceId(snapshot.getId());
                mProducts.add(product);

            }

        }
        return mProducts;
    }

    @Override
    public Single<List<Product>> getProductList() {
        if (!mProducts.isEmpty()) {
            getAllDevicesObservable();
            return Single.just(mProducts);
        }
        getAllDevicesObservable();
    return Single.just(mProducts) ;}
}
