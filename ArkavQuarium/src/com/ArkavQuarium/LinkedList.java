package com.ArkavQuarium;

public class LinkedList<T> {
    private Element<T> first;
    private Element<T> last;
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
        this.last = this.first;
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
        if (this.first.getData() == null) {
            this.first = tmp;
            this.last = this.first;
        } else {
            this.last.setNext(tmp);
            this.last = tmp;
        }
        size++;
    }

    // remove a data from linkedlist
    public void remove(T data) {
        Element<T> curr = first;
        if (this.first.getData().equals(data)) {
            if (this.first.getNext() == null) {
                Element<T> newNode = new Element<T>();
                this.first = newNode;
                this.last = this.first;
            } else {
                this.first.setData(null);
                this.first = this.first.getNext();
            }
            size --;
        } else {
            boolean deleted = false;
            while (!deleted) {
                Element<T> currNext = curr.getNext();
                if (currNext.getData().equals(data)) {
                    currNext.setData(null);
                    curr.setNext(currNext.getNext());
                    currNext = null;
                    deleted = true;
                    size--;
                } else {
                    curr = curr.getNext();
                }
            }
        }
    }
}