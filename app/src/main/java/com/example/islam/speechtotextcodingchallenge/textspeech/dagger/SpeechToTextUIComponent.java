package com.example.islam.speechtotextcodingchallenge.textspeech.dagger;

import com.example.islam.speechtotextcodingchallenge.data.dagger.SpeechToTextDataRepoComponent;
import com.example.islam.speechtotextcodingchallenge.textspeech.view.SpeechToTextActivity;
import com.example.islam.speechtotextcodingchallenge.utils.FragmentScoped;

import dagger.Component;

/**
 * Created by islam on 24/12/16.
 */

@FragmentScoped
@Component(dependencies = SpeechToTextDataRepoComponent.class,
        modules = SpeechToTextUIModule.class)
public interface SpeechToTextUIComponent {
    void inject(SpeechToTextActivity speechToTextActivity);
}
