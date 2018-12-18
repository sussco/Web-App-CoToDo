package com.mobileapps.group15.cotodo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PersonRepository {

    private PersonDao mPersonDao;
    private LiveData<List<Person>> mAllPersons;

    PersonRepository(Application application) {
        ProjectRoomDatabase db = ProjectRoomDatabase.getDatabase(application);
        mPersonDao = db.personDao();
        mAllPersons = mPersonDao.getAllPersons();
    }

    LiveData<List<Person>> getAllPersons() {
        return mAllPersons;
    }


    public void insert (Person person) {
        new insertAsyncTask(mPersonDao).execute(person);
    }
    public void delete (Person person) {
        new deleteAsyncTask(mPersonDao).execute(person);
    }
    public void update (Person person){
        new updateAsyncTask(mPersonDao).execute(person);
    }

    private static class insertAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao mAsyncTaskDao;

        insertAsyncTask(PersonDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao mAsyncTaskDao;

        deleteAsyncTask(PersonDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao mAsyncTaskDao;

        updateAsyncTask(PersonDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
