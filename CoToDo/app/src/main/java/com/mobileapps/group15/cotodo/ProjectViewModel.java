package com.mobileapps.group15.cotodo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {

    private ProjectRepository mRepository;

    private LiveData<List<Project>> mAllProjects;

    public ProjectViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ProjectRepository(application);
        mAllProjects = mRepository.getAllProjects();
    }

    LiveData<List<Project>> getAllProjects() { return mAllProjects;}

    public void insert(Project project) {mRepository.insert(project);}

    public void update(Project project) {
        mRepository.update(project);
    }
}
