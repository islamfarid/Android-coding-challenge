package com.example.islam.speechtotextcodingchallenge.data.location;

import android.content.Context;
import android.location.Address;

import com.google.android.gms.location.LocationRequest;
import com.patloew.rxlocation.RxLocation;

import io.reactivex.Observable;

/**
 * Created by islam on 24/12/16.
 */

public class LocationProvider implements LocationDataSource {
    Context context;

    public LocationProvider(Context context) {
        this.context = context;
    }

    @Override
    public Observable<Address> getCurrentLocationObservable() {
        RxLocation rxLocation = new RxLocation(context);

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(1);

        return rxLocation.location().updates(locationRequest)
                .flatMap(location ->
                        rxLocation.geocoding().fromLocation(location).toObservable());

    }
}
