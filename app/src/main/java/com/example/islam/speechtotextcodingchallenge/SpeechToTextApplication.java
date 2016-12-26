package com.example.islam.speechtotextcodingchallenge;

import android.app.Application;

import com.example.islam.speechtotextcodingchallenge.data.dagger.DaggerSpeechToTextDataRepoComponent;
import com.example.islam.speechtotextcodingchallenge.data.dagger.SpeechToTextDataRepoComponent;
import com.example.islam.speechtotextcodingchallenge.data.dagger.SpeechToTextDataRepoModule;

/**
 * Created by islam on 23/12/16.
 */

public class SpeechToTextApplication extends Application {
    private SpeechToTextDataRepoComponent mSpeechToTextDataRepoComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mSpeechToTextDataRepoComponent = DaggerSpeechToTextDataRepoComponent.builder()
                .applicationModule(new ApplicationModule((getApplicationContext()))).speechToTextDataRepoModule(new SpeechToTextDataRepoModule()).build();
    }

    public SpeechToTextDataRepoComponent getmSpeechToTextDataRepoComponent() {
        return mSpeechToTextDataRepoComponent;
    }

}
