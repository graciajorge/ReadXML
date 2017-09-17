package com.example.sunny.parsexml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


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
 * XMLDoc to save file as native xml document and
 * obtain values in list by element name.
 *
 * Created by Sunny on 6/2/2017.
 */
public class XMLDoc {
    private Document xml;
    private List <String> strList;

    /**
     * constructor initializes new string list
     */
    XMLDoc(){
        strList=new ArrayList<String>();
    }


    /**
     * set xml document from inputStream
     *
     * @param tempStr as InputStream
     * @throws Exception when error
     */
    public void setXmlDoc(InputStream tempStr) throws Exception{
        DocumentBuilderFactory docBF;
        DocumentBuilder docB;

        docBF= DocumentBuilderFactory.newInstance();
        docB=docBF.newDocumentBuilder();
        xml=docB.parse(tempStr);


    }


    /**
     * set xml document from inputSource
     *
     * @param tempStr as InputSource
     * @throws Exception when error
     */
    public void setXmlDoc(InputSource tempStr) throws Exception{
        DocumentBuilderFactory docBF;
        DocumentBuilder docB;

        docBF= DocumentBuilderFactory.newInstance();
        docB=docBF.newDocumentBuilder();
        xml=docB.parse(tempStr);



    }


    /**
     * set xml document from file
     *
     * @param f as file
     * @throws SAXException error from document builder
     * @throws IOException file to parse doesn't exist
     * @throws ParserConfigurationException fail when parsing
     */
    public void setXmlDoc(File f) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory docBF;
        DocumentBuilder docB;

        docBF= DocumentBuilderFactory.newInstance();
        docB=docBF.newDocumentBuilder();

        if(f.exists()) {
            xml = docB.parse(f);
        }
        else{
            throw new IOException("File to parse doesn't Exist");
        }

    }


    /**
     * return xml document
     *
     * @return xml as Document
     */
    public Document getXMLDoc(){
        return xml;
    }


    /**
     * return nodeList from element name specified
     *
     * @param name as String
     * @return eList as NodeList
     */
    public NodeList getNodeListByName(String name){

        NodeList eList=xml.getElementsByTagName(name);

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


    /**
     * return content in xml document as some string
     *
     * @return document as string
     */
    public String toString(){
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            StringWriter sw = new StringWriter();
            t.transform(new DOMSource(xml),new StreamResult(sw));
            return sw.toString();
        }catch(TransformerConfigurationException ex){
            return "Error configuration xml doc to string!";
        }catch(TransformerException ex){
            return "Error transformation xml doc to string";
        }

    }





}
