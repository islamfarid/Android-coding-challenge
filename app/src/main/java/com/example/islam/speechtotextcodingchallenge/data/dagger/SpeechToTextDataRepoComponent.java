package com.example.islam.speechtotextcodingchallenge.data.dagger;

import com.example.islam.speechtotextcodingchallenge.ApplicationModule;
import com.example.islam.speechtotextcodingchallenge.data.SpeechToTextDataRepo;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by islam on 23/12/16.
 */
@Singleton
@Component(modules = {SpeechToTextDataRepoModule.class, ApplicationModule.class})
public interface SpeechToTextDataRepoComponent {
    SpeechToTextDataRepo getSpeechToTextDataRepo();
}
