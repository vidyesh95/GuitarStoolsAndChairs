package com.righttickk.guitarstoolsandchairs.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.righttickk.guitarstoolsandchairs.R;
import com.righttickk.guitarstoolsandchairs.adapter.RecyclerViewAdapter;
import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;

import java.util.List;

public class HomeFragment extends Fragment {

    public static final int URL_REQUEST = 1;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

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

        // RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getAllRecyclerViewItems().observe(this, new Observer<List<RecyclerViewItem>>() {
            @Override
            public void onChanged(List<RecyclerViewItem> recyclerViewItems) {
                adapter.submitList(recyclerViewItems);
            }
        });

        // Optional
        /*new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                homeViewModel.delete(adapter.getRecyclerViewItemAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);*/

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewItem recyclerViewItem) {
                if (recyclerViewItem.getText4() != null) {
                    String url = recyclerViewItem.getText4();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(url));
                    startActivity(browserIntent);
                } else if (recyclerViewItem.getText1() != null) {
                    String url = "https://www.google.com/#q=" + recyclerViewItem.getText1();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(url));
                    startActivity(browserIntent);
                } else {
                    String url = "https://www.amazon.in/s?k=guitar+stool+chair";
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(url));
                    startActivity(browserIntent);
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // On view Destroy Change Theme to default
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = getActivity().getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            if (flags == View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) {
                flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; // use XOR here for remove LIGHT_STATUS_BAR from flags
                getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);
            }
        }
    }
}