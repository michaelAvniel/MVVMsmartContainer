package com.smartcontainer.smartcontainermvvm.inventory_status;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcontainer.smartcontainermvvm.R;
import com.smartcontainer.smartcontainermvvm.data.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class MyRecyclerAdapter  extends android.support.v7.widget.RecyclerView.Adapter<MyRecyclerAdapter.ProductViewHolder> {


    private final LayoutInflater mInflater;
    private List<Product> mProducts;

    public MyRecyclerAdapter(Context context, List<Product> products) {
        mProducts = products;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<Product> devices) {
        mProducts = devices;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cardview, parent, false);
        return new ProductViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product device = mProducts.get(position);
        holder.mWeight.setText(String.valueOf(device.getReadingGram()));
        switch (device.getDeviceId()) {
            case "device1":
                holder.mImageView.setImageResource(R.drawable.tomato);
                holder.mName.setText("Tomato");
                holder.mLastFill.setText("08:21");
                holder.mCapacity.setText("45%");
                break;
            case "device2":
                holder.mImageView.setImageResource(R.drawable.sweetpotato);
                holder.mName.setText("Sweet Potato");
                holder.mLastFill.setText("08:32");
                holder.mCapacity.setText("25%");
                break;
            case "device3":
                holder.mImageView.setImageResource(R.drawable.mushroom2);
                holder.mName.setText("Mushroom");
                holder.mLastFill.setText("08:36");
                holder.mCapacity.setText("63%");
                break;
            case "device4":
                holder.mImageView.setImageResource(R.drawable.onion);
                holder.mName.setText("Onion");
                holder.mLastFill.setText("08:45");
                holder.mCapacity.setText("92%");
                break;
            case "device5":
                holder.mImageView.setImageResource(R.drawable.gamba);
                holder.mName.setText("Red Pepper");
                holder.mLastFill.setText("08:57");
                holder.mCapacity.setText("32%");
                break;
            case "device6":
                holder.mImageView.setImageResource(R.drawable.corn);
                holder.mName.setText("Corn");
                holder.mLastFill.setText("09:08");
                holder.mCapacity.setText("81%");
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mProducts == null ? 0 : mProducts.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView mImageView;
        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.tv_weight)
        TextView mWeight;
        @BindView(R.id.tv_last_fill)
        TextView mLastFill;
        @BindView(R.id.tv_capacity)
        TextView mCapacity;

        public ProductViewHolder(View itemView) {
            super(itemView);/*
            mImageView = itemView.findViewById(R.id.iv_image);
            mName = itemView.findViewById(R.id.tv_name);
            mWeight = itemView.findViewById(R.id.tv_weight);
            mLastFill = itemView.findViewById(R.id.tv_last_fill);
            mCapacity = itemView.findViewById(R.id.tv_capacity);*/
            ButterKnife.bind(this, itemView);
        }
    }

}
