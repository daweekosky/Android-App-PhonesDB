package com.example.app3;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhonesViewModel extends AndroidViewModel {
    private PhonesRepository repository;
    private LiveData<List<Phone>> listPhones;

    public PhonesViewModel(@NonNull Application application) {
        super(application);
        this.repository = new PhonesRepository(application);
        this.listPhones = this.repository.getListPhoness();
    }
    public LiveData<List<Phone>> getPhoneList() {
        return listPhones;
    }

    public void insert(Phone phone){
        this.repository.insert(phone);
    }

    public void update(Phone phone){
        this.repository.update(phone);
    }

    public void delete(Phone phone){
        this.repository.delete(phone);
    }
}
