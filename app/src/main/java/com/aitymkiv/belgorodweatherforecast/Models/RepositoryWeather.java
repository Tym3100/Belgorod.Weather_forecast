package com.aitymkiv.belgorodweatherforecast.Models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryWeather {

    private List<MainListJsonObject> listJsonObjects = new ArrayList<>();
    private static RepositoryWeather mInstance;
    private final long CITY_ID = 578072;
    private final String API_KEY = "fc95608dbf99a0975a30a60e37751427";
    private MutableLiveData<List<MainListJsonObject>> listLiveData = new MutableLiveData<>();

    private RepositoryWeather() {

    }

    public static RepositoryWeather getInstance() {
        if (mInstance == null) {
            mInstance = new RepositoryWeather();
        }
        return mInstance;
    }

    public void getData() {
        NetworkService.getInstance()
                .getJSONApi()
                .getWeatherWithCity(CITY_ID, API_KEY)
                .enqueue(new Callback<MainJsonObject>() {
                    @Override
                    public void onResponse(Call<MainJsonObject> call, Response<MainJsonObject> response) {
                        if(response.isSuccessful() && response.body() != null) {
                            listJsonObjects.clear();
                            for (int i = 0; i < response.body().getListWeather().size(); i++) {
                                listJsonObjects.add(response.body().getListWeather().get(i));
                                Log.d("MyTag", "From repo. date: " + response.body().getListWeather().get(i).getDate());
                            }
                            listLiveData.postValue(listJsonObjects);
                        }
                        else{

                        }
                    }

                    @Override
                    public void onFailure(Call<MainJsonObject> call, Throwable t) {
                        Log.d("MyTag", " " + t.getMessage());
                    }
                });
    }

    public MutableLiveData<List<MainListJsonObject>> getListLiveData() {
        return listLiveData;
    }
}
