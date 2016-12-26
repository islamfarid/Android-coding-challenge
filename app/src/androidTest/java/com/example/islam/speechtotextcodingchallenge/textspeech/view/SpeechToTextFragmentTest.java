package com.example.islam.speechtotextcodingchallenge.textspeech.view;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;

import com.example.islam.speechtotextcodingchallenge.R;
import com.example.islam.speechtotextcodingchallenge.utils.EspressoIdlingResource;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by islam on 26/12/16.
 */
@RunWith(AndroidJUnit4.class)
public class SpeechToTextFragmentTest {

    @Rule
    public IntentsTestRule<SpeechToTextActivity> mRule = new IntentsTestRule<>(SpeechToTextActivity.class);
    private String speechText;
    private Instrumentation.ActivityResult result;
    private Intent data;

    private Intent getResultIntent(String speechText) {
        final ArrayList<String> results = new ArrayList<>();
        results.add(speechText);
        Intent intent = new Intent();
        intent.putStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS, results);
        return intent;
    }

    @Test
    public void testRecordedMessageISDisplayedCorrectly() {
        init(speechText = "Weather");
        Espresso.unregisterIdlingResources(EspressoIdlingResource.getIdlingResource());
        intending(hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)).respondWith(result);
        onView(withId(R.id.speak_imagebutton)).check(matches(isClickable()));
        onView(withId(R.id.speak_imagebutton)).perform(click());

        intended(CoreMatchers.allOf(
                hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),
                hasExtraWithKey(RecognizerIntent.EXTRA_LANGUAGE_MODEL)
        ));

        onView(withId(R.id.speech_input_textview)).check(matches(withText(speechText)));
    }

    private void init(String recordedMEssage) {
        data = getResultIntent(recordedMEssage);
        result = new Instrumentation.ActivityResult(Activity.RESULT_OK, data);
    }

    @Test
    public void testRecordedMessageNotAboutWeather_ErrorMessageIsShown() {
        init(speechText = "any_thing");
        Espresso.unregisterIdlingResources(EspressoIdlingResource.getIdlingResource());
        intending(hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)).respondWith(result);
        onView(withId(R.id.speak_imagebutton)).check(matches(isClickable()));
        onView(withId(R.id.speak_imagebutton)).perform(click());

        intended(CoreMatchers.allOf(
                hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),
                hasExtraWithKey(RecognizerIntent.EXTRA_LANGUAGE_MODEL)
        ));

        onView(withId(R.id.error_message_textview)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecordedMessageIsAboutWeather_WeatherIconIsShown() {
        new Instrumentation.ActivityResult(Activity.RESULT_OK, data);
        init(speechText = "weather_any_thing");
        Espresso.registerIdlingResources(EspressoIdlingResource.getIdlingResource());
        intending(hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)).respondWith(result);
        onView(withId(R.id.speak_imagebutton)).check(matches(isClickable()));
        onView(withId(R.id.speak_imagebutton)).perform(click());
        intended(CoreMatchers.allOf(
                hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),
                hasExtraWithKey(RecognizerIntent.EXTRA_LANGUAGE_MODEL)
        ));

        onView(withId(R.id.weather_icon_imageview)).check(matches(hasDrawable()));
    }

    public static BoundedMatcher<View, ImageView> hasDrawable() {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has drawable");
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                return imageView.getDrawable() != null;
            }
        };
    }
}