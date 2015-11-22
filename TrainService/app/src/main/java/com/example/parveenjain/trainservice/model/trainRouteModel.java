package com.example.parveenjain.trainservice.model;


import android.os.Parcel;
import android.os.Parcelable;


public class trainRouteModel implements Parcelable{

    String latitude;
    String longitude;
    int no;
    String fullname;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeInt(no);
        dest.writeString(fullname);
    }

    public trainRouteModel(Parcel in) {
        latitude = in.readString();
        longitude = in.readString();
        no = in.readInt();
        fullname = in.readString();
    }

    public trainRouteModel() {

    }

    public static final Parcelable.Creator<trainRouteModel> CREATOR = new Parcelable.Creator<trainRouteModel>() {
        public trainRouteModel createFromParcel(Parcel in) {
            return new trainRouteModel(in);
        }
        public trainRouteModel[] newArray(int size) {
            return  new trainRouteModel[size];
        }
    };



}
