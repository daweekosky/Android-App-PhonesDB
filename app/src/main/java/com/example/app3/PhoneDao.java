package com.example.app3;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhoneDao {
    @Insert(onConflict = ABORT)
    void insert(Phone phone);

    @Update
    void update(Phone phone);

    @Query("select * from phones")
    LiveData<List<Phone>> findAllPhones();

    @Delete
    void delete(Phone phone);
}
