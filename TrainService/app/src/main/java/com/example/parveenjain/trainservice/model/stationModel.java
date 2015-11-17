package com.example.parveenjain.trainservice.model;


public class stationModel {

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


}
