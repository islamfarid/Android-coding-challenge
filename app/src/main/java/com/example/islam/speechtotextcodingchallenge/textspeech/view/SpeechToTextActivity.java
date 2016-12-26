package com.example.islam.speechtotextcodingchallenge.textspeech.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.islam.speechtotextcodingchallenge.R;
import com.example.islam.speechtotextcodingchallenge.SpeechToTextApplication;
import com.example.islam.speechtotextcodingchallenge.textspeech.dagger.DaggerSpeechToTextUIComponent;
import com.example.islam.speechtotextcodingchallenge.textspeech.dagger.SpeechToTextUIModule;
import com.example.islam.speechtotextcodingchallenge.textspeech.presenter.SpeechToTextPresenter;
import com.example.islam.speechtotextcodingchallenge.utils.ActivityUtils;

import javax.inject.Inject;

public class SpeechToTextActivity extends AppCompatActivity {
    @Inject
    SpeechToTextPresenter mSpeechToTextPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        SpeechToTextFragment speechToTextFragment =
                (SpeechToTextFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        if (speechToTextFragment == null) {
            // Create the fragment
            speechToTextFragment = SpeechToTextFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getFragmentManager(), speechToTextFragment, R.id.contentFrame);
        }
        setTitle(getResources().getString(R.string.app_name));
        DaggerSpeechToTextUIComponent.builder()
                .speechToTextDataRepoComponent(((SpeechToTextApplication) getApplication()).getmSpeechToTextDataRepoComponent())
                .speechToTextUIModule(new SpeechToTextUIModule(speechToTextFragment)).build()
                .inject(this);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /* for espresso known issue
        * https://github.com/googlesamples/android-testing/issues/91*/
        super.onActivityResult(requestCode, resultCode, data);
        getFragmentManager().findFragmentById(R.id.contentFrame).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }
}
