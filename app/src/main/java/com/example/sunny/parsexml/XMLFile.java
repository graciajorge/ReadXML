package com.example.sunny.parsexml;

import android.text.Editable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Class extends CustomFile to save a XML
 * file
 *
 * Created by Sunny on 6/18/2017.
 */
public class XMLFile extends CustomFile{

    /**
     * write file using a FileInputStream, and check if the filename
     * already exists in our external application directory.
     *
     * @param text as FileInputStream
     * @throws java.io.IOException
     */
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
