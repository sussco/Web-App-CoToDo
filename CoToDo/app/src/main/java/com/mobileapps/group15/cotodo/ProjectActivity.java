package com.mobileapps.group15.cotodo;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    static final int request_code1 = 1;
    static final int request_code2 = 2;
    private int projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        Bundle b  = getIntent().getExtras();
        projectId =  b.getInt("id");
        TextView t = (TextView) findViewById(R.id.projName);
        t.setText(MainActivity.projects.get(projectId).getTitle());
        t.setTextColor(getResources().getColor(R.color.black));
        t = findViewById(R.id.Description);
        t.setText(MainActivity.projects.get(projectId).getDescription());

        // get the reference of RecyclerView

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        TaskAdapter customAdapter = new TaskAdapter(ProjectActivity.this, MainActivity.projects.get(projectId), projectId);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        MainActivity.mTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> list_tasks) {
                // Update the cached copy of the projects.
                for(Project proj : MainActivity.projects){
                    proj.setTasks(new LinkedList<Task>());
                    for(Task t : list_tasks){
                        if(t.getId().equals(proj.getId())) {
                            proj.addTask(t);
                        }
                    }
                    proj.updateTasksMembers();
                }
                update();
            }
        });
        MainActivity.mPersonViewModel.getAllPersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable final List<Person> list_persons) {
                // Update the cached copy of the projects.
                for(Project proj : MainActivity.projects){
                    proj.cleanMembers();
                    for(Person p : list_persons){
                        if(p.getIdproject().equals(proj.getId())) {
                            proj.addMember(p);
                        }
                    }
                    proj.updateTasksMembers();
                }
                update();
            }
        });
        TextView title =  findViewById(R.id.projName);
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                // todo faire set title du projet bd.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });

        EditText description =  findViewById(R.id.Description);
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                // todo faire set description du projet bd  .setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too

            }
        });


        update();
    }

    public void goToMainActivity(View v){
        finish();
    }

    public void layClick(View v){
        EditText description =  findViewById(R.id.Description);
        description.setEnabled(false);
        description.setEnabled(true);
        EditText title =  findViewById(R.id.projName);
        title.setEnabled(false);
        title.setEnabled(true);
    }

    public void modifyDescription(View v){
        EditText description =  findViewById(R.id.Description);
        description.setEnabled(true);
    }

    public void modifyTitle(View v){
        EditText title =  findViewById(R.id.projName);
        title.setEnabled(true);
    }

    public void goToAddPerson(View v){
        Intent intent = new Intent(this, AddPerson.class);
        startActivityForResult(intent, request_code2);
    }

    public void goToAddTask(View v){
        Intent intent = new Intent(this, AddTask.class);
        intent.putExtra("id",projectId);
        startActivityForResult(intent, request_code1);
    }

    public void update(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        TaskAdapter taskAdapter = new TaskAdapter(ProjectActivity.this, MainActivity.projects.get(projectId), projectId);
        recyclerView.setAdapter(taskAdapter);
    }

    public void removeProject(View v){
        MainActivity.projects.get(projectId).cleanProject();
        Project p = MainActivity.projects.get(projectId);
        MainActivity.mProjectViewModel.delete(p);
        goToMainActivity(v);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == request_code1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Task tas = new Task(data.getStringExtra("taskName"));
                tas.setId(MainActivity.projects.get(projectId).getId());
                MainActivity.mTaskViewModel.insert(tas);
            }
        }
        if (requestCode == request_code2) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Person p = new Person(data.getStringExtra("firstName"), data.getStringExtra("lastName"));
                p.setIdproject(MainActivity.projects.get(projectId).getId());
                MainActivity.mPersonViewModel.insert(p);
            }
        }
        update();
    }
}
