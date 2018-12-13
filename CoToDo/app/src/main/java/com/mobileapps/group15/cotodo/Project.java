package com.mobileapps.group15.cotodo;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Project {

    private UUID mId;
    private String mTitle;
    private String mDescription;
    private String mOwner;
    private List<Person> members = new LinkedList<Person>();
    private List<Task> tasks = new LinkedList<Task>();


    public String getTitle() { return mTitle; }
    public List<Task> getTasks(){ return tasks;}
    public String getDescription() { return mDescription; }
    public String getOwner() { return mOwner; }
    public UUID getId() {
        return mId;
    }

    public void setTitle(String title) { this.mTitle = title; }
    public void setDescription(String description) { this.mDescription = description; }
    public void setOwner(String owner) { this.mOwner = owner; }

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
        double length = (double) tasks.size();
        double nCompletedTasks = 0;
        if(length == 0) {
            return 0;
        }
        Iterator it = tasks.iterator();
        while(it.hasNext()){
            nCompletedTasks += ((((Task) it.next()).isCompleted()) ? 1.0 : 0.0);
        }
        return (nCompletedTasks /length);
    }

    public int countCompletedTasks(){
        int nCompletedTasks = 0;
        Iterator it = tasks.iterator();
        while(it.hasNext()){
        nCompletedTasks += ((((Task) it.next()).isCompleted()) ? 1 : 0);
    }
        return nCompletedTasks;
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
