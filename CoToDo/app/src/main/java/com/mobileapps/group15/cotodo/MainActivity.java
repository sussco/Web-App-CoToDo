package com.mobileapps.group15.cotodo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    static final int request_code = 1;
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

        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        ProjectAdapter projectAdapter = new ProjectAdapter(MainActivity.this);
        recyclerView.setAdapter(projectAdapter); // set the Adapter to RecyclerView
        mProjectViewModel.getAllProjects().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable final List<Project> list_projets) {
                // Update the cached copy of the projects.
                projects = new LinkedList<Project>();
                for(Project p : list_projets){
                    projects.add(p);
                }
                onResume();
            }
        });

        mTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> list_tasks) {
                // Update the cached copy of the projects.
                for(Project proj : projects){
                    proj.setTasks(new LinkedList<Task>());
                    for(Task t : list_tasks){
                        if(t.getId().equals(proj.getId())) {
                            proj.addTask(t);
                        }
                    }
                }
                onResume();
            }
        });

        mPersonViewModel.getAllPersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable final List<Person> list_persons) {
                // Update the cached copy of the projects.
                for(Project proj : projects){
                    proj.cleanMembers();
                    for(Person p : list_persons){
                        if(p.getIdproject().equals(proj.getId())) {
                            proj.addMember(p);
                        }
                    }
                    proj.updateTasksMembers();
                }
            }
        });
        if(!projects.isEmpty()){
            TextView tv = findViewById(R.id.noProject);
            tv.setVisibility(View.INVISIBLE);
        }
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
        if(!projects.isEmpty()){
            TextView tv = findViewById(R.id.noProject);
            tv.setVisibility(View.INVISIBLE);
        }
        else{
            TextView tv = findViewById(R.id.noProject);
            tv.setVisibility(View.VISIBLE);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == request_code) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                mProjectViewModel.insert(new Project(data.getStringExtra("projectTitle"),
                        data.getStringExtra("projectDescription"), "Me"));

            }
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ProjectAdapter projectAdapter = new ProjectAdapter(MainActivity.this);
        recyclerView.setAdapter(projectAdapter); // set the Adapter to RecyclerView
        if(!projects.isEmpty()){
            TextView tv = findViewById(R.id.noProject);
            tv.setVisibility(View.INVISIBLE);
        }
    }
}
