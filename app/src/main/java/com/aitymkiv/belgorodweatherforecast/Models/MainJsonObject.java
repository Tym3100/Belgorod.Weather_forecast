package com.aitymkiv.belgorodweatherforecast.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainJsonObject {

    @SerializedName("list")
    @Expose
    private List<MainListJsonObject> listWeather;

    public List<MainListJsonObject> getListWeather() {
        return listWeather;
    }

    public void setListWeather(List<MainListJsonObject> listWeather) {
        this.listWeather = listWeather;
    }
}
