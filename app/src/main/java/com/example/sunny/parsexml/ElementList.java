package com.example.sunny.parsexml;

import org.w3c.dom.NodeList;

/**
 * A doubly linked list filled with elements
 * from xml file.
 *
 * Created by Sunny on 6/25/2017.
 */

public class ElementList {
    Element head;
    Element tail;
    private int count;


    /**
     * constructor to initialize tail and head in our list
     */
    public ElementList(){
        head=null;
        tail=null;
    }


    /**
     * obtain our list count/length
     *
     * @return count as integer
     */
    public int getCount(){return count;}


    /**
     * creates element with name and list
     *
     * @param name as String
     * @param list as NodeList
     * @return node as Element
     */
    public Element createElement(String name,NodeList list){
        Element node=new Element(name,list);
        return node;
    }

    /**
     * pushes entry at the head of the list
     *
     * @param name as String
     * @param list as NodeList
     */
    public void insertHead(String name, NodeList list){
        Element node=this.createElement(name,list);
        count++;
        if(head==null){
            head=node;
            tail=head;
        }else{
            node.next=head;
            head=node;
        }

    }


    /**
     * insert entry at the end/tail of the list
     *
     * @param name as String
     * @param list as NodeList
     */
    public void insertEnd(String name,NodeList list){
        Element node=new Element(name,list);
        count++;
        if(tail==null){
            tail=node;
            head=node;
        }else{
            tail.next=node;
            node.prev=tail;
            tail=node;
        }
    }


    /**
     * return value in list by index and element name
     *
     * @param name as string
     * @param index as integer
     * @return value as string
     */
    public String valueByNameIndex(String name,int index){
        Element temp=head;
        String value="EMPTY";

        while(temp!=null){
            if(temp.getName().compareToIgnoreCase(name)==0) {
                value=temp.findValueByIndex(index).toString();
                return value;
            }//element is found
            temp=temp.next;
        }
        return value;
    }


    /**
     * find element name by index in doubly linked list
     *
     * @param index as integer
     * @return value as String
     */
    public String nameByIndex(int index){
        int counter=0;
        Element temp=head;
        String value="EMPTY";

        while(temp!=null){
            if(index==counter) {
                value=temp.getName();
                return value;
            }
            temp=temp.next;
            counter++;
        }
        return value;
    }

}