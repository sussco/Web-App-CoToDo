package com.mobileapps.group15.cotodo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.Iterator;

public class AddPerson extends AppCompatActivity {


    private Person mPerson;
    private EditText mTitleField;
    private EditText mNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        mPerson = new Person();
        mTitleField = (EditText) findViewById(R.id.firstName);
        mTitleField.setText(mPerson.getFirstName());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mPerson.setFirstName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });
        mNameField = (EditText) findViewById(R.id.lastName);
        mNameField.setText(mPerson.getLastName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mPerson.setLastName(s.toString());
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

    public void submitPerson(View v){
            Intent data = new Intent();
            data.putExtra("firstName", mPerson.getFirstName());
            data.putExtra("lastName", mPerson.getLastName());
            setResult(RESULT_OK, data);
//---close the activity---
            finish();
        }

    }



