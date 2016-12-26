package com.example.islam.speechtotextcodingchallenge.textspeech.presenter;

import android.location.Address;

import com.example.islam.speechtotextcodingchallenge.data.models.CurrentWeather;
import com.example.islam.speechtotextcodingchallenge.textspeech.SpeechToTextContract;
import com.example.islam.speechtotextcodingchallenge.textspeech.business.SpeechToTextBussiness;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by islam on 26/12/16.
 */
public class SpeechToTextPresenterTest {
    SpeechToTextPresenter mSpeechToTextPresenter;
    @Mock
    private SpeechToTextBussiness mSpeechToTextBussiness;
    @Mock
    private SpeechToTextContract.View mSpeechToTextView;
    @Mock
    Address address;

    @Before
    public void setupTasksPresenter() {
        MockitoAnnotations.initMocks(this);
        mSpeechToTextPresenter = new SpeechToTextPresenter(mSpeechToTextView);
        mSpeechToTextPresenter.setBusiness(mSpeechToTextBussiness);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());

    }

    @Test
    public void testWhenloadWeatherData_SpeechToTextViewShowProgressISShown() {
        mSpeechToTextPresenter.loadWeatherData("weather");
        verify(mSpeechToTextView, times(1)).showLoading();
    }

    @Test
    public void testWhenLoadWeatherDataLocationError_SpeechToTextViewHideLoadingISCalled() {
        doNothing().when(mSpeechToTextView).showLoading();
        doNothing().when(mSpeechToTextView).showErrorMessage(null);
        when(mSpeechToTextBussiness.checkIfSpeechTextbelongToWeather("weather")).thenReturn(true);
        when(mSpeechToTextBussiness.getCurrentLocationObservable()).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable());
        }));
        mSpeechToTextPresenter.loadWeatherData("weather");
        verify(mSpeechToTextView, times(1)).hideLoading();
    }
    @Test
    public void testWhenLoadWeatherDataLocationSuccess_SpeechToTextViewHideLoadingISCalled() {
        doNothing().when(mSpeechToTextView).showLoading();
        doNothing().when(mSpeechToTextView).showWeatherData(null);
        when(mSpeechToTextBussiness.checkIfSpeechTextbelongToWeather("weather")).thenReturn(true);
        when(mSpeechToTextBussiness.getCurrentLocationObservable()).thenReturn(Observable.create(sub -> {
            sub.onNext(address);
            sub.onComplete();
        }));
        mSpeechToTextPresenter.loadWeatherData("weather");
        verify(mSpeechToTextView, times(1)).hideLoading();
    }
    @Test
    public void testWhenLoadWeatherDataRemoteError_SpeechToTextViewShowErrorISCalled() {
        doNothing().when(mSpeechToTextView).showLoading();
        doNothing().when(mSpeechToTextView).hideLoading();
        doNothing().when(mSpeechToTextView).showErrorMessage(null);
        when(mSpeechToTextBussiness.checkIfSpeechTextbelongToWeather("weather")).thenReturn(true);
        when(mSpeechToTextBussiness.appendLongitudeToLAtitude(0.0, 0.0)).thenReturn("0.0");
        when(mSpeechToTextBussiness.getCurrentLocationObservable()).thenReturn(Observable.create(sub -> {
            sub.onNext(address);
            sub.onComplete();
        }));
        when(mSpeechToTextBussiness.loadWeatherForSpecificLocation("0.0")).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable());
        }));
        when(address.getLatitude()).thenReturn(0.0);
        when(address.getLongitude()).thenReturn(0.0);
        mSpeechToTextPresenter.loadWeatherRemotely(address);
        verify(mSpeechToTextView, times(1)).showErrorMessage(null);

    }
    @Test
    public void testWhenLoadWeatherDataRemoteSuccess_SpeechToTextViewHideLoadingSCalled() {
        doNothing().when(mSpeechToTextView).showLoading();
        doNothing().when(mSpeechToTextView).hideLoading();
        doNothing().when(mSpeechToTextView).showWeatherData(null);
        when(mSpeechToTextBussiness.checkIfSpeechTextbelongToWeather("weather")).thenReturn(true);
        when(mSpeechToTextBussiness.appendLongitudeToLAtitude(0.0, 0.0)).thenReturn("0.0");
        when(mSpeechToTextBussiness.getCurrentLocationObservable()).thenReturn(Observable.create(sub -> {
            sub.onNext(address);
            sub.onComplete();
        }));
        when(mSpeechToTextBussiness.loadWeatherForSpecificLocation("0.0")).thenReturn(Observable.create(sub -> {
            sub.onNext(new CurrentWeather());
            sub.onComplete();
        }));
        when(address.getLatitude()).thenReturn(0.0);
        when(address.getLongitude()).thenReturn(0.0);
        mSpeechToTextPresenter.loadWeatherRemotely(address);
        verify(mSpeechToTextView, times(1)).hideLoading();

    }

    @Test
    public void testWhenLoadWeatherDataLocationError_SpeechToTextViewShowErrorISCalled() {
        doNothing().when(mSpeechToTextView).showLoading();
        doNothing().when(mSpeechToTextView).showErrorMessage(null);
        when(mSpeechToTextBussiness.checkIfSpeechTextbelongToWeather("weather")).thenReturn(true);
        when(mSpeechToTextBussiness.appendLongitudeToLAtitude(0.0, 0.0)).thenReturn("0.0");
        when(mSpeechToTextBussiness.getCurrentLocationObservable()).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable());
        }));
        mSpeechToTextPresenter.loadWeatherData("weather");
        verify(mSpeechToTextView, times(1)).showErrorMessage(null);
    }


    @After
    public void teardown() {
        RxAndroidPlugins.reset();
    }
}