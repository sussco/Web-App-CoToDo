package com.mobileapps.group15.cotodo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int request_code = 1;
    ProjectListFragment mProjectListFragment;
    public static  List<Project> projects = new LinkedList<Project>();
    public static  List<Person> persons = new LinkedList<Person>();

    public static ProjectViewModel mProjectViewModel;
    public static PersonViewModel mPersonViewModel;
    public static TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProjectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        mPersonViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);


        mProjectViewModel.getAllProjects().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable final List<Project> list_projets) {
                // Update the cached copy of the projects.
                projects = new LinkedList<Project>();
                for(Project p : list_projets){
                    projects.add(p);
                }

            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == request_code) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
//                projects.add(new Project(data.getStringExtra("projectTitle"),
//                        data.getStringExtra("projectDescription"), "Me"));
                mProjectViewModel.insert(new Project(data.getStringExtra("projectTitle"),
                        data.getStringExtra("projectDescription"), "Me"));
            }
        }
    }
}
