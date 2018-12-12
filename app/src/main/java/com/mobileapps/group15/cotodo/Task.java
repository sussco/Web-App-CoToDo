package com.mobileapps.group15.cotodo;

import java.util.*;


import java.util.LinkedList;

public class Task {

    private String name;
    private List<Person> members = new LinkedList<Person>() ;
    private boolean completed = false;
    private List<Person> possibleMembers = new LinkedList<Person>() ;


    public Task(String name, LinkedList<Person> possiblePersons){
        this.name = name;
        this.possibleMembers = possiblePersons;
    }

    public Task(){
    }

    public Task(String name){
        this.name = name;
    }

    public Task(String name, LinkedList<Person> possiblePersons, LinkedList<Person> persons){
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
