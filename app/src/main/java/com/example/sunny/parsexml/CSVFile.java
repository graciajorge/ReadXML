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


    /**
     * Constructor to initialize an xmlDoc variable
     * to hold existing xml document to be converted
     *
     */
    public CSVFile(){
        xmlDoc=new XMLDoc();
    }

    /**
     * set nodeList which consists of element names which belong to
     * xml file.
     *
     * @param arrList as ArrayAdapter
     */
    public void setNodeList(ArrayAdapter arrList){
        nodeList=arrList;
    }


    /**
     * Create elementList from element names, to be used
     * when creating our CSV file.
     *
     * @return eList as ElementList
     */
    private ElementList createList(){
        ElementList eList=new ElementList();
        NodeList valueList;
        String name="";


        for(int i=0;i<nodeList.getCount();i++){
            name=nodeList.getItem(i).toString(); //element name
            valueList=xmlDoc.getNodeListByName(name); //get list from element name
            if(valueList.getLength()!=0) {
                if(i==0)
                    varRow=name;
                else
                    varRow+=","+name;
                eList.insertEnd(name, valueList);//push list and element name to end
            }
        }

        return eList;
    }


    /**
     * pass file with xml data to be
     * converted to csv using the element list
     * created from the elements specified by the user.
     *
     * @param text as File
     * @throws Exception when error
     */
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
