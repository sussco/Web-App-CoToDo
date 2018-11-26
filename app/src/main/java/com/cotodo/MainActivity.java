package com.cotodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import java.util.*;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    // TODO: add the cells which represents projects: (use this link to code it):
    // http://tutos-android-france.com/material-design-recyclerview-et-cardview/

    // TODO: go to this website:
    // https://developer.android.com/reference/android/app/Activity
    // and in the section Starting Activities and Getting Results, add to this code the
    // possibility to have a return from the add_project activity.

    private List<Project> projects = new LinkedList<Project>();

    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goToAddProject(View v){
        Intent intent = new Intent(this, AddProject.class);
        startActivity(intent);
    }

}
