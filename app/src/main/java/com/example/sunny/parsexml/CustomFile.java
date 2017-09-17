package com.example.sunny.parsexml;

import android.text.Editable;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class contains basic information for
 * custom files.
 *
 * Created by Sunny on 6/18/2017.
 */

public class CustomFile {
    private String filename;
    private File intDir;
    private File extDir;
    private FileType fType;
    private final String tempFilename="temp_file_out.file";

    /**
     * constructor to initialize files to null
     */
    CustomFile(){
        intDir=null;
        extDir=null;
    }


    /**
     * set the filename as string
     *
     * @param filename as string
     */
    public void setFilename(String filename){
        this.filename=filename;
    }


    /**
     * return the filename as string
     *
     * @return filename as string
     */
    public String getFilename(){
        return filename;
    }


    /**
     * get temporary filename as string
     *
     * @return tempFilename as string
     */
    public String getTempFilename(){return tempFilename;}


    /**
     * set external directory for application
     *
     * @param dir as File
     */
    public void setExtDir(File dir){
        extDir=dir;
    }


    /**
     * return external directory of applicaiton
     *
     * @return extDir as File
     */
    public File getExtDir(){
        return extDir;
    }


    /**
     * set internal directory for application
     *
     * @param dir as file
     */
    public void setIntDir(File dir){intDir=dir;}


    /**
     * return external directory for appliation
     *
     * @return intDir as file
     */
    public File getIntDir(){return intDir;}


    /**
     * set file type of this custom file
     *
     * @param f as FileType
     */
    public void setfType(FileType f){fType=f;}


    /**
     * return file type of custom file
     *
     * @return fType as FileType
     */
    public FileType getfType(){return fType;}


    /**
     * write to file by passing an editable text
     * from text box.
     *
     * @param text as Editable
     * @throws IOException when error
     */
    public void writeFile(Editable text) throws IOException {
        FileOutputStream outStream;

        //TODO check that we have enough space to read and write

        outStream= new FileOutputStream(new File(getIntDir(),getFilename()),false);//append is false

        for(int i=0;i<text.length();i++){
            outStream.write(text.charAt(i));
        }
        outStream.close();
    }



}
