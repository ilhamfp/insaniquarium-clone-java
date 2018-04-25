package com.arkavquarium.test;

import com.arkavquarium.LinkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    public void testSize(){
        LinkedList<String> list = new LinkedList<String>();
        list.add("A");
        list.add("B");
        assertEquals(2, list.getSize());
    }

    @Test
    public void testAddRemove(){
        LinkedList<String> list = new LinkedList<String>();
        list.add("A");
        list.add("B");
        list.remove("B");
        assertEquals(1, list.getSize());
    }

    @Test
    public void testGetByIndeks(){
        LinkedList<String> list = new LinkedList<String>();
        list.add("A");
        list.add("B");
        assertEquals("B", list.get(1));
    }
}