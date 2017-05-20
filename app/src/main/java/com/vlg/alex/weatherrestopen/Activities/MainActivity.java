package com.vlg.alex.weatherrestopen.Activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.vlg.alex.weatherrestopen.Adapters.WeatherAdapter;
import com.vlg.alex.weatherrestopen.Models.WeatherData;
import com.vlg.alex.weatherrestopen.Network.ApiService;
import com.vlg.alex.weatherrestopen.Network.Constants;
import com.vlg.alex.weatherrestopen.Network.RetrofitClient;
import com.vlg.alex.weatherrestopen.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener{

    public static String TAG = "REST";

    @BindView(R.id.progressBar) ProgressBar pd;
    @BindView(R.id.rv_weather) RecyclerView weatherList;
    @BindView(R.id.placeholder) RelativeLayout placeholder;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    public void getWeatherData(String city){
        pd.setVisibility(View.VISIBLE);
        placeholder.setVisibility(View.INVISIBLE);
        ApiService api = RetrofitClient.getApiService();
        Call<WeatherData> call = api.getData(city,16,"metric", Constants.apiKey);
        call.enqueue(new Callback<WeatherData>() {

            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
                mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                weatherList.setLayoutManager(mLinearLayoutManager);
                adapter = new WeatherAdapter(response.body().getList(),getApplicationContext(),0);
                weatherList.setAdapter(adapter);
                pd.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        getWeatherData(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
