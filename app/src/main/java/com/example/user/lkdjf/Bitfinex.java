package com.example.user.lkdjf;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bitfinex {

    @SerializedName("mid")
    @Expose
    private String mid;
    @SerializedName("bid")
    @Expose
    private String bid;
    @SerializedName("ask")
    @Expose
    private String ask;
    @SerializedName("last_price")
    @Expose
    private String lastPrice;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Float getBid() {
        Float bidw  = Float.valueOf(bid);
        return bidw;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Float getAsk() {
        Float askw = Float.valueOf(ask);
        return askw;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public Float getLastPrice() {
        Float lastw = Float.valueOf(lastPrice);
        return lastw;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Float getTimestamp() {
        Float timew  = Float.valueOf(timestamp);
        return timew;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}