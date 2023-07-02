package com.example.app3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhonesAdapter extends RecyclerView.Adapter<PhoneViewHolder> {
    private LayoutInflater inflater;

    public PhonesAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
        this.phoneList = null;
    }

    private List<Phone> phoneList;

    public interface OnPhoneClickListener{
        void onPhoneClick(Phone phone);
    }

    private OnPhoneClickListener onPhoneClickListener;

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = this.inflater.inflate(R.layout.phone_info,parent,false);
        PhoneViewHolder holder = new PhoneViewHolder(rootView);
        if(this.phoneList != null && this.onPhoneClickListener != null){
            holder.itemView.setOnClickListener(view -> {
                Phone phone = this.phoneList.get(holder.getAdapterPosition());
                this.onPhoneClickListener.onPhoneClick(phone);
            });
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        Phone phone = this.phoneList.get(position);
        holder.getManufacturerTv().setText(phone.getManufacturer());
        holder.getModelTv().setText(phone.getModel());
        holder.getAndroidTv().setText(phone.getAndroidVersion());
    }

    @Override
    public int getItemCount() {
        return this.phoneList != null ? this.phoneList.size() : 0;
    }

    public void setPhoneList(List<Phone> phoneList){
        this.phoneList = phoneList;
        notifyDataSetChanged();
    }

    public List<Phone> getPhoneList(){
        return phoneList;
    }

    public OnPhoneClickListener getOnPhoneClickListener() {
        return onPhoneClickListener;
    }

    public void setOnPhoneClickListener(OnPhoneClickListener onPhoneClickListener) {
        this.onPhoneClickListener = onPhoneClickListener;
    }
}
