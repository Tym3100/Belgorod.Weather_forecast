package com.aitymkiv.belgorodweatherforecast.Models;

import java.util.List;

public class DailyWeatherLocalModel {
    private String mDayOfWeek;
    private String mDate;
    private int mTemperature;
    private String mIcon;
    private List<HourlyWeatherLocalModel> mListHourlyWeather;

    public DailyWeatherLocalModel(String dayOfWeek, String date, int temperature, String icon, List<HourlyWeatherLocalModel> listHourlyWeather) {
        this.mDayOfWeek = dayOfWeek;
        this.mDate = date;
        this.mTemperature = temperature;
        this.mIcon = icon;
        this.mListHourlyWeather = listHourlyWeather;
    }

    public String getDayOfWeek() {
        return mDayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.mDayOfWeek = dayOfWeek;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public int getTemperature() {
        return mTemperature;
    }

    public void setTemperature(int temperature) {
        this.mTemperature = temperature;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        this.mIcon = icon;
    }

    public List<HourlyWeatherLocalModel> getListHourlyWeather() {
        return mListHourlyWeather;
    }

    public void setListHourlyWeather(List<HourlyWeatherLocalModel> listHourlyWeather) {
        this.mListHourlyWeather = mListHourlyWeather;
    }
}
