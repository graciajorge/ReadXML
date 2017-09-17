package com.example.sunny.readxml;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.sunny.parsexml.CustomFile;
import com.example.sunny.parsexml.FileType;
import com.example.sunny.parsexml.XMLFile;

import java.util.List;

/**
 * Display multiple options for the user to save
 * the retrieved xml. Maybe implement some file manager
 *
 * Note: Files are saved to external app directory
 *
 * Created by Sunny on 6/11/2017.
 */

public class SaveAsActivity extends Activity implements FileDataPass{
    private ArrayAdapter nodeAdapt;
    private List <String> nodeList;
    private String tempFilename;
    private CustomFile file;
    private FileType fType;

    /*Start Interface Implementation for FileDataPass*/
    public CustomFile getTempFile(){
        return file;
    }

    public ArrayAdapter getViewList() {
        return null;
    }
    /*End Interface implementation*/


    /*User clicks button to save output as xml therefore display dialog to enter filename and save*/
    public void onSaveXML(View v){
        file=new XMLFile();

        file.setIntDir(getFilesDir());//internal directory for reference
        file.setExtDir(getExternalFilesDir(null)); //we will save file edited file here!
        file.setfType(FileType.XML);//dialog must know filetype before save!

        DialogFragment newFragment = new SaveFileDialog();
        newFragment.show(getFragmentManager(),"SaveAsXML");



    }

    /*User clicks button to convert xml to csv so display new activity*/
    public void onSaveCSV(View v){

        Intent saveCSV;
        saveCSV=new Intent(this,SaveFileActivity.class);
        saveCSV.putExtra("FILE_TYPE","CSV");
        startActivity(saveCSV);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saveas_activity); //set pre-defined layout
    }

}
