package com.mobileapps.group15.cotodo;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity(tableName = "project_table")
@TypeConverters({UUIDTypeConverter.class})
public class Project {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private UUID mId;
    @ColumnInfo(name = "title")
    private String mTitle;
    @ColumnInfo(name = "description")
    private String mDescription;
    @ColumnInfo(name = "owner")
    private String mOwner;


    @Ignore
    private List<Person> members = new LinkedList<Person>();

    @Ignore
    private List<Task> tasks = new LinkedList<Task>();


    public String getTitle() { return mTitle; }
    public List<Task> getTasks(){ return tasks;}
    public String getDescription() { return mDescription; }
    public String getOwner() { return mOwner; }
    public UUID getId() { return mId; }
    public List<Person> getMembers() {
        return members;
    }

    public void setTitle(String title) { this.mTitle = title; }
    public void setDescription(String description) { this.mDescription = description; }
    public void setOwner(String owner) { this.mOwner = owner; }
    public void setId(UUID uuid) { this.mId = uuid;}
    public void setMembers(List<Person> members) {
        this.members = members;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Project(String title, String description,
                   String owner) {
        mId = UUID.randomUUID();
        mTitle = title;
        mDescription = description;
        mOwner = owner;
    }
    public Project() {
        mId = UUID.randomUUID();
        mTitle = null;
        mDescription = null;
        mOwner = null;
    }

    public boolean removeMember(Person person){
        return this.members.remove(person);
    }


    public void addMember(Person person){
        this.members.add(person);
    }

    public boolean addTask(Task task){
        Iterator it = tasks.iterator();
        while(it.hasNext()){
            if(((Task)it.next()).getName() == task.getName()){
                return false;
            }
        }
        return this.tasks.add(task);
    }

    public boolean addPerson(Person p){
        Iterator it = tasks.iterator();
        while(it.hasNext()){
            ((Task)it.next()).addPossibleMember(p);
        }
        return true;
    }

    public double giveProgress(){
        int length = tasks.size();
        int nCompletedTasks = 0;
        if(length == 0) {
            return 1;
        }
        Iterator it = tasks.iterator();
        while(it.hasNext()){
            nCompletedTasks += ((((Task) it.next()).isCompleted()) ? 1 : 0);
        }
        return ((double) nCompletedTasks) / ((double) length);
    }

    public boolean removetask(Task task){
        return this.tasks.remove(task);
    }



    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Project)
            return (((Project)obj).getId().equals(mId));
        else
            return false;
    }
}
