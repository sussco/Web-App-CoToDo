package com.mobileapps.group15.cotodo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.Iterator;

public class AddTask extends AppCompatActivity {


    private Task mTask;
    private EditText mNameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mTask = new Task();
        mNameField = (EditText) findViewById(R.id.taskName);
        mNameField.setText(mTask.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTask.setName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });

    }

    public void goToProjectActivity(View v){
        finish();
    }

    public void submitTask(View v){
            Intent data = new Intent();
            data.putExtra("taskName", mTask.getName());
            setResult(RESULT_OK, data);
//---close the activity---
            finish();

    }
}


