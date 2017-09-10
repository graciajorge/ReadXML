package com.example.sunny.parsexml;

import android.text.Editable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Sunny on 6/18/2017.
 */

public class XMLFile extends CustomFile{

    public XMLFile(){

    }

    public void writeFile(Editable text) throws java.io.IOException{
        FileOutputStream outStream;

        //outStream= new FileOutputStream(new File(getExternalFilesDir(null),"temp_xml.xml"));
        //TODO check that we have enough space to read and write

        outStream= new FileOutputStream(new File(super.getIntDir(),super.getFilename()),false);//append is false

        for(int i=0;i<text.length();i++){
            outStream.write(text.charAt(i));
        }
        outStream.close();

    }

    public void writeFile(FileInputStream text) throws java.io.IOException{
        FileOutputStream outStream;
        int fByte;
        File tempF=new File(super.getExtDir(),super.getFilename());

        if(tempF.exists()==false) {
            outStream = new FileOutputStream(tempF,false);

            while ((fByte = text.read()) != -1) {
                outStream.write(fByte);
            }
            outStream.close();
        }//file doesn't already exist
        else{
            throw new java.io.IOException("File"+ this.getFilename()+"already Exists!");
        }//file exists error


    }



}
