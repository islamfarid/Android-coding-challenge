package com.example.islam.speechtotextcodingchallenge.textspeech.business;

import android.location.Address;

import com.example.islam.speechtotextcodingchallenge.common.Constants;
import com.example.islam.speechtotextcodingchallenge.data.SpeechToTextDataRepo;
import com.example.islam.speechtotextcodingchallenge.data.models.CurrentWeather;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by islam on 24/12/16.
 */

public class SpeechToTextBussiness {
    SpeechToTextDataRepo mSpeechToTextDataRepo;

    @Inject
    public SpeechToTextBussiness(SpeechToTextDataRepo speechToTextDataRepo) {
        mSpeechToTextDataRepo = speechToTextDataRepo;
    }

    public boolean checkIfSpeechTextbelongToWeather(String speechText) {
        return (speechText.toLowerCase().contains(Constants.APP_MAIN_FUNCTIONALITY.toLowerCase()));
    }

    public Observable<Address> getCurrentLocationObservable() {
        return mSpeechToTextDataRepo.getCurrentLocationObservable();
    }

    public Observable<CurrentWeather> loadWeatherForSpecificLocation(String longitudeAndLatitude) {
        return mSpeechToTextDataRepo.loadWeatherForSpecificLocation(Constants.WEATHER_API_KEY, longitudeAndLatitude, Constants.WEATHER_UNITS_VALUE, Constants.WEATHER_API_EXCLUDE_VALUE);
    }

    public String appendLongitudeToLAtitude(double longitude, double latitude) {
        return latitude + "," + longitude;
    }
}
