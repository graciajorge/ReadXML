package com.example.sunny.readxml;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


/**
 * Display general progress bar in a dialog to
 * block user interaction with the ui while
 * running long processes
 *
 * Created by Sunny on 9/16/2017.
 */
public class ProgressBarDialog extends DialogFragment{

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View progress;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.wait);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        progress=inflater.inflate(R.layout.progressbar_dialog, null);
        builder.setView(progress);

        return builder.create();
    }

}
