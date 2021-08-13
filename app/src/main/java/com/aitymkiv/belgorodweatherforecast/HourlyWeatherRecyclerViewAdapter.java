package com.aitymkiv.belgorodweatherforecast;


import android.graphics.Color;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.aitymkiv.belgorodweatherforecast.Models.HourlyWeatherLocalModel;
import com.aitymkiv.belgorodweatherforecast.databinding.HourlyWeatherItemBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HourlyWeatherRecyclerViewAdapter extends RecyclerView.Adapter<HourlyWeatherRecyclerViewAdapter.MyViewHolder> {

    private List<HourlyWeatherLocalModel> mListHourlyWeather = new ArrayList<>();
    private final OnHourlyClickListener mOnHourlyClickListener;
    private HourlyWeatherLocalModel selectedWeather;


    interface OnHourlyClickListener{
        void onHourlyClick(HourlyWeatherLocalModel hourlyWeatherLocalModel, int position);
    }

    public HourlyWeatherRecyclerViewAdapter(OnHourlyClickListener onHourlyClickListener) {
        this.mOnHourlyClickListener = onHourlyClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(HourlyWeatherItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(mListHourlyWeather.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHourlyWeather.get(position);
                mOnHourlyClickListener.onHourlyClick(mListHourlyWeather.get(position), position);
                selectedWeather = mListHourlyWeather.get(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListHourlyWeather.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        HourlyWeatherItemBinding binding;

        public MyViewHolder(HourlyWeatherItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HourlyWeatherLocalModel hourlyWeatherLocalModel){
            binding.imageViewHourlyWeather.setImageBitmap(MainActivity.loadImageFromAsset(hourlyWeatherLocalModel.getIcon() + ".png"));
            binding.textViewHour.setText(hourlyWeatherLocalModel.getTime());

        }
    }

    public void submitList(List<HourlyWeatherLocalModel> list){
        mListHourlyWeather = list;
        notifyDataSetChanged();
    }
}
