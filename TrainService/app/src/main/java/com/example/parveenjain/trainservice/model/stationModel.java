package com.example.parveenjain.trainservice.model;


import android.os.Parcel;
import android.os.Parcelable;

public class stationModel implements Parcelable{

    private int no;
    private String src_departure_time;
    private String to;
    private String dest_arrival_time;
    private int number;
    private String name;
    private String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDest_arrival_time() {
        return dest_arrival_time;
    }

    public void setDest_arrival_time(String dest_arrival_time) {
        this.dest_arrival_time = dest_arrival_time;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSrc_departure_time() {
        return src_departure_time;
    }

    public void setSrc_departure_time(String src_departure_time) {
        this.src_departure_time = src_departure_time;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(no);
        dest.writeString(src_departure_time);
        dest.writeString(to);
        dest.writeString(dest_arrival_time);
        dest.writeInt(number);
        dest.writeString(name);
        dest.writeString(from);
    }
    public stationModel(Parcel in) {
        no=in.readInt();
        src_departure_time=in.readString();
        to=in.readString();
        dest_arrival_time=in.readString();
        number=in.readInt();
        name=in.readString();
        from=in.readString();
    }
    public stationModel() {

    }

    public static final Parcelable.Creator<stationModel> CREATOR = new Parcelable.Creator<stationModel>() {
        public stationModel createFromParcel(Parcel in) {
            return new stationModel(in);
        }
        public stationModel[] newArray(int size) {
            return new stationModel[size];
        }
    };
}
