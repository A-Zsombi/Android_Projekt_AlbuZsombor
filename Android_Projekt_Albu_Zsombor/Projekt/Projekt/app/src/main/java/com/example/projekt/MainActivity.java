package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;
    String url = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}";
    String apikey = "fcd2c236893559071f53147e2c72132f";
    LocationManager manager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);

    }

    public void getweather(View v){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi myapi=retrofit.create(weatherapi.class);
        Call<Example> examplecall=myapi.getweather(et.getText().toString().trim(),apikey);
        examplecall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code()==404){
                    Toast.makeText(MainActivity.this,"Please Enter a valid City",Toast.LENGTH_LONG).show();
                }
                else if(!(response.isSuccessful())){
                    Toast.makeText(MainActivity.this,response.code()+" ",Toast.LENGTH_LONG).show();
                    return;
                }
                Example mydata=response.body();
                WeatherClass weatherObj =mydata.getWeather();

                Double temp= weatherObj.getTemp();
                Integer temperature=(int)(temp-273.15);
                tv.setText(String.valueOf(temperature)+" C");

                Double temp11= weatherObj.getTemp();
                Integer temperature11=(int)(((temp11-273.15)*1.8)+32);
                tv6.setText(String.valueOf(temperature11)+" F");

                Integer temp2= weatherObj.getHumidity();
                tv2.setText(String.valueOf(temp2)+" %");

                Double temp3= weatherObj.getTempMin();
                Integer temperature3=(int)(temp3-273.15);
                tv3.setText(String.valueOf(temperature3)+" C");

                Double temp33= weatherObj.getTempMin();
                Integer temperature33=(int)(((temp33-273.15)*1.8)+32);
                tv7.setText(String.valueOf(temperature33)+" F");

                Double temp4= weatherObj.getTempMax();
                Integer temperature4=(int)(temp4-273.15);
                tv4.setText(String.valueOf(temperature4)+" C");

                Double temp44= weatherObj.getTempMax();
                Integer temperature44=(int)(((temp44-273.15)*1.8)+32);
                tv8.setText(String.valueOf(temperature44)+" F");

                Integer temp5= weatherObj.getPressure();
                tv5.setText(String.valueOf(temp5)+" hPa");

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }
}
