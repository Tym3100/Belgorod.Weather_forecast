package com.aitymkiv.belgorodweatherforecast.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.aitymkiv.belgorodweatherforecast.Models.DailyWeatherLocalModel;
import com.aitymkiv.belgorodweatherforecast.Models.HourlyWeatherLocalModel;
import com.aitymkiv.belgorodweatherforecast.Models.WeatherModel;
import com.aitymkiv.belgorodweatherforecast.Models.MainListJsonObject;
import com.aitymkiv.belgorodweatherforecast.Models.RepositoryWeather;

import java.util.ArrayList;
import java.util.List;

public class WeatherViewModel extends ViewModel {
    private final MutableLiveData<List<DailyWeatherLocalModel>> liveData = new MutableLiveData<>();
    private List<WeatherModel> mappingWeather = new ArrayList<>();
    private List<HourlyWeatherLocalModel> mappingHourlyWeather = new ArrayList<>();
    private List<DailyWeatherLocalModel> mappingDailyWeather = new ArrayList<>();

    public WeatherViewModel() {
        RepositoryWeather.getInstance().getData();
        RepositoryWeather.getInstance().getListLiveData().observeForever(new Observer<List<MainListJsonObject>>() {
            @Override
            public void onChanged(List<MainListJsonObject> listJsonObjects) {
                mapping(listJsonObjects);
                liveData.postValue(mappingDailyWeather);
            }
        });
    }
    public void mapping(List<MainListJsonObject> listJsonObjects){
        mappingWeather.clear();
        mappingDailyWeather.clear();
        mappingHourlyWeather.clear();
        for(int i = 0; i < listJsonObjects.size(); i++) {
            mappingWeather.add(new WeatherModel(
                    (listJsonObjects.get(i).getMainWeather().getTemp() - 273),
                    listJsonObjects.get(i).getDate(),
                    listJsonObjects.get(i).getListWeather().get(0).getDescription(),
                    listJsonObjects.get(i).getListWeather().get(0).getIcon()));
        }
        for(WeatherModel weatherModel: mappingWeather){
            String time = weatherModel.getDate().substring(11, 13);
            mappingHourlyWeather.add(new HourlyWeatherLocalModel(time,
                    weatherModel.getTemperature(), weatherModel.getDescription(),
                    weatherModel.getIcon()));
            if (time.equals("21")){
                mappingDailyWeather.add(new DailyWeatherLocalModel(weatherModel.getDayOfWeek(),
                        weatherModel.getDate().substring(0, 10),
                        mappingHourlyWeather.get((int)(mappingHourlyWeather.size()/2)).getTemperature(),
                        mappingHourlyWeather.get((int)(mappingHourlyWeather.size()/2)).getIcon(),
                        mappingHourlyWeather));
                mappingHourlyWeather = new ArrayList<>();
            }
            Log.d("MyTag", "\tdate: " + weatherModel.getDate() + "\tdayOfWeek: " +
                    weatherModel.getDayOfWeek() + "\tdescription: " + weatherModel.getDescription()
                    + "\ticon: " + weatherModel.getIcon() + "\ttemperature: "
                    + weatherModel.getTemperature());
        }

        for(DailyWeatherLocalModel dailyWeatherLocalModel: mappingDailyWeather) {
            Log.d("MyTag1", "-------------------------------------");
            Log.d("MyTag1", "date: " + dailyWeatherLocalModel.getDate() +
                    " dayOfWeek: " + dailyWeatherLocalModel.getDayOfWeek() +
                    " icon: " + dailyWeatherLocalModel.getIcon() +
                    " temperature: " + dailyWeatherLocalModel.getTemperature());
            for (HourlyWeatherLocalModel hourlyWeatherLocalModel : dailyWeatherLocalModel.getListHourlyWeather()) {
                Log.d("MyTag1", "time: " + hourlyWeatherLocalModel.getTime() +
                        " temperature: " + hourlyWeatherLocalModel.getTemperature() +
                        " description: " + hourlyWeatherLocalModel.getDescription());
            }
        }
    }

    public LiveData<List<DailyWeatherLocalModel>> getLiveData() {
        return liveData;
    }
}
