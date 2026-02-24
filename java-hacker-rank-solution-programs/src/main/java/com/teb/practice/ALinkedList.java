package com.teb.practice;

/*
 * A Node class is provided for you in the editor. A Node object has an integer data field, data,
 * and a Node instance pointer, next, pointing to another node
 * A Node insert function has two parameters: a pointer, head, pointing to the first node of a linked list,
 * and an integer, data, that must be added to the end of the list as a new Node object.
 * Complete the insert function in your editor so that it creates a new Node (pass data as the Node constructor argument)
 * and inserts it at the tail of the linked list referenced by the head parameter.
 * Once the new node is added, return the reference to the head node.
 */

import static com.teb.practice.constants.Constants.SCAN;
import static com.teb.practice.constants.Constants.SPACE;

import static java.lang.System.out;

class Node {

    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class ALinkedList {

    private static Node insert(Node head, int data) {

        if (head == null) return new Node(data);

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(data);

        return head;
    }

    private static void display(Node head) {

        Node current = head;
        while (current != null) {
            out.print(current.data + SPACE);
            current = current.next;
        }
    }

    public static void main(String[] args) {

        Node head = null;
        int limit = SCAN.nextInt();

        while (limit-- > 0) {
            int element = SCAN.nextInt();
            head = insert(head, element);
        }

        display(head);
    }
}
