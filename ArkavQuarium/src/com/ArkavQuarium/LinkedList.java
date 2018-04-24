package com.ArkavQuarium;

//import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;

public class LinkedList<T> {
    private Element<T> first;
    private int size;

    // class element list
    private class Element<T> {
        private T data;
        private Element<T> next;

        // ctor
        public Element() {
            this.data = null;
            this.next = null;
        }

        public Element(T obj) {
            this.data = obj;
            this.next = null;
        }

        /*** getter and setter ***/
        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Element<T> getNext() {
            return next;
        }

        public void setNext(Element<T> next) {
            this.next = next;
        }

    }

    // default ctor
    public LinkedList() {
        Element<T> temp = new Element<T>();
        this.first = temp;
    }

    /*** getter and setter ***/
    public int getSize() {
        return size;
    }

    public T get(int idx){
        Element<T> curr = first;
        for(int i = 0; i < idx; i++){
            if(curr.getNext() != null)
                curr = curr.getNext();
        }
        return curr.getData();
    }

    public boolean isEmpty(){
        return size == 0;
    }
    // add a data to linkedlist
    public void add(T data) {
        Element<T> tmp = new Element<T>(data);
        if(isEmpty())
            first = tmp;
        else{
            Element<T> it = first;
            while(it.getNext() != null)
                it = it.getNext();
            it.setNext(tmp);
        }
        size++;
    }

    // remove a data from linkedlist
    public void remove(T data) {
        Element<T> curr = first;
        boolean found = false;
        while( curr.getNext() != null){
            if(curr.getNext().getData().equals(data)){
                found = true;
                break;
            }
            else
                curr = curr.getNext();
        }

        if(found){
            Element<T> tmp = curr.getNext();
            curr.setNext(curr.getNext().getNext());
        }
        size--;
    }
}