package com.mobileapps.group15.cotodo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;

    private LiveData<List<Task>> mAllTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks() { return mAllTasks;}

    public void insert(Task task) {mRepository.insert(task);}

    public void update(Task task) {
        mRepository.update(task);
    }

    public void delete(Task task) { mRepository.delete(task);}
}
