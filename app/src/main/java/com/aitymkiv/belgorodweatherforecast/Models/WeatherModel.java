package com.aitymkiv.belgorodweatherforecast.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherModel {
    private Integer mTemperature;
    private String mDate;
    private String mDescription;
    private String mDayOfWeek;
    private String mIcon;

    public String getDayOfWeek() {
        return mDayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.mDayOfWeek = dayOfWeek;
    }

    public Integer getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Double temperature) {
        this.mTemperature = temperature.intValue();
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
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

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public WeatherModel(Double temperature, String date, String description, String icon) {
        this.mTemperature = temperature.intValue();
        this.mDate = date;
        this.mDescription = description;
        this.mIcon = icon;

        date = date.substring(0, 10);
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.mDayOfWeek = date1.toString().substring(0, 3);

    }
}
