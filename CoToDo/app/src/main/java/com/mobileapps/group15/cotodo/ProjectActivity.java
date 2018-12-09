package com.mobileapps.group15.cotodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
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
        t.setText(MainActivity.projects.get(projectId).getTitle() + " task list");
    }

    public void goToMainActivity(View v){
        finish();
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
        Iterator it = MainActivity.projects.get(projectId).getTasks().iterator();
        List<String> list = new ArrayList<String>();
        while(it.hasNext()){
            Task t = (Task)it.next();
            list.add(t.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView lv = findViewById(R.id.taskList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                intent.putExtra("idProject", projectId);
                intent.putExtra("idTask", position);
                startActivity(intent);
            }});
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
                tas.addPossibleMembers(MainActivity.persons);
                MainActivity.projects.get(projectId).addTask(tas);
                update();
            }
        }
        if (requestCode == request_code2) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Person p = new Person(data.getStringExtra("firstName"), data.getStringExtra("lastName"));
                MainActivity.persons.add(p);
                Iterator it = MainActivity.projects.iterator();
                while(it.hasNext()){
                    ((Project)it.next()).addPerson(p);
                }

            }
        }
    }
}
