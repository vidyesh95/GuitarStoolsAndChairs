package com.righttickk.guitarstoolsandchairs.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<RecyclerViewItem>> mRecyclerViewList;

    public HomeViewModel() {
        /*mRecyclerViewList.getValue(mRecyclerViewList);*/
    }

    /*public LiveData<ArrayList<RecyclerViewItem>> getRecyclerViewList {
        return mRecyclerViewList;
    }*/
}