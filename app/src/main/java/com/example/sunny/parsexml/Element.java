package com.example.sunny.parsexml;

import org.w3c.dom.NodeList;

/**
 * Created by Sunny on 6/25/2017.
 */

public class Element {
    private String name;
    private NodeList list;
    public Element prev;
    public Element next;

    public Element(String name,NodeList list){
        this.name=name;
        this.list=list;
        prev=null;
        next=null;
    };

    public String getName(){return name;}

    public NodeList getList(){return list;}

    public String findValueByIndex(int index){
        return list.item(index).getTextContent();
    }

}
