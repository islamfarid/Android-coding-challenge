package com.example.islam.speechtotextcodingchallenge.data.dagger;

import android.content.Context;

import com.example.islam.speechtotextcodingchallenge.data.location.Location;
import com.example.islam.speechtotextcodingchallenge.data.location.LocationDataSource;
import com.example.islam.speechtotextcodingchallenge.data.location.LocationProvider;
import com.example.islam.speechtotextcodingchallenge.data.remote.Remote;
import com.example.islam.speechtotextcodingchallenge.data.remote.ServiceGenerator;
import com.example.islam.speechtotextcodingchallenge.data.remote.WeatherRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 23/12/16.
 */
@Module
public class SpeechToTextDataRepoModule {
    @Singleton
    @Provides
    @Remote
    WeatherRemoteDataSource provideWeatherRemoteDataSource() {
        return ServiceGenerator.createService(WeatherRemoteDataSource.class);
    }

    @Singleton
    @Provides
    @Location
    LocationDataSource provideLocationDataSource(Context context) {
        return new LocationProvider(context);
    }
}
