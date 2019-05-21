package com.example.heroapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import API.Heroapi;
import Model.Hero;
import URL.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText hname,hdesc;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hname=findViewById(R.id.hname);
        hdesc=findViewById(R.id.hdesc);
        save=findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= hname.getText().toString();
                String desc=hdesc.getText().toString();



                Retrofit retrofit= new Retrofit.Builder()
                        . baseUrl(URL.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Heroapi heroapi=retrofit.create(Heroapi.class);
                Call<Void> heroescall = heroapi.addHero(name,desc);
                heroescall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(MainActivity.this, "Succesfully added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
