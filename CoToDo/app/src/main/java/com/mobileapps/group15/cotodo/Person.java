package com.mobileapps.group15.cotodo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity(primaryKeys = {"firstName","lastName"},tableName = "person_table")
@TypeConverters({UUIDTypeConverter.class})
public class Person {

    @NonNull
    @ColumnInfo(name = "firstName")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "project")
    private UUID Id;

    @ColumnInfo(name = "tasks")
    @TypeConverters(ArrayTypeConverter.class)
    private List<UUID> mTasks;


    public Person(String fName, String lName){
        mTasks = new LinkedList<UUID>();
        firstName = fName;
        lastName = lName;
    }

    public Person(){mTasks = new LinkedList<UUID>();}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public List<UUID> getTasks() {
        return mTasks;
    }

    public void setTasks(List<UUID> mTasks) {
        this.mTasks = mTasks;
    }

    public void addTask(UUID t){
        Log.e("addTask",String.valueOf(!mTasks.contains(t)));
        if(!mTasks.contains(t)){
            this.mTasks.add(t);
            MainActivity.mPersonViewModel.update(this);
        }
    }

    public void removeTask(UUID t){
        Log.e("removeTask",String.valueOf(mTasks.contains(t)));
        if(mTasks.contains(t)){
            this.mTasks.remove(t);
            MainActivity.mPersonViewModel.update(this);
        }

    }

}
