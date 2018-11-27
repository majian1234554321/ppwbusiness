package com.yjhh.ppwbusiness.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BusinessHoursBean implements Serializable, Parcelable {
    public BusinessHoursBean(String begin, String end) {
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

    protected BusinessHoursBean(Parcel in) {
        this.begin = in.readString();
        this.end = in.readString();
    }

    public static final Parcelable.Creator<BusinessHoursBean> CREATOR = new Parcelable.Creator<BusinessHoursBean>() {
        @Override
        public BusinessHoursBean createFromParcel(Parcel source) {
            return new BusinessHoursBean(source);
        }

        @Override
        public BusinessHoursBean[] newArray(int size) {
            return new BusinessHoursBean[size];
        }
    };
}
