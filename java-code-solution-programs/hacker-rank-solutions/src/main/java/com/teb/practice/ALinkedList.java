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

import java.util.ArrayList;
import java.util.List;

public class ALinkedList {

    private Node head;

    protected void insert(List<Double> inputList) {

        if (inputList == null || inputList.isEmpty()) return;

        for (double data : inputList) {
            Node node = new Node(data * 0.12);

            if (head == null) {
                head = node;
                continue;
            }

            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
        }
    }

    protected List<Double> display() {

        List<Double> resultList = new ArrayList<>();
        Node current = head;

        while (current != null) {
            resultList.add(current.data);
            current = current.next;
        }

        return resultList;
    }

    private static class Node {

        final double data;
        Node next;

        Node(double data) {
            this.data = data;
            this.next = null;
        }
    }
}
