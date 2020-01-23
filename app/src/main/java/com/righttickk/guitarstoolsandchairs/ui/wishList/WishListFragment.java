package com.righttickk.guitarstoolsandchairs.ui.wishList;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.AppBarLayout;
import com.righttickk.guitarstoolsandchairs.R;

public class WishListFragment extends Fragment {

    private WishListViewModel wishListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wishListViewModel =
                ViewModelProviders.of(this).get(WishListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wish_list, container, false);

        // Change theme according to AppBarLayout State
        AppBarLayout mAppBarLayout = root.findViewById(R.id.header_container_wish_list);
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

        /*final TextView textView = root.findViewById(R.id.text_wish_list);
        wishListViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
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