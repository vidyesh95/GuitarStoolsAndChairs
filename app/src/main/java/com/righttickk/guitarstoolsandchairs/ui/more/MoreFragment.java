package com.righttickk.guitarstoolsandchairs.ui.more;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.AppBarLayout;
import com.righttickk.guitarstoolsandchairs.BuildConfig;
import com.righttickk.guitarstoolsandchairs.R;

public class MoreFragment extends Fragment {

    private MoreViewModel moreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moreViewModel =
                ViewModelProviders.of(this).get(MoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_more, container, false);
        // Change theme according to AppBarLayout State
        AppBarLayout mAppBarLayout = root.findViewById(R.id.header_container_more);
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

        /*final TextView textView = root.findViewById(R.id.text_more);*/
        final TextView textView2 = root.findViewById(R.id.whats_app_text);
        final TextView textView3 = root.findViewById(R.id.email_text);
        final TextView textView4 = root.findViewById(R.id.contact_text);
        final TextView textView5 = root.findViewById(R.id.rate_text);
        final TextView textView6 = root.findViewById(R.id.share_text);
        final TextView textView7 = root.findViewById(R.id.privacy_text);
        final TextView textView8 = root.findViewById(R.id.disclaimer_text);
        /*moreViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsApp();
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email();
            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email();
            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate();
            }
        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privacy();
            }
        });
        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disclaimer();
            }
        });
        return root;
    }

    private void whatsApp() {
        PackageManager pm = getActivity().getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            String phoneNumberWithCountryCode = "+919970450462";
            String message = "Hello, I am 'user' of your app Guitar Stools And Stands and I" +
                    " would like to enquire you about ";
            Uri uri = Uri.parse(
                    String.format("https://api.whatsapp.com/send?phone=%s&text=%s",
                            phoneNumberWithCountryCode, message));
            Intent whatsAppIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(whatsAppIntent);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getActivity(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);
        }
    }

    private void email() {
        Intent webEmailIntent = new Intent();
        webEmailIntent.setAction(Intent.ACTION_SENDTO);
        webEmailIntent.setData(Uri.parse("mailto:"));
        webEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"righttickk@gmail.com"});
        webEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Guitar stools and chair enquiry");
        webEmailIntent.putExtra(Intent.EXTRA_TEXT, "I would like to enquire you about");
        // Verify the original intent will resolve to at least one activity
        if (webEmailIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(webEmailIntent);
        }
    }


    private void rate() {
        Uri uri = Uri.parse("market://details?id=" + getActivity().getApplication().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getApplication().getPackageName())));
        }
    }


    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
        sendIntent.setType("text/plain");
        // Verify the original intent will resolve to at least one activity
        if (sendIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    private void privacy() {
        Intent privacyIntent = new Intent();
        privacyIntent.setAction(Intent.ACTION_VIEW);
        privacyIntent.setData(Uri.parse("https://www.freeprivacypolicy.com/privacy/view/173742ed4ca887b64192d94ec27ff319"));
        // Verify the original intent will resolve to at least one activity
        if (privacyIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(privacyIntent);
        }
    }

    private void disclaimer() {
        DisclaimerDialog disclaimerDialog = new DisclaimerDialog();
        disclaimerDialog.show(getActivity().getSupportFragmentManager(), "disclaimer dialog");
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