package com.righttickk.guitarstoolsandchairs.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;

import java.util.List;

// Data Access Objects
@Dao
public interface RecyclerDao {

    @Insert
    void insert(RecyclerViewItem recyclerViewItem);

    @Update
    void update(RecyclerViewItem recyclerViewItem);

    // Optional
    @Delete
    void delete(RecyclerViewItem recyclerViewItem);

    // Optional
    @Query("DELETE FROM recycler_table")
    void deleteAllRecyclerViewItems();

    @Query("SELECT * FROM recycler_table")
    LiveData<List<RecyclerViewItem>> getAllRecyclerViewItems();
}
