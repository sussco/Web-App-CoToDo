package com.mobileapps.group15.cotodo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

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

    public Person(String fName, String lName){
        firstName = fName;
        lastName = lName;
    }

    public Person(){}

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

}
