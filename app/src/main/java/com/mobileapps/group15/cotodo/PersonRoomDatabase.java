package com.mobileapps.group15.cotodo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Person.class}, version=1)
public abstract class PersonRoomDatabase extends RoomDatabase{

    public abstract PersonDao personDao();

    private static volatile PersonRoomDatabase INSTANCE;

    static PersonRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PersonRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PersonRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                }
            };
}