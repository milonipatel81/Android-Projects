package com.example.edithapp.room;
import  androidx.room.*;

import java.util.List;
@Dao
public interface CategoryDao {
    @Insert
    Long insert(Category category);
    @Query("SELECT * FROM `Category` ORDER BY `id` DESC")
    List<Category> getAllCategory();
    @Query("SELECT * FROM `Category` WHERE `id` =:id")
    Category getCategory(int id);
    @Query("SELECT * FROM `Category` WHERE `type` =:TYPE")
    List<Category> getByTypeCategory(String TYPE);
    @Update
    void update(Category category);
    @Delete
    void delete(Category category);
}
