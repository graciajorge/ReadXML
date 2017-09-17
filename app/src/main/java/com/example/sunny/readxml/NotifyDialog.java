package com.example.sunny.readxml;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Creates a general dialog that displays
 * 'alert' in title with a string message that is passed from
 * some intent.
 * !Class using this dialog fragment must implement 'AlertDataPass'!
 * Created by Sunny on 6/7/2017.
 */
public class NotifyDialog extends DialogFragment {
    private AlertDataPass alert;

    /*method specific to dialog fragment used to get alert msg from the intent*/
    public void onAttach(Context temp){
        super.onAttach(temp);
        Activity act= (Activity) temp;
        alert=(AlertDataPass) act;

    }


    /*onCreateDialog sets necessary settings and returns dialog to calling activity*/
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Alert");
        /*getMessage using interface to pass data from calling Activity*/
        builder.setMessage(alert.getMessage());

        builder.setPositiveButton(R.string.ok,null);
        builder.setNegativeButton(R.string.cancel,null);
        return builder.create();
    }
}
