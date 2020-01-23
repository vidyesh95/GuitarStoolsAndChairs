package com.righttickk.guitarstoolsandchairs.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;

import java.util.List;

@Dao
public interface RecyclerDao {

    @Insert
    void insert(RecyclerViewItem recyclerViewItem);

    @Update
    void update(RecyclerViewItem recyclerViewItem);

    @Query("SELECT * FROM recycler_table")
    LiveData<List<RecyclerViewItem>> getAllRecyclerViewItems();
}
