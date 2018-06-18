package com.example.user.lkdjf;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CEX {

    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("last")
    @Expose
    private String last;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("volume30d")
    @Expose
    private String volume30d;
    @SerializedName("bid")
    @Expose
    private Float bid;
    @SerializedName("ask")
    @Expose
    private Float ask;

    public Float getTimestamp() {
        Float timew  = Float.valueOf(timestamp);
        return timew;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public Float getLast() {

        Float lastw  = Float.valueOf(last);
        return lastw;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume30d() {
        return volume30d;
    }

    public void setVolume30d(String volume30d) {
        this.volume30d = volume30d;
    }

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public Float getAsk() {
        return ask;
    }

    public void setAsk(Float ask) {
        this.ask = ask;
    }

}

