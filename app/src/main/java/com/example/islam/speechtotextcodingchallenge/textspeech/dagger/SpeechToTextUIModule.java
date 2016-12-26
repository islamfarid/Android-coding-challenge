package com.example.islam.speechtotextcodingchallenge.textspeech.dagger;

import com.example.islam.speechtotextcodingchallenge.textspeech.SpeechToTextContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 24/12/16.
 */
@Module
public class SpeechToTextUIModule {
    private final SpeechToTextContract.View mView;


    public SpeechToTextUIModule(SpeechToTextContract.View view) {
        this.mView = view;
    }

    @Provides
    SpeechToTextContract.View provideManufactureView() {
        return mView;
    }
}
