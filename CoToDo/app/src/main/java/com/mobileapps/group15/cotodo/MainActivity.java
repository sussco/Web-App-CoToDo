package com.mobileapps.group15.cotodo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int request_code = 1;
    public static  List<Project> projects = new LinkedList<Project>();
    public static  List<Person> persons = new LinkedList<Person>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        ProjectAdapter projectAdapter = new ProjectAdapter(MainActivity.this);
        recyclerView.setAdapter(projectAdapter); // set the Adapter to RecyclerView
    }

   /* List<Project> dummyProjects = new ArrayList<Project>(0);
        dummyProjects.add(new Project("0", "Project1", "a project", "Group 15"));
        dummyProjects.add(new Project("1", "Project2", "another project", "Group 15"));

        mProjectListFragment.setProjects(dummyProjects);*/

    public void goToAddProject(View v){
        Intent intent = new Intent(this, AddProject.class);
        startActivityForResult(intent, request_code);
    }

    @Override
    public void onResume(){
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ProjectAdapter projectAdapter = new ProjectAdapter(MainActivity.this);
        recyclerView.setAdapter(projectAdapter);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == request_code) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                projects.add(new Project(data.getStringExtra("projectTitle"),
                        data.getStringExtra("projectDescription"), "Me"));

            }
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ProjectAdapter projectAdapter = new ProjectAdapter(MainActivity.this);
        recyclerView.setAdapter(projectAdapter); // set the Adapter to RecyclerView
    }
}
