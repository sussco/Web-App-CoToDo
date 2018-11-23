package com.cotodo;

import java.util.*;

public class Project {

    private String name;
    private String description;
    private List<Person> members = new LinkedList<Person>();
    private List<Task> tasks = new LinkedList<Task>();

    public Project(String name, String description){
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
