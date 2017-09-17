/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sunny.parsexml;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.net.*;

import java.util.ArrayList;
import java.util.List;


/**
 * class to handle android http xml messages. Implements
 * getRequest. Run request on a separate thread.
 *
 * Note: Android won't allow web usage on main thread!
 *
 * @author Sunny
 */
public class HttpMessage implements GetRequest{

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


    /**
     * set url for our web service call
     *
     * @param urlText as String
     * @throws MalformedURLException when bad url
     */
    public void setUrl(String urlText) throws MalformedURLException{
        url=new URL(urlText);
    }


    /**
     * get web service url
     *
     * @return url as URL
     */
    public URL getUrl(){
        return url;
    }


    /**
     * set username for basic authentication header
     *
     * @param username as string
     */
    public void setUsername(String username){
        this.username=username;
    }


    /**
     * get username as string
     *
     * @return username as String
     */
    public String getUsername(){
        return username;
    }


    /**
     * set password for basic authentication header
     *
     * @param pswd as String
     */
    public void setPswd(String pswd){
        this.pswd=pswd;
    }


    /**
     * get password as string
     *
     * @return pswd as String
     */
    public String getPswd(){ return pswd; }


    /**
     * override the status code for message return
     *
     * @param statusCode as integer
     */
    public void setStatusCode(int statusCode){
        this.statusCode=statusCode;
    }


    /**
     * get status code from http response
     *
     * @return statusCode as integer
     */
    public int getStatusCode(){
        return statusCode;
    }


    /**
     * get error message if any
     *
     * @return errMsg as StringBuilder
     */
    public StringBuilder getErrMsg(){return errMsg;}


    /**
     * get content string from message returned
     *
     * @return strList as List<String>
     */
    public List <String> getList(){
        return strList;
    }


    /**
     * toString overridden from interface getrequest to output
     * web service response as string.
     *
     * @return string content
     */
    public String toString(){
        return strList.toString();
    }


    /**
     * call service request
     * and read response or set err message.
     *
     */
    public void sendRequest(){
        String type="";
        String line="";
        FileOutputStream file;
        try {
            src.setConnectTimeout(5000);
            this.statusCode=src.getResponseCode();//find our status code
            type=src.getContentType();
            if(statusCode==200&&(type.equals(XML_TXT)||type.equals(XML_APP))) {
                Reader temp=new InputStreamReader(src.getInputStream());
                xmlStr=new BufferedReader(temp);
                while((line=xmlStr.readLine())!=null){
                    strList.add(line);
                }
            }//we need OK status code and is xml
            else if(statusCode==200&&(!type.equals(XML_TXT)&&!type.equals(XML_APP))){
                throw new Exception("Response "+statusCode+" Type:'"+type+"' is NOT XML type: '"+XML_APP+"' or '"+XML_TXT+"'");
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

        sendRequest();
    }


}
