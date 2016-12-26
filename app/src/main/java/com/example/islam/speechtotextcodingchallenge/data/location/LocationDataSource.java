package com.example.islam.speechtotextcodingchallenge.data.location;

import android.location.Address;

import io.reactivex.Observable;


/**
 * Created by islam on 24/12/16.
 */

public interface LocationDataSource {
    Observable<Address> getCurrentLocationObservable();
}
