package com.example.sunny.readxml;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Sunny on 6/7/2017.
 */

public class NotifyDialog extends DialogFragment {
    private AlertDataPass alert;

    public void onAttach(Context temp){
        super.onAttach(temp);
        Activity act= (Activity) temp;
        alert=(AlertDataPass) act;

    }



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
