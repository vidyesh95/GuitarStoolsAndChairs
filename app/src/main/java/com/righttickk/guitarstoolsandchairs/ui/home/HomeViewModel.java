package com.righttickk.guitarstoolsandchairs.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;
import com.righttickk.guitarstoolsandchairs.repository.RecyclerViewRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private RecyclerViewRepository recyclerViewRepository;
    private LiveData<List<RecyclerViewItem>> allRecyclerViewItems;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        recyclerViewRepository = new RecyclerViewRepository(application);
        allRecyclerViewItems = recyclerViewRepository.getAllRecyclerViewItems();
    }

    // Optional
    public void insert(RecyclerViewItem recyclerViewItem) {
        recyclerViewRepository.insert(recyclerViewItem);
    }

    // Optional
    public void update(RecyclerViewItem recyclerViewItem) {
        recyclerViewRepository.update(recyclerViewItem);
    }

    // Optional
    public void delete(RecyclerViewItem recyclerViewItem) {
        recyclerViewRepository.delete(recyclerViewItem);
    }

    // Optional
    public void deleteAllRecyclerViewItems() {
        recyclerViewRepository.deleteAllRecyclerViewItems();
    }

    public LiveData<List<RecyclerViewItem>> getAllRecyclerViewItems() {
        return allRecyclerViewItems;
    }
}