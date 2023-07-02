package com.example.app3;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhonesRepository {

    private PhoneDao dao;
    private LiveData<List<Phone>> listPhoness;

    public PhonesRepository(Application application) {
        PhonesDatabase database = PhonesDatabase.getDatabase(application);
        this.dao = database.phoneDao();
        this.listPhoness = dao.findAllPhones();
    }

    public PhoneDao getDao() {
        return dao;
    }

    public void setDao(PhoneDao dao) {
        this.dao = dao;
    }

    public LiveData<List<Phone>> getListPhoness() {
        return listPhoness;
    }

    public void setListPhoness(LiveData<List<Phone>> listPhoness) {
        this.listPhoness = listPhoness;
    }

    public void insert(Phone phone){
        PhonesDatabase.databaseWriteExecutor.execute(() -> this.dao.insert(phone));
    }
    public void update(Phone phone){
        PhonesDatabase.databaseWriteExecutor.execute(() -> this.dao.update(phone));
    }

    public void delete(Phone phone) {
        PhonesDatabase.databaseWriteExecutor.execute(() -> this.dao.delete(phone));
    }
}

