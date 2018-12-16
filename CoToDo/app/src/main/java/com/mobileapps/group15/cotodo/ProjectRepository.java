package com.mobileapps.group15.cotodo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ProjectRepository {

    private ProjectDao mProjectDao;
    private LiveData<List<Project>> mAllProjects;

    ProjectRepository(Application application) {
        ProjectRoomDatabase db = ProjectRoomDatabase.getDatabase(application);
        mProjectDao = db.projectDao();
        mAllProjects = mProjectDao.getAllProjects();
    }

    LiveData<List<Project>> getAllProjects() {
        return mAllProjects;
    }


    public void insert (Project project) {
        new insertAsyncTask(mProjectDao).execute(project);
    }

    public void update (Project project){
        new updateAsyncTask(mProjectDao).execute(project);
    }

    public void delete (Project project) {
        new deleteAsyncTask(mProjectDao).execute(project);
    }

    private static class insertAsyncTask extends AsyncTask<Project, Void, Void> {

        private ProjectDao mAsyncTaskDao;

        insertAsyncTask(ProjectDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Project... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Project, Void, Void> {

        private ProjectDao mAsyncTaskDao;

        updateAsyncTask(ProjectDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Project... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Project, Void, Void> {

        private ProjectDao mAsyncTaskDao;

        deleteAsyncTask(ProjectDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Project... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
