package com.mobileapps.group15.cotodo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert
    void insert(Project project);

    @Query("SELECT * from project_table")
    LiveData<List<Project>> getAllProjects();

    @Update
    void update(Project project);

    @Delete
    void delete(Project project);
}
