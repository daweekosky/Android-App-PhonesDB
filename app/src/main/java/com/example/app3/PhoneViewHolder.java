package com.example.app3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhoneViewHolder extends RecyclerView.ViewHolder {
    private TextView manufacturerTv;

    private TextView modelTv;
    private TextView androidTv;


    public PhoneViewHolder(@NonNull View itemView) {
        super(itemView);
        this.manufacturerTv = itemView.findViewById(R.id.manufacturer_tv);
        this.modelTv = itemView.findViewById(R.id.model_tv);
        this.androidTv = itemView.findViewById(R.id.android_tv);
    }

    public TextView getManufacturerTv() {
        return manufacturerTv;
    }

    public TextView getModelTv() {
        return modelTv;
    }
    public TextView getAndroidTv() {
        return androidTv;
    }
}
