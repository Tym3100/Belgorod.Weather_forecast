package com.aitymkiv.belgorodweatherforecast;


import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aitymkiv.belgorodweatherforecast.Models.DailyWeatherLocalModel;
import com.aitymkiv.belgorodweatherforecast.databinding.DailyWeatherItemBinding;

import java.util.ArrayList;
import java.util.List;

public class DailyWeatherRecyclerViewAdapter extends RecyclerView.Adapter<DailyWeatherRecyclerViewAdapter.DailyWeatherViewHolder> {

    private List<DailyWeatherLocalModel> mListDailyWeather = new ArrayList<>();
    private final OnDayClickListener mOnDayClickListener;
    private int selectedPosition;

    interface OnDayClickListener{
        void onDayClick(DailyWeatherLocalModel dailyWeatherLocalModel, int position);
    }

    public DailyWeatherRecyclerViewAdapter(OnDayClickListener onDayClickListener) {
        this.mOnDayClickListener = onDayClickListener;
    }

    @NonNull
    @Override
    public DailyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DailyWeatherViewHolder(DailyWeatherItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailyWeatherViewHolder holder, int position) {
        if(selectedPosition == position){
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }
        else{
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.bind(mListDailyWeather.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListDailyWeather.get(position).getListHourlyWeather();
                mOnDayClickListener.onDayClick(mListDailyWeather.get(position), position);
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListDailyWeather.size();
    }
    public static class DailyWeatherViewHolder extends RecyclerView.ViewHolder{

        DailyWeatherItemBinding binding;

        public DailyWeatherViewHolder(DailyWeatherItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DailyWeatherLocalModel dailyWeatherModel){
            binding.dayOfWeek.setText(dailyWeatherModel.getDayOfWeek());
            binding.date.setText(dailyWeatherModel.getDate());
            binding.imageViewWeatherStatus.setImageBitmap(MainActivity.loadImageFromAsset(dailyWeatherModel.getIcon() + ".png"));
            binding.temperature.setText(String.valueOf(dailyWeatherModel.getTemperature()) + "℃");
        }
    }

    public void submitList(List<DailyWeatherLocalModel> listDailyWeather){
        this.mListDailyWeather = listDailyWeather;
        notifyDataSetChanged();
    }
}
