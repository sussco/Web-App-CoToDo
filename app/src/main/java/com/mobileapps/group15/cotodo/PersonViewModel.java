package com.mobileapps.group15.cotodo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository mRepository;

    private LiveData<List<Person>> mAllPersons;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PersonRepository(application);
        mAllPersons = mRepository.getAllPersons();
    }

    LiveData<List<Person>> getAllPersons() { return mAllPersons;}

    public void insert(Person person) {mRepository.insert(person);}

    public void update(Person person) {
        mRepository.update(person);
    }
}
