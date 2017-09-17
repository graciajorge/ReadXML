package com.example.sunny.readxml;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunny.parsexml.CSVFile;
import com.example.sunny.parsexml.CustomFile;
import com.example.sunny.parsexml.FileType;

import java.util.LinkedList;

/**
 * Implements AlertDataPass to pass alert message to dialog, and
 * FileDataPass to pass file to SaveFileDialog.
 * Convert and save xml file as a CSV file from
 * the elements the user chooses through this activity
 * Note: element names are case-sensitive
 *
 * Created by Sunny on 6/22/2017.
 */

public class SaveFileActivity extends Activity implements AlertDataPass,FileDataPass{
    private LinkedList nodeList;
    private ArrayAdapter nodeAdapt;
    private String msg;
    private CustomFile file;


    /*Implement getMessage from interface AlertDataPass*/
    public String getMessage() {
        return msg;
    }


    @Override
    /*Implemented methods from FileDataPass*/
    public CustomFile getTempFile() {
        return file;
    }


    @Override
    public ArrayAdapter getViewList() {return nodeAdapt;}


    /*Check if the element the user inserts is already in the list*/
    public boolean inList(String element){

        for(int i=0;i<nodeAdapt.getCount();i++){
            if(element.compareToIgnoreCase(nodeAdapt.getItem(i).toString())==0)
                return true;
        }

        return false;
    }


    /*User clicks add, in order push element in list*/
    public void onAdd(View v){
        DialogFragment alert= new NotifyDialog();

        EditText element= (EditText)findViewById(R.id.element_name);

        //check if element already exists
        if(!this.inList(element.getText().toString()))
            nodeAdapt.add(element.getText().toString());
        else{
            msg="Element '"+element.getText().toString()+"' already exists in list";
            alert.show(getFragmentManager(),"Alert");
        }//element already in list - show error

        element.setText("");
    }


    //Todo implement a more intuitive way to edit list
    /*User clicks an item on the ListView, then delete that item*/
    public void onClickList(View v){
        TextView element=(TextView)v;
        String name=element.getText().toString();
        if(name!=null) {
            nodeAdapt.remove(name);
            Toast.makeText(getBaseContext(),"'"+name + "' was removed", Toast.LENGTH_SHORT).show();
        }//if not null remove element name from list
    }


    /*User clicks save, therefore display saveFileDialog*/
    public void onSaveCSV(View v){
        file=new CSVFile();

        file.setIntDir(getFilesDir());//internal directory for reference
        file.setExtDir(getExternalFilesDir(null)); //we will save file edited file here!
        file.setfType(FileType.CSV);//dialog must know filetype before save!

        DialogFragment newFragment = new SaveFileDialog();
        newFragment.show(getFragmentManager(),"SaveAsCSV");

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_file_activity);

        ListView list= (ListView)findViewById(R.id.element_view);
        nodeList= new LinkedList<String>();
        nodeAdapt=new ArrayAdapter<String>(this,R.layout.list_view_content,R.id.text_list,nodeList);
        list.setAdapter(nodeAdapt);

    }

}
