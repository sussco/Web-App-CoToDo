package com.mobileapps.group15.cotodo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AddProject extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference projectsNode;

    private Project mProject;
    private EditText mTitleField;
    private EditText mDescriptionField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        mProject = new Project();
        mTitleField = (EditText) findViewById(R.id.projectTitle);
        mTitleField.setText(mProject.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mProject.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });

        mTitleField = (EditText) findViewById(R.id.projectDescription);
        mTitleField.setText(mProject.getDescription());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mProject.setDescription(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });

    }

    public void goToMainActivity(View v){
        finish();
    }

    public void submitProject(View v){

        mDatabase = FirebaseDatabase.getInstance().getReference();
        projectsNode = mDatabase.child("projects");
        DatabaseReference newProjectRef = projectsNode.push();
        //String projectId = newProjectRef.getKey();
        Iterator it = MainActivity.projects.iterator();
        boolean projectNameTaken = false;
        while (it.hasNext()){
            if(((Project)it.next()).getTitle() == mProject.getTitle()) projectNameTaken = true;
        }
        if(projectNameTaken){
            // print
        }
        else{
            Intent data = new Intent();
            data.putExtra("projectTitle", mProject.getTitle());
            data.putExtra("projectDescription", mProject.getDescription());
            setResult(RESULT_OK, data);
            newProjectRef.setValue(mProject);
//---close the activity---
            finish();
        }

    }
}


