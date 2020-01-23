package com.righttickk.guitarstoolsandchairs.repository;

import android.app.Application;
import android.os.AsyncTask;

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

    public void insert(RecyclerViewItem recyclerViewItem) {
        new InsertRecyclerViewItemAsyncTask(recyclerDao).execute(recyclerViewItem);
    }

    public void update(RecyclerViewItem recyclerViewItem) {
        new UpdateRecyclerViewItemAsyncTask(recyclerDao).execute(recyclerViewItem);
    }

    public LiveData<List<RecyclerViewItem>> getAllRecyclerViewItems() {
        return allRecyclerViewItems;
    }

    private static class InsertRecyclerViewItemAsyncTask extends AsyncTask<RecyclerViewItem, Void, Void> {

        private RecyclerDao recyclerDao;

        private InsertRecyclerViewItemAsyncTask(RecyclerDao recyclerDao) {
            this.recyclerDao = recyclerDao;
        }

        @Override
        protected Void doInBackground(RecyclerViewItem... recyclerViewItems) {
            recyclerDao.insert(recyclerViewItems[0]);
            return null;
        }
    }

    private static class UpdateRecyclerViewItemAsyncTask extends AsyncTask<RecyclerViewItem, Void, Void> {

        private RecyclerDao recyclerDao;

        private UpdateRecyclerViewItemAsyncTask(RecyclerDao recyclerDao) {
            this.recyclerDao = recyclerDao;
        }

        @Override
        protected Void doInBackground(RecyclerViewItem... recyclerViewItems) {
            recyclerDao.update(recyclerViewItems[0]);
            return null;
        }
    }
}
