/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sunny.parsexml;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.net.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Sunny
 */
public class HttpMessage extends Thread implements GetRequest{

    private URL url;
    private static StringBuilder errMsg;
    private HttpURLConnection src;
    private BufferedReader xmlStr;
    private static List<String> strList;
    private final String filename="temp_xml.xml";
    private static int statusCode;
    private String username;
    private String pswd;

    /**
     * Constructor to set default necessary
     * variables.
     *
     */
    public HttpMessage() {

        url = null;
        errMsg = new StringBuilder();
        src = null;
        strList=new ArrayList<String>();

    }


    public void setUrl(String urlText) throws MalformedURLException{
        url=new URL(urlText);
    }


    public URL getUrl(){
        return url;
    }


    public void setUsername(String username){
        this.username=username;
    }


    public String getUsername(){
        return this.username;
    }


    public void setPswd(String pswd){
        this.pswd=pswd;
    }


    public String getPswd(){
        return this.pswd;
    }


    public void setStatusCode(int statusCode){
        this.statusCode=statusCode;
    }


    public int getStatusCode(){
        return this.statusCode;
    }


    public StringBuilder getErrMsg(){return errMsg;}




    public void run(){
        String type="";
        String line="";
        FileOutputStream file;
        try {
            src.setConnectTimeout(5000);
            this.statusCode=src.getResponseCode();//find our status code
            type=src.getContentType();
            if(statusCode==200&&(type.equals(XML_TXT))) {
                Reader temp=new InputStreamReader(src.getInputStream());
                xmlStr=new BufferedReader(temp);
                while((line=xmlStr.readLine())!=null){
                    strList.add(line);
                }
            }//we need OK status code and is xml
            else if(statusCode==200&&(!type.equals(XML_TXT)&&!type.equals(XML_APP))){
                throw new Exception("Response "+statusCode+" is not XML type: '"+type+"' and '"+XML_TXT+"'");
            }//status OK but NOT xml

        }
        catch(SocketTimeoutException e){
            errMsg.append(e.toString());
        }
        catch(Exception e){
            errMsg.append(e.toString());
        }


    }


    /**
     * Request connection and remember to
     * catch exception.
     *
     * @throws Exception IOException
     */
    public void requestMsg() throws SocketTimeoutException,Exception{

        src=(HttpURLConnection)url.openConnection();
        src.setRequestMethod("GET");
        /*Clean lists for each connection*/
        strList.clear();
        errMsg.setLength(0);//reset string builder

        start();//thread to use android network features
        join();//wait for thread to die


    };


    public List <String> getList(){
        return strList;
    }


    public String toString(){
        return strList.toString();
    }


}
