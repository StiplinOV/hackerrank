package com.stiplin.leetcode;

import java.util.Stack;

public class ReverseLinkedList {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        ListNode next = head.next;
        while (next != null) {
            ListNode nextNext = next.next;
            next.next = prev;
            prev = next;
            next = nextNext;
        }
        head.next = null;
        return prev;
    }
}
