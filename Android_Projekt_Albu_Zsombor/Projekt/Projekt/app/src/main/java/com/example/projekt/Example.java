package com.example.projekt;

import com.google.gson.annotations.SerializedName;

public class Example {
   @SerializedName("main")
   WeatherClass weatherObj;

    public WeatherClass getWeather() {
        return weatherObj;
    }


}
