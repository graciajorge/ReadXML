package com.example.sunny.parsexml;

import android.text.Editable;
import android.widget.ArrayAdapter;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class Extends CustomFile and uses an xml file
 * to generate a generic CSV file from elements
 * chosen by the user.
 *
 * Created by Sunny on 6/24/2017.
 */

public class CSVFile extends CustomFile{
    private ArrayAdapter nodeList;
    private XMLDoc xmlDoc;
    private String varRow;


    public CSVFile(){
        xmlDoc=new XMLDoc();
    }

    public void setNodeList(ArrayAdapter arrList){
        nodeList=arrList;
    }

    private ElementList createList(){
        ElementList eList=new ElementList();
        NodeList valueList;
        String name="";


        for(int i=0;i<nodeList.getCount();i++){
            name=nodeList.getItem(i).toString();
            valueList=xmlDoc.getNodeListByName(name);
            if(valueList.getLength()!=0) {
                if(i==0)
                    varRow=name;
                else
                    varRow+=","+name;
                eList.insertEnd(name, valueList);
            }
        }

        return eList;
    }

    @Override
    public void writeFile(Editable text) throws IOException {

    }

    public void writeFile(File text) throws Exception{
        FileOutputStream outStream;
        ElementList eList;
        int fByte;
        String value="";
        File tempF=new File(super.getExtDir(),super.getFilename());

            if (tempF.exists() == false && nodeList.getCount() != 0) {
                try {
                    xmlDoc.setXmlDoc(text);
                    eList=createList();

                    if(eList.head!=null){

                        outStream = new FileOutputStream(tempF, false);
                        outStream.write((varRow).getBytes());//write column names

                        for(int j=0;j<eList.head.getList().getLength();j++){
                            for(int k=0;k<eList.getCount();k++){
                                value=eList.valueByNameIndex(nodeList.getItem(k).toString(),j);

                                if(value.contains(",")){
                                    value="\""+value+"\"";
                                }//if value has commas add double-qoutes

                                if(k==0)
                                    outStream.write(("\n"+value).getBytes());
                                else{
                                    outStream.write((","+value).getBytes());
                                }
                            }
                        }
                    }
                    else{
                        throw new Exception("No elements chosen exist!");
                    }

                }catch(Exception ex){
                    throw new Exception(ex.toString());
                }//some unexpected error was thrown
                outStream.close();
            }//file doesn't already exist
            else if (tempF.exists() == false && nodeList.getCount() == 0) {
                throw new Exception("Please add elements to choose!");
            }//user didn't give element names
            else {
                throw new Exception("File" + this.getFilename() + "already Exists!");
            }//file exists error

    }
}
