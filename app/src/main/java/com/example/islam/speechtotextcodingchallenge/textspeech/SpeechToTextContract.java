package com.example.islam.speechtotextcodingchallenge.textspeech;

import com.example.islam.speechtotextcodingchallenge.BasePresenter;
import com.example.islam.speechtotextcodingchallenge.BaseView;
import com.example.islam.speechtotextcodingchallenge.data.models.CurrentWeather;

/**
 * Created by islam on 24/12/16.
 */

public interface SpeechToTextContract {
    interface View extends BaseView<Presenter> {
        void showWeatherData(CurrentWeather currentWeather);

        void showLoading();

        void showErrorMessage(String errorMsg);

        void hideLoading();

        void showNotSupportedErrorMessage();
    }

    interface Presenter extends BasePresenter {
        void loadWeatherData(String speechToTextData);
    }
}
