package com.example.sunny.parsexml;

import org.w3c.dom.NodeList;

/**
 * Created by Sunny on 6/25/2017.
 */

public class ElementList {
    Element head;
    Element tail;
    private int count;

    public ElementList(){
        head=null;
        tail=null;
    }


    public int getCount(){return count;}


    public Element createElement(String name,NodeList list){
        Element node=new Element(name,list);
        return node;
    }

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

    public String valueByNameIndex(String name,int index){
        Element temp=head;
        String value="EMPTY";

        while(temp!=null){
            if(temp.getName().compareToIgnoreCase(name)==0) {
                value=temp.findValueByIndex(index).toString();
                return value;
            }
            temp=temp.next;
        }
        return value;
    }

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