package com.example.islam.speechtotextcodingchallenge.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by islam on 23/12/16.
 */

public class Currently {
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("precipIntensity")
    @Expose
    private String precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    private String precipProbability;
    @SerializedName("temperature")
    @Expose
    private String temperature;
    @SerializedName("apparentTemperature")
    @Expose
    private String apparentTemperature;
    @SerializedName("dewPoint")
    @Expose
    private String dewPoint;
    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("windSpeed")
    @Expose
    private String windSpeed;
    @SerializedName("windBearing")
    @Expose
    private String windBearing;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("cloudCover")
    @Expose
    private String cloudCover;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("ozone")
    @Expose
    private String ozone;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(String precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public String getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(String precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(String apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(String windBearing) {
        this.windBearing = windBearing;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getOzone() {
        return ozone;
    }

    public void setOzone(String ozone) {
        this.ozone = ozone;
    }

}
