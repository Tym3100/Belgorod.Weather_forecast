package com.aitymkiv.belgorodweatherforecast.Models;

public class HourlyWeatherLocalModel {
    private String mTime;
    private int mTemperature;
    private String mDescription;
    private String mIcon;


    public HourlyWeatherLocalModel(String time, int temperature, String description, String icon) {
        this.mTime = time;
        this.mTemperature = temperature;
        this.mDescription = description;
        this.mIcon = icon;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }

    public int getTemperature() {
        return mTemperature;
    }

    public void setTemperature(int temperature) {
        this.mTemperature = temperature;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String urlIcon) {
        this.mIcon = urlIcon;
    }
}
