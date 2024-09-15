package com.ufc.pi.webservice.data.structures;

public class DoublyLinkedList<T> {  
    Node head, tail = null;  
  
    public class Node{  
        public T data;  
        public Node previous;  
        public Node next;  
  
        public Node(T data) {  
            this.data = data;  
        }  
    }  
  
    public void addNode(T data) {  
        Node newNode = new Node(data);  
  
        if(head == null) {  
            head = tail = newNode;  
            head.previous = null;  
            tail.next = null;  
        }  
        else {  
            tail.next = newNode;  
            newNode.previous = tail;  
            tail = newNode;  
            tail.next = null;  
        }  
    }  

    public Node getHead() {
        return head;
    }
}
 

