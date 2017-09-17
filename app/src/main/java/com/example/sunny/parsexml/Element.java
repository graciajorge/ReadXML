package com.example.sunny.parsexml;

import org.w3c.dom.NodeList;

/**
 * Class represents a node in the ElementList
 * doubly linked list.
 *
 * Created by Sunny on 6/25/2017.
 */

public class Element {
    private String name;
    private NodeList list;
    public Element prev;
    public Element next;


    /**
     * constructor takes element name, and element value list as nodelist.
     *
     * @param name as String
     * @param list as NodeList
     */
    public Element(String name,NodeList list){
        this.name=name;
        this.list=list;
        prev=null;
        next=null;
    }


    /**
     * return name of element.
     *
     * @return name as String
     */
    public String getName(){return name;}


    /**
     * return list of values.
     *
     * @return list as NodeList
     */
    public NodeList getList(){return list;}


    /**
     * return a specific value in value list given
     * a certain index.
     *
     * @param index as integer
     * @return textContent as String
     */
    public String findValueByIndex(int index){
        return list.item(index).getTextContent();
    }

}
