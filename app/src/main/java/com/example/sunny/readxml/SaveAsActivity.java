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
 * Created by Sunny on 6/11/2017.
 */

public class SaveAsActivity extends Activity implements FileDataPass{
    private ArrayAdapter nodeAdapt;
    private List <String> nodeList;
    private String tempFilename;
    private CustomFile file;
    private FileType fType;


    public CustomFile getTempFile(){
        return file;
    }

    @Override
    public ArrayAdapter getViewList() {
        return null;
    }

    public void onSaveXML(View v){
        file=new XMLFile();

        file.setIntDir(getFilesDir());//internal directory for reference
        file.setExtDir(getExternalFilesDir(null)); //we will save file edited file here!
        file.setfType(FileType.XML);//dialog must know filetype before save!

        DialogFragment newFragment = new SaveFileDialog();
        newFragment.show(getFragmentManager(),"SaveAsXML");



    }

    public void onSaveCSV(View v){

        Intent saveCSV;
        saveCSV=new Intent(this,SaveFileActivity.class);
        saveCSV.putExtra("FILE_TYPE","CSV");
        startActivity(saveCSV);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.saveas_activity);




    }

}
