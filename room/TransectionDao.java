package com.example.edithapp.room;
import androidx.lifecycle.LiveData;
import  androidx.room.*;

import java.util.List;

@Dao
public interface TransectionDao {
    @Insert
    Long insert(Transection transection);

    @Query("SELECT * FROM `Transection`")
    List<Transection> getAllCategory();


    @Query("SELECT date || ',' || sum(amount) || ',' || Category.type FROM `Transection`,Category where Category.name=Transection.category group by  date,Category.type ORDER BY Category.type DESC")
    List<String> getMonthlyRepo();

    @Query("SELECT date || ',' || amount || ',' || Category.type || ',' || Category.name FROM `Transection`,Category where Category.name=Transection.category")
    List<String> getyearlyRepo();

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);
}
