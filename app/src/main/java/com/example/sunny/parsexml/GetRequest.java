package com.example.sunny.parsexml;

/**
 * interface with constant values for our http
 * get requests.
 *
 * Created by Sunny on 6/2/2017.
 */

public interface GetRequest {

    public final String GET_NOT_FOUND="EMPTY MESSAGE"; //message is empty

    /*only allowable content types*/
    public final String XML_TXT="text/xml";
    public final String XML_APP="application/xml";


    /**
     * return string content
     * of object variable
     *
     * @return string contents
     */
    abstract String toString();


}
