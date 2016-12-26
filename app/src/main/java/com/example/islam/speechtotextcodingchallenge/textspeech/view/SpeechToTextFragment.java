package com.example.islam.speechtotextcodingchallenge.textspeech.view;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islam.speechtotextcodingchallenge.R;
import com.example.islam.speechtotextcodingchallenge.data.models.CurrentWeather;
import com.example.islam.speechtotextcodingchallenge.textspeech.SpeechToTextContract;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by islam on 24/12/16.
 */

public class SpeechToTextFragment extends Fragment implements SpeechToTextContract.View {
    private final int REQ_CODE_SPEECH_INPUT = 100;
    @Bind(R.id.loading_progressbar)
    ProgressBar mLoadingProgressbar;
    @Bind(R.id.speech_input_textview)
    TextView mSpeechInputTextView;
    @Bind(R.id.error_message_textview)
    TextView mErrorMEssageTextViewl;
    @Bind(R.id.temperature_textview)
    TextView mTemperatureTextView;
    @Bind(R.id.weather_icon_imageview)
    ImageView mWeatherImageView;
    @Bind(R.id.summary_textview)
    TextView mSummaryTextView;
    @Bind(R.id.speak_imagebutton)
    ImageButton mSpeakImageButton;
    SpeechToTextContract.Presenter mPresenter;

    public static SpeechToTextFragment newInstance() {
        return new SpeechToTextFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_speech_to_text, container, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        return root;
    }

    @Override
    public void setPresenter(SpeechToTextContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showWeatherData(CurrentWeather currentWeather) {
        int id = getResources().getIdentifier(currentWeather.getCurrently().getIcon().replace("-", "_"), "drawable", getActivity().getPackageName());
        mWeatherImageView.setImageResource(id);
        mSummaryTextView.setText(currentWeather.getCurrently().getSummary());
        mTemperatureTextView.setText(currentWeather.getCurrently().getTemperature() + getResources().getString(R.string.temp_unit));
    }

    @Override
    public void showLoading() {
        mLoadingProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        mErrorMEssageTextViewl.setVisibility(View.VISIBLE);
        mErrorMEssageTextViewl.setText(errorMsg);
    }

    @Override
    public void hideLoading() {
        mLoadingProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void showNotSupportedErrorMessage() {
        mErrorMEssageTextViewl.setVisibility(View.VISIBLE);
        mErrorMEssageTextViewl.setText(getResources().getString(R.string.not_supported_feature));
    }

    @OnClick(R.id.speak_imagebutton)
    public void speackOnClick() {
        promptSpeechInput();
        reset();
    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            getActivity().startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String speechText = result.get(0);
                    mSpeechInputTextView.setText(speechText);
                    mPresenter.loadWeatherData(speechText);
                }
                break;
            }

        }
    }

    private void reset() {
        mSpeechInputTextView.setText("");
        mWeatherImageView.setImageDrawable(null);
        mSummaryTextView.setText("");
        mTemperatureTextView.setText("");
        mErrorMEssageTextViewl.setVisibility(View.GONE);
    }
}
