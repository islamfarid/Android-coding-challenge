package com.example.islam.speechtotextcodingchallenge.textspeech.presenter;

import android.location.Address;
import android.support.annotation.NonNull;

import com.example.islam.speechtotextcodingchallenge.textspeech.SpeechToTextContract;
import com.example.islam.speechtotextcodingchallenge.textspeech.business.SpeechToTextBussiness;
import com.example.islam.speechtotextcodingchallenge.utils.EspressoIdlingResource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by islam on 24/12/16.
 */

public class SpeechToTextPresenter implements SpeechToTextContract.Presenter {

    @NonNull
    private SpeechToTextContract.View mSpeechToTextView;
    @NonNull
    private SpeechToTextBussiness mSpeechToTextBussiness;
    @NonNull
    private CompositeDisposable mSubscriptions;

    @Inject
    public SpeechToTextPresenter(SpeechToTextContract.View mManufactureView) {
        this.mSpeechToTextView = mManufactureView;
        mSubscriptions = new CompositeDisposable();
    }


    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    /**
     * Method injection is used here to safely reference {@code this} after the object is created.
     * For more information, see Java Concurrency in Practice.
     */
    @Inject
    public void setupListeners() {
        mSpeechToTextView.setPresenter(this);
    }

    @Inject
    public void setBusiness(SpeechToTextBussiness speechToTextBussiness) {
        this.mSpeechToTextBussiness = speechToTextBussiness;
    }

    @Override
    public void loadWeatherData(String speechToTextData) {
        EspressoIdlingResource.increment();
        mSpeechToTextView.showLoading();
        if (mSpeechToTextBussiness.checkIfSpeechTextbelongToWeather(speechToTextData)) {
            mSpeechToTextBussiness.getCurrentLocationObservable().subscribeOn(Schedulers.newThread())
                    .subscribe(
                            // onNext
                            address -> {
                                loadWeatherRemotely(address);
                            }, // onError
                            exception -> {
                                mSpeechToTextView.hideLoading();
                                mSpeechToTextView.showErrorMessage(exception.getMessage());//TODO error handler
                            },//onComplete
                            () -> {
                                mSpeechToTextView.hideLoading();
                                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                                    EspressoIdlingResource.decrement(); // Set app as idle.
                                }
                            });
        } else {
            mSpeechToTextView.hideLoading();
            mSpeechToTextView.showNotSupportedErrorMessage();//TODO error handler
        }
    }

    public void loadWeatherRemotely(Address address) {
        EspressoIdlingResource.increment();
        mSpeechToTextView.showLoading();
        mSpeechToTextBussiness.loadWeatherForSpecificLocation(mSpeechToTextBussiness.
                appendLongitudeToLAtitude(address.getLongitude(), address.getLatitude())).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        currentWeather -> {
                            mSpeechToTextView.showWeatherData(currentWeather);
                        }, // onError
                        exception -> {
                            mSpeechToTextView.showErrorMessage(exception.getMessage());//TODO error handler
                            mSpeechToTextView.hideLoading();
                        },//onComplete
                        () -> {
                            mSpeechToTextView.hideLoading();
                            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                                EspressoIdlingResource.decrement(); // Set app as idle.
                            }
                        });
    }

}


