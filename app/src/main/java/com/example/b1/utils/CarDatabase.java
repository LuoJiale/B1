package com.example.b1.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * 此类实现了RoomDatabase，并使用了单例设计模式，防止重复创建,系统根据此类的注解自动生成增删改查的方法；
 */
@Database(entities = {Car.class},version = 1,exportSchema = false)
public abstract class CarDatabase extends RoomDatabase {
    private static CarDatabase carDatabase;

    public static synchronized CarDatabase getCarDatabase(Context context) {
        if(carDatabase == null){
            carDatabase = Room.databaseBuilder(context,CarDatabase.class,"car").allowMainThreadQueries().build();
        }
        return carDatabase;
    }

    public abstract CarDao getCarDao();
}
