package com.aitymkiv.belgorodweatherforecast.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainListJsonObject {

    @SerializedName("main")
    @Expose
    private DailyWeatherFromJson mainWeather;

    @SerializedName("weather")
    @Expose
    private List<WeatherJsonObject> listWeather;

    @SerializedName("dt_txt")
    @Expose
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DailyWeatherFromJson getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(DailyWeatherFromJson mainWeather) {
        this.mainWeather = mainWeather;
    }

    public List<WeatherJsonObject> getListWeather() {
        return listWeather;
    }

    public void setListWeather(List<WeatherJsonObject> listWeather) {
        this.listWeather = listWeather;
    }
}
