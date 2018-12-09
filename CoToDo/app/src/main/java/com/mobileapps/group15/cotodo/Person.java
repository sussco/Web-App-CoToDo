package com.mobileapps.group15.cotodo;

public class Person {

    private String firstName;
    private String lastName;

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



}
