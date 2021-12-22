package com.stiplin.leetcode;

public class LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        do {
            ListNode slowNext = slowPointer.next;
            if(slowNext == null) {
                return false;
            }
            ListNode fastNext = fastPointer.next;
            if (fastNext == null) {
                return false;
            }
            ListNode fastNextNext = fastNext.next;
            if (fastNextNext == null) {
                return false;
            }
            slowPointer = slowNext;
            fastPointer = fastNextNext;
        } while (slowPointer != fastPointer);

        return true;
    }

}
