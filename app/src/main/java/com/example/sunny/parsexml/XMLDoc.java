package com.example.sunny.parsexml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Sunny on 6/2/2017.
 */

public class XMLDoc {
    private Document XMLDoc;
    private List <String> strList;

    //TODO CREATE XML DOC FOR XML DOCUMENT
    XMLDoc(){
        strList=new ArrayList<String>();
    }

    public void setXmlDoc(InputStream tempStr) throws Exception{
        DocumentBuilderFactory docBF;
        DocumentBuilder docB;

        docBF= DocumentBuilderFactory.newInstance();
        docB=docBF.newDocumentBuilder();
        XMLDoc=docB.parse(tempStr);


    }

    public void setXmlDoc(InputSource tempStr) throws Exception{
        DocumentBuilderFactory docBF;
        DocumentBuilder docB;

        docBF= DocumentBuilderFactory.newInstance();
        docB=docBF.newDocumentBuilder();
        XMLDoc=docB.parse(tempStr);



    }

    public void setXmlDoc(File f) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory docBF;
        DocumentBuilder docB;

        docBF= DocumentBuilderFactory.newInstance();
        docB=docBF.newDocumentBuilder();

        if(f.exists()) {
            XMLDoc = docB.parse(f);
        }
        else{
            throw new IOException("File to parse doesn't Exist");
        }

    }


    public Document getXMLDoc(){
        return XMLDoc;
    }

    public NodeList getNodeListByName(String name){

        NodeList eList=this.XMLDoc.getElementsByTagName(name);

        //List <Node> nodes=new ArrayList<Node>();
        //int i=to.getLength();

        /*for(int i=0;i<to.getLength();i++){
            xml.append(to.item(i).getTextContent());
        }*/

        /*if(xml!=null){
            return xml.toString();

        }
        else{
            return GET_NOT_FOUND;
        }*/
        //return to.item(0).getTextContent();
        return eList;
    }

    public String toString(){
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            StringWriter sw = new StringWriter();
            t.transform(new DOMSource(XMLDoc),new StreamResult(sw));
            return sw.toString();
        }catch(TransformerConfigurationException ex){
            return "Error configuration xml doc to string!";
        }catch(TransformerException ex){
            return "Error transformation xml doc to string";
        }

    }





}
