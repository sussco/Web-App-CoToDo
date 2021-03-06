package com.mobileapps.group15.cotodo;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    static final int request_code1 = 1;
    static final int request_code2 = 2;
    private int projectId;
    private int taskId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        TextView t = findViewById(R.id.projName);
        Bundle b  = getIntent().getExtras();
        projectId =  b.getInt("idProject");
        taskId = b.getInt("idTask");
        Log.e("tskMembers",MainActivity.projects.get(projectId).getTasks().get(taskId).getPossibleMembers().toString());
        t.setText(MainActivity.projects.get(projectId).getTasks().get(taskId).getName());
        update();
    }

    @SuppressLint("RestrictedApi")
    public void update(){
        Iterator it = MainActivity.projects.get(projectId).getTasks().get(taskId).getMembers().iterator();
        List<String> list = new ArrayList<String>();
        while(it.hasNext()){
            Person p = (Person)it.next();
            list.add("⊖  " + p.getFirstName()+" " + p.getLastName());
        }


        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView lv = findViewById(R.id.personList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.projects.get(projectId).getTasks().get(taskId).removeMember(
                        MainActivity.projects.get(projectId).getTasks().get(taskId).getMembers().get(position)
                );
                update();
            }});



        Iterator itAdd = MainActivity.projects.get(projectId).getTasks().get(taskId).getPossibleMembers().iterator();
        List<String> listAdd = new ArrayList<String>();
        while(itAdd.hasNext()){
            Person p = (Person)itAdd.next();
            listAdd.add("⊕  " + p.getFirstName()+" " + p.getLastName());
        }


        ArrayAdapter adapterAdd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listAdd);
        ListView lvToAdd = findViewById(R.id.personToAddList);
        lvToAdd.setAdapter(adapterAdd);
        lvToAdd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.projects.get(projectId).getTasks().get(taskId).addMember(
                        MainActivity.projects.get(projectId).getTasks().get(taskId).getPossibleMembers().get(position)
                );
                update();
            }});

        if(MainActivity.projects.get(projectId).getTasks().get(taskId).isCompleted()){
            FloatingActionButton b = findViewById(R.id.undoTaskButton);
            b.setVisibility(View.VISIBLE);
            b.setClickable(true);
            TextView t = findViewById(R.id.undoDoneTask);
            t.setVisibility(View.VISIBLE);


            b = findViewById(R.id.doneTaskButton);
            b.setVisibility(View.INVISIBLE);
            b.setClickable(false);
            t = findViewById(R.id.doneTaskText);
            t.setVisibility(View.INVISIBLE);
        }
        else{
            FloatingActionButton b = findViewById(R.id.doneTaskButton);
            b.setVisibility(View.VISIBLE);
            b.setClickable(true);
            TextView t = findViewById(R.id.doneTaskText);
            t.setVisibility(View.VISIBLE);


            b = findViewById(R.id.undoTaskButton);
            b.setVisibility(View.INVISIBLE);
            b.setClickable(false);
            t = findViewById(R.id.undoDoneTask);
            t.setVisibility(View.INVISIBLE);
        }

    }

    public void goToProjectActivity(View v){
        finish();
    }

    public void deleteTask(View v){
        MainActivity.projects.get(projectId).getTasks().get(taskId).removeAllMembersTask();
        MainActivity.mTaskViewModel.delete(MainActivity.projects.get(projectId).getTasks().get(taskId));
        MainActivity.projects.get(projectId).removeTask(MainActivity.projects.get(projectId).getTasks().get(taskId));
        finish();
    }


    public void doneTask(View v){
        MainActivity.projects.get(projectId).getTasks().get(taskId).setDone(true);
        update();
    }

    public void undoTask(View v){
        MainActivity.projects.get(projectId).getTasks().get(taskId).setDone(false);
        update();

    }
}
