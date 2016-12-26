package com.example.islam.speechtotextcodingchallenge.data.remote;


import com.example.islam.speechtotextcodingchallenge.data.models.CurrentWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface WeatherRemoteDataSource {

    @GET("{api_key}/{latitudeAndlongitudeAndtime}/")
    Observable<CurrentWeather> loadWeatherForSpecificLocation(
            @Path("api_key") String apiKey, @Path("latitudeAndlongitudeAndtime") String longitudeAndLatitude,
            @Query("units") String units,
            @Query("exclude") String exclude);

}
