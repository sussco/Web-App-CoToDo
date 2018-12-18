package com.mobileapps.group15.cotodo;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ArrayTypeConverter {



    @TypeConverter
    public static List<UUID> stringToIntegerList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<UUID>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String integerListToString(List<UUID> uuids) {
        Gson gson = new Gson();
        return gson.toJson(uuids);
    }
}