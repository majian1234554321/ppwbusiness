package com.yjhh.ppwbusiness.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ShopTimesModel implements Serializable, Parcelable{


    public ShopTimesModel(String begin, String end) {
        this.begin = begin;
        this.end = end;
    }

    public String begin;
    public String end;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.begin);
        dest.writeString(this.end);
    }

    protected ShopTimesModel(Parcel in) {
        this.begin = in.readString();
        this.end = in.readString();
    }

    public static final Parcelable.Creator<ShopTimesModel> CREATOR = new Parcelable.Creator<ShopTimesModel>() {
        @Override
        public ShopTimesModel createFromParcel(Parcel source) {
            return new ShopTimesModel(source);
        }

        @Override
        public ShopTimesModel[] newArray(int size) {
            return new ShopTimesModel[size];
        }
    };

}
