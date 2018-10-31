package com.smartcontainer.smartcontainermvvm.data.model;

import com.google.firebase.firestore.Exclude;

public class Product {
    @Exclude
    private String mDeviceId;
    private double mReadingGram;
    private long mLastRead;

    public double getReadingGram() {
        return mReadingGram;
    }

    public void setReadingGram(double readingGram) {
        mReadingGram = readingGram;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    public void setDeviceId(String deviceId) {
        mDeviceId = deviceId;
    }

    public long getLastRead() {
        return mLastRead;
    }

    public void setLastRead(long lastRead) {
        mLastRead = lastRead;
    }
}
