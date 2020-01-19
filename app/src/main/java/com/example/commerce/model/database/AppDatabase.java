package com.example.commerce.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.commerce.model.product.Response;
import com.example.commerce.model.dao.ResponseDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {Response.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ResponseDao mResponseDao();
    private static AppDatabase INSTANCE;
    public final static Executor responseExecutor = Executors.newFixedThreadPool(4);

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class,
                            "app_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
