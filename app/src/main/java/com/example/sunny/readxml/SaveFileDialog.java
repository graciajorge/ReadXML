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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sunny.parsexml.CSVFile;
import com.example.sunny.parsexml.CustomFile;
import com.example.sunny.parsexml.FileType;
import com.example.sunny.parsexml.XMLFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * Display a text box for user to write
 * filename to save and save the file as
 * directed
 *
 * Created by Sunny on 6/12/2017.
 */
public class SaveFileDialog extends DialogFragment implements AlertDataPass{

    private FileDataPass fileData;
    private CustomFile cFile;
    private String msg;


    /*Constructor to initialize customeFile*/
    public SaveFileDialog(){
        cFile=new XMLFile();
    }


    /*Display an alert if an error occurs while trying to save*/
    private void createAlertDialog(String msg){
        this.msg="it was false";//message to pass to alert

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Alert");
        builder.setMessage(msg.replace("java.lang.Exception:",""));
        builder.setPositiveButton(R.string.ok,null);
        builder.setNegativeButton(R.string.cancel,null);
        builder.create();
        builder.show();

    }


    /*Save file as xml file*/
    private void saveXML(String filename){
        XMLFile save=new XMLFile();
        cFile.setFilename(filename);//we assume file directories are set

        try {
            FileInputStream outStr = new FileInputStream(new File(cFile.getIntDir(), cFile.getTempFilename()));
            save.setExtDir(cFile.getExtDir());
            save.setFilename(cFile.getFilename());
            save.setfType(FileType.XML);

            save.writeFile(outStr);
            outStr.close();
        }
        catch(Exception ex){
            createAlertDialog(ex.toString());
        }



    }


    /*Save file as CSV file*/
    private void saveCSV(String filename){
        CSVFile csv=new CSVFile();
        cFile.setFilename(filename);//we assume file directories are set
        ArrayAdapter eList=fileData.getViewList();

        csv.setNodeList(eList);
        try {
            File xmlFile=new File(cFile.getIntDir(),cFile.getTempFilename());
            csv.setExtDir(cFile.getExtDir());
            csv.setfType(FileType.CSV);
            csv.setFilename(filename);
            csv.writeFile(xmlFile);
        }
        catch(Exception ex){

            createAlertDialog(ex.getMessage());
            //Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_LONG).show();

        }
    }


    /*Obtain file data from intent to save*/
    public void onAttach(Context temp){
        super.onAttach(temp);
        Activity parent= (Activity)temp;
        fileData=(FileDataPass) parent;

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View save;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        save=inflater.inflate(R.layout.save_file_dialog, null);
        builder.setView(save)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog,int id){
                        EditText filename=(EditText)getDialog().findViewById(R.id.filename);
                        cFile=fileData.getTempFile();
                        switch(cFile.getfType()){
                            case XML:
                                saveXML(filename.getText().toString());
                                break;
                            case CSV:
                                saveCSV(filename.getText().toString());
                                break;
                            default:
                                Toast.makeText(getActivity(),"File type is not supported!",Toast.LENGTH_SHORT).show();

                        }




                    }


                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getDialog().cancel();

                    }
                });


        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();

        //Check directories have been added
        if(cFile.getExtDir()!=null&&cFile.getIntDir()!=null){
            cFile.setIntDir(getActivity().getFilesDir());
            cFile.setExtDir(getActivity().getExternalFilesDir(null));
        }//get directories from calling activity

    }

    /*Overridden messages from interfaces*/

    /*Override message return for 'AlertDataPass'. String is used to set message in dialog fragment*/
    @Override
    public String getMessage() {
        return this.msg;
    }
}
