package com.mobileapps.group15.cotodo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.*;


import java.util.LinkedList;

@Entity(primaryKeys = {"project","id"},tableName = "task_table")
@TypeConverters({UUIDTypeConverter.class})
public class Task {



    @ColumnInfo(name = "project")
    @NonNull
    private UUID Id;

    @ColumnInfo(name = "id")
    @NonNull
    private UUID mIdtask;



    @ColumnInfo(name = "name")
    private String name;

    @Ignore
    private List<Person> members = new LinkedList<Person>() ;

    @ColumnInfo(name = "completed")
    private boolean completed = false;

    @Ignore
    private List<Person> possibleMembers = new LinkedList<Person>() ;


    public Task(String name, LinkedList<Person> possiblePersons){
        this.mIdtask = UUID.randomUUID();
        this.name = name;
        this.possibleMembers = possiblePersons;
    }

    public Task(){
        this.mIdtask = UUID.randomUUID();
    }

    public Task(String name){
        this.mIdtask = UUID.randomUUID();
        this.name = name;
    }

    public Task(String name, LinkedList<Person> possiblePersons, LinkedList<Person> persons){
        this.mIdtask = UUID.randomUUID();
        this.name = name;
        this.possibleMembers = possiblePersons;
        this.members = persons;
    }

    public boolean addMember(Person person){
        // TODO : add a sort per alphabet feature
        if(possibleMembers.contains(person)){
            possibleMembers.remove(person);
            return this.members.add(person);
        }
        return false;
    }

    public boolean addPossibleMember(Person person){
        // TODO : add a sort per alphabet feature
        return this.possibleMembers.add(person);
    }

    public void addPossibleMembers(List<Person> persons){
        // TODO : add a sort per alphabet feature
        Iterator it = persons.iterator();
        while(it.hasNext()){
            possibleMembers.add(((Person)it.next()));
        }
    }

    public boolean removeMember(Person person){
        if(this.members.remove(person)){
            return this.possibleMembers.add(person);
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getIdtask() {
        return mIdtask;
    }
    public void setIdtask(UUID mIdtask) {
        this.mIdtask = mIdtask;
    }
    public UUID getId() {
        return Id;
    }
    public void setId(UUID id) {
        Id = id;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> persons) {
        this.members = persons;
    }

    public List <Person> getPossibleMembers(){return possibleMembers;}

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
