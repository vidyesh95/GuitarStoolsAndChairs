package com.righttickk.guitarstoolsandchairs.ui.more;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DisclaimerDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Disclaimer")
                .setMessage("This mobile app is intended for informational and research purpose " +
                        "only. \nThis application is not a substitute for online store and only " +
                        "provides user with variety of options for purchase. We are not " +
                        "responsible for any bad link. \nYou expressly understand and agree that " +
                        "your use of the application and the data from the application is at " +
                        "your sole risk and that the application is provided as is and as " +
                        "available without warranty of any kind. ")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
