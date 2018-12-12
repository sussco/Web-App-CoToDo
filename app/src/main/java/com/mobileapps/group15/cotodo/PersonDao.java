package com.mobileapps.group15.cotodo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    void insert(Person person);

    @Query("SELECT * from person_table")
    LiveData<List<Person>> getAllPersons();

    @Update
    void update(Person person);
}
