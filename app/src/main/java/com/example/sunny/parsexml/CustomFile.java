package com.example.sunny.parsexml;

import android.text.Editable;
import android.widget.ArrayAdapter;

import java.io.File;

/**
 * Created by Sunny on 6/18/2017.
 */

public abstract class CustomFile {
    private File file;
    private String filename;
    private File intDir;
    private File extDir;
    private FileType fType;
    private final String tempFilename="temp_file_out.file";

    CustomFile(){
        file=null;
        filename="temp_file_out.file";//default name
    }

    public void setFilename(String filename){
        this.filename=filename;
    }

    public String getFilename(){
        return filename;
    }

    public String getTempFilename(){return this.tempFilename;}

    public void setFile(File file){
        this.file=file;
    }

    public File getFile(){
        return file;
    }

    public void setExtDir(File dir){
        this.extDir=dir;
    }

    public File getExtDir(){
        return extDir;
    }

    public void setIntDir(File dir){this.intDir=dir;}

    public File getIntDir(){return this.intDir;}

    public void setfType(FileType f){this.fType=f;}

    public FileType getfType(){return this.fType;}

    public abstract void writeFile(Editable text) throws java.io.IOException;



}
