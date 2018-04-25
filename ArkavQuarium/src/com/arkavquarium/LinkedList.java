package com.arkavquarium;

//import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;

import java.io.Serializable;

public class LinkedList<T> implements Serializable {
  private Element<T> first;
  private int size;

  private class Element<T> implements Serializable {
    private T data;
    private Element<T> next;

    public Element() {
      this.data = null;
      this.next = null;
    }

    public Element(T obj) {
      this.data = obj;
      this.next = null;
    }

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

  public int getSize() {
    return size;
  }

  /**
   * getter for i-th element of linkedlist.
   * @param idx = i
   * @return i-th element of linkedlist
   */
  public T get(int idx) {
    Element<T> curr = first;
    for (int i = 0; i < idx; i++) {
      if (curr.getNext() != null) {
        curr = curr.getNext();
      }
    }
    return curr.getData();
  }

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * add element to linkedlist.
   * @param data = element to be added
   */
  public void add(T data) {
    Element<T> tmp = new Element<T>(data);
    if (isEmpty()) {
      first = tmp;
    } else {
      Element<T> it = first;
      while (it.getNext() != null) {
        it = it.getNext();
      }
      it.setNext(tmp);
    }
    size++;
  }

  /**
   * remove a data from linkedlist.
   * @param data = data to be removed
   */
  public void remove(T data) {
    Element<T> curr = first;
    Element<T> before = first;
    boolean found = false;

    if (data.equals(first.getData())) {
      first = first.getNext();
    } else {
      while (curr.getNext() != null) {
        if (curr.getNext().getData().equals(data)) {
          found = true;
          break;
        } else {
          curr = curr.getNext();
        }
      }
      if (found) {
        Element<T> tmp = curr.getNext();
        curr.setNext(curr.getNext().getNext());
      }
    }
    size--;
  }
}
