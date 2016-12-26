package com.example.islam.speechtotextcodingchallenge.data;

import android.location.Address;

import com.example.islam.speechtotextcodingchallenge.data.location.Location;
import com.example.islam.speechtotextcodingchallenge.data.location.LocationDataSource;
import com.example.islam.speechtotextcodingchallenge.data.models.CurrentWeather;
import com.example.islam.speechtotextcodingchallenge.data.remote.Remote;
import com.example.islam.speechtotextcodingchallenge.data.remote.WeatherRemoteDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by islam on 23/12/16.
 */
@Singleton
public class SpeechToTextDataRepo implements SpeechToTextDataSource {
    WeatherRemoteDataSource weatherRemoteDataSource;
    LocationDataSource locationDataSource;

    @Inject
    public SpeechToTextDataRepo(@Remote WeatherRemoteDataSource weatherRemoteDataSource, @Location LocationDataSource locationDataSource) {
        this.weatherRemoteDataSource = weatherRemoteDataSource;
        this.locationDataSource = locationDataSource;
    }

    @Override
    public Observable<Address> getCurrentLocationObservable() {
        return locationDataSource.getCurrentLocationObservable();
    }

    @Override
    public Observable<CurrentWeather> loadWeatherForSpecificLocation(String apiKey, String longitudeAndLatitude, String units, String exclude) {
        return weatherRemoteDataSource.loadWeatherForSpecificLocation(apiKey, longitudeAndLatitude, units, exclude);
    }
}
