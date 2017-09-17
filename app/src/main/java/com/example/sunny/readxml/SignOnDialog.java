package com.example.sunny.readxml;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


/**
 * Display signon dialog so user can set his username
 * and password for basic authorization header.
 *
 * Created by Sunny on 6/4/2017.
 */

public class SignOnDialog extends DialogFragment{

    private SignOnDataPass passSignOn;


    /*obtain parameter from the activity*/
    public void onAttach(Context temp){
        super.onAttach(temp);
        Activity act= (Activity)temp;
        passSignOn=(SignOnDataPass) act;

    }


    /*Pass password to the activity*/
    public void passPswd(String pswd){
        passSignOn.passPassword(pswd);
    }


    /*pass username to the activity*/
    public void passUser(String user){
        passSignOn.passUsername(user);
    }


    /*get password and username if it is already set*/
    public void defaultSignOn(View v){

        EditText pswd=(EditText)v.findViewById(R.id.password);
        EditText user=(EditText)v.findViewById(R.id.username);

        pswd.setText(passSignOn.getPassword());
        user.setText(passSignOn.getUsername());

    }


    /*once user clicks ok send back the username and password*/
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View signOn;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        signOn=inflater.inflate(R.layout.login_credentials, null);
        builder.setView(signOn)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog,int id){
                EditText pswd=(EditText)getDialog().findViewById(R.id.password);
                EditText user=(EditText)getDialog().findViewById(R.id.username);
                SignOnDialog.this.passPswd(pswd.getText().toString());
                SignOnDialog.this.passUser(user.getText().toString());

            }


        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SignOnDialog.this.getDialog().cancel();
            }
        });

        this.defaultSignOn(signOn);//remember password and username

        return builder.create();
    }


}
