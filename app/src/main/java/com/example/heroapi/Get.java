package com.example.heroapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import API.Heroapi;
import Model.Hero;
import URL.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Get extends AppCompatActivity {
    private TextView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        lv=findViewById(R.id.lv);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Heroapi heroapi= retrofit.create(Heroapi.class);
        Call<List<Hero>> listCall=heroapi.getHero();
        listCall.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                if (!response.isSuccessful()){
                    lv.setText("Code:" + response.code());
                    return;
                }
                List<Hero> heroList = response.body();
                for ( Hero hero: heroList){
                    String content ="";
                    content +="ID:" + hero.getID() + "/n";
                    content +="Name:" + hero.getName() + "/n";
                    content +="description:" + hero.getDesc() + "/n";
                    lv.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
lv.setText("Error" + t.getMessage());
            }
        });

    }
}
DFRGDNRLGDRKLIJG