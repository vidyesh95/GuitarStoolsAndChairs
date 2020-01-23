package com.righttickk.guitarstoolsandchairs.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.righttickk.guitarstoolsandchairs.R;
import com.righttickk.guitarstoolsandchairs.adapter.RecyclerViewAdapter;
import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /*homeViewModel.getRecyclerViewList.observe(this, new Observer<ArrayList<RecyclerViewItem>>() {
            @Override
            public void onChanged(ArrayList<RecyclerViewItem> recyclerViewItems) {
                mAdapter.notifyDataSetChanged();
            }
        });*/

        // Change theme according to AppBarLayout State
        AppBarLayout mAppBarLayout = root.findViewById(R.id.header_container_home);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                } else if (isShow) {
                    isShow = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        int flags = getActivity().getWindow().getDecorView().getSystemUiVisibility(); // get current flag
                        flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; // use XOR here for remove LIGHT_STATUS_BAR from flags
                        getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);
                    }
                }
            }
        });

        ArrayList<RecyclerViewItem> recyclerViewList = new ArrayList<>();
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_1,
                "On-Stage DT7500 Guitarist Stool With Foot Rest - Black",
                "₹2,699", "Bajaao.com"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_2,
                "lbanez IMC50FS Chair Guitar Stand",
                "₹2,662", "Bajaao.com"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_3,
                "GTB RED Furniture - Adjustable Stool-Doctor/Kitchen Stool/Office Stool/" +
                        "Chair/Cafeteria Stool/Bar Stool",
                "₹1,199", "Amazon.com"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_4,
                "MBTC Essilor PU 360° Height Adjustable Kitchen/Office/Bar Stool in White",
                "₹2,199", "Amazon.com"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_1, "Line 1", "Line 2", "Line 3"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_2, "Line 4", "Line 5", "Line 6"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_3, "Line 7", "Line 8", "Line 9"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_4, "Line 10", "Line 11", "Line 12"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_1, "Line 13", "Line 14", "Line 15"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_2, "Line 16", "Line 17", "Line 18"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_3, "Line 19", "Line 20", "Line 21"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_4, "Line 22", "Line 23", "Line 24"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_1, "Line 25", "Line 26", "Line 27"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_2, "Line 28", "Line 29", "Line 30"));
        recyclerViewList.add(new RecyclerViewItem(R.drawable.guitar_item_3, "Line 31", "Line 32", "Line 33"));

        mRecyclerView = root.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(),2);

        mAdapter = new RecyclerViewAdapter(recyclerViewList);

        /*mAdapter = new RecyclerViewAdapter(this,homeViewModel.getRecyclerViewList.getValue());*/

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // On view Destroy Change Theme to default
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = getActivity().getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            if (flags==View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) {
                flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; // use XOR here for remove LIGHT_STATUS_BAR from flags
                getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);
            }
        }
    }
}