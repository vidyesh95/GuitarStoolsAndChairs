package com.righttickk.guitarstoolsandchairs.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.righttickk.guitarstoolsandchairs.dao.RecyclerDao;
import com.righttickk.guitarstoolsandchairs.database.RecyclerDatabase;
import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;

import java.util.List;

public class RecyclerViewRepository {

    private RecyclerDao recyclerDao;

    private LiveData<List<RecyclerViewItem>> allRecyclerViewItems;

    public RecyclerViewRepository(Application application) {
        RecyclerDatabase database = RecyclerDatabase.getInstance(application);
        recyclerDao = database.recyclerDao();
        allRecyclerViewItems = recyclerDao.getAllRecyclerViewItems();
    }
}
