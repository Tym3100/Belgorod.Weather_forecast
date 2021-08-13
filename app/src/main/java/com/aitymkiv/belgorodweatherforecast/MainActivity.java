package com.aitymkiv.belgorodweatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.aitymkiv.belgorodweatherforecast.Models.DailyWeatherLocalModel;
import com.aitymkiv.belgorodweatherforecast.Models.HourlyWeatherLocalModel;
import com.aitymkiv.belgorodweatherforecast.Models.RepositoryWeather;
import com.aitymkiv.belgorodweatherforecast.ViewModel.WeatherViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerViewDailyWeather;
    private RecyclerView mRecyclerViewHourlyWeather;
    private TextView mTextViewTemperature;
    private TextView mTextViewDescription;
    private TextView mSelectedDate;
    private static AssetManager assetManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assetManager = getAssets();

        mSwipeRefreshLayout =findViewById(R.id.swipeRefresh);
        mTextViewTemperature = findViewById(R.id.temperature);
        mTextViewDescription = findViewById(R.id.description);
        mSelectedDate = findViewById(R.id.selected_date);

        mRecyclerViewHourlyWeather = findViewById(R.id.recyclerViewHourlyWeather);
        mRecyclerViewDailyWeather = findViewById(R.id.recyclerViewDailyWeather);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        mTextViewTemperature.setText("Loading...");
        mTextViewDescription.setText("Loading...");

        HourlyWeatherRecyclerViewAdapter.OnHourlyClickListener onHourlyClickListener = new HourlyWeatherRecyclerViewAdapter.OnHourlyClickListener() {
            @Override
            public void onHourlyClick(HourlyWeatherLocalModel hourlyWeatherLocalModel, int position) {
                mTextViewTemperature.setText(String.valueOf(hourlyWeatherLocalModel.getTemperature()) + "℃");
                mTextViewDescription.setText(hourlyWeatherLocalModel.getDescription());
                mSelectedDate.setText(hourlyWeatherLocalModel.getTime() + ":00");
            }
        };

        HourlyWeatherRecyclerViewAdapter hourlyWeatherAdapter = new HourlyWeatherRecyclerViewAdapter(onHourlyClickListener);

        DailyWeatherRecyclerViewAdapter.OnDayClickListener onDayClickListener = new DailyWeatherRecyclerViewAdapter.OnDayClickListener() {
            @Override
            public void onDayClick(DailyWeatherLocalModel dailyWeatherLocalModel, int position) {
                hourlyWeatherAdapter.submitList(dailyWeatherLocalModel.getListHourlyWeather());
            }
        };

        DailyWeatherRecyclerViewAdapter dailyWeatherAdapter = new DailyWeatherRecyclerViewAdapter(onDayClickListener);
        mRecyclerViewHourlyWeather.setAdapter(hourlyWeatherAdapter);
        mRecyclerViewDailyWeather.setAdapter(dailyWeatherAdapter);

        mRecyclerViewHourlyWeather.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mRecyclerViewDailyWeather.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerViewDailyWeather.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mRecyclerViewHourlyWeather.setHasFixedSize(true);
        mRecyclerViewDailyWeather.setHasFixedSize(true);

        WeatherViewModel weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        weatherViewModel.getLiveData().observe(this, new Observer<List<DailyWeatherLocalModel>>() {
                    @Override
                    public void onChanged(List<DailyWeatherLocalModel> dailyWeatherLocalModels) {
                        dailyWeatherAdapter.submitList(dailyWeatherLocalModels);
                        hourlyWeatherAdapter.submitList(dailyWeatherLocalModels.get(0).getListHourlyWeather());
                        mSelectedDate.setText(dailyWeatherLocalModels.get(0).getListHourlyWeather().get(0).getTime() + ":00");
                        mTextViewTemperature.setText("" + dailyWeatherLocalModels.get(0).getListHourlyWeather().get(0).getTemperature() + "℃");
                        mTextViewDescription.setText(dailyWeatherLocalModels.get(0).getListHourlyWeather().get(0).getDescription());
                        Toast toast = Toast.makeText(MainActivity.this, "Данные обновились", Toast.LENGTH_LONG);
                        toast.show();
                    }
        });

    }

    @Override
    public void onRefresh() {
        RepositoryWeather.getInstance().getData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public static Bitmap loadImageFromAsset(String name){
        try (InputStream is = assetManager.open(name)) {
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}