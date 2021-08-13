package com.aitymkiv.belgorodweatherforecast.Models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherPlaceHolderAPI {


    @GET("forecast?")
    Call<MainJsonObject> getWeatherWithCity(@Query("id") long cityId,
                                                        @Query("appid") String appKey);

}
