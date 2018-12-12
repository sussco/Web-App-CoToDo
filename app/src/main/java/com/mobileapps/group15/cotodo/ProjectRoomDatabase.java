package com.mobileapps.group15.cotodo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Project.class, Task.class, Person.class}, version=1)
public abstract class ProjectRoomDatabase extends RoomDatabase{

    public abstract ProjectDao projectDao();
    public abstract PersonDao personDao();
    public abstract TaskDao taskDao();

    private static volatile ProjectRoomDatabase INSTANCE;

    static ProjectRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProjectRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProjectRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                }
            };



}