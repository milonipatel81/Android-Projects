package com.example.edithapp.room;
import android.content.Context;
import androidx.room.*;
// Database 
@Database(entities = {Category.class,Transection.class},version = 1)
public abstract class CategoryDatabase extends  RoomDatabase {
    // Dao instance
    public abstract CategoryDao categoryDao();
    public abstract TransectionDao transectionDao();
    // Database Instance
    private static CategoryDatabase INSTANCE;
    // Method to return instance
    public static CategoryDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) { 
            INSTANCE = 
            Room.databaseBuilder(
                context.getApplicationContext(), 
                CategoryDatabase.class, 
                "user-database").allowMainThreadQueries().build(); }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
