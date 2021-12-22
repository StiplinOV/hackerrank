package com.stiplin.leetcode;

public class AddTwoNumbers {

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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode currentResultNode = null;
        ListNode currentl1Pointer = l1;
        ListNode currentl2Pointer = l2;
        boolean overflow = false;
        ListNode result = null;
        while (currentl1Pointer != null && currentl2Pointer != null) {
            int newVal = currentl1Pointer.val + currentl2Pointer.val;
            if (overflow) {
                newVal++;
            }
            overflow = false;
            if (newVal > 9) {
                overflow = true;
                newVal = newVal - 10;
            }
            if (currentResultNode != null) {
                currentResultNode.next = new ListNode(newVal);
                currentResultNode = currentResultNode.next;
            } else {
                currentResultNode = new ListNode(newVal);
                result = currentResultNode;
            }
            currentl1Pointer = currentl1Pointer.next;
            currentl2Pointer = currentl2Pointer.next;
        }
        while (currentl1Pointer != null) {
            int newVal = currentl1Pointer.val;
            if (overflow) {
                newVal++;
            }
            overflow = false;
            if (newVal > 9) {
                overflow = true;
                newVal = newVal - 10;
            }

            currentResultNode.next = new ListNode(newVal);
            currentResultNode = currentResultNode.next;
            currentl1Pointer = currentl1Pointer.next;
        }
        while (currentl2Pointer != null) {
            int newVal = currentl2Pointer.val;
            if (overflow) {
                newVal++;
            }
            overflow = false;
            if (newVal > 9) {
                overflow = true;
                newVal = newVal - 10;
            }

            currentResultNode.next = new ListNode(newVal);
            currentResultNode = currentResultNode.next;
            currentl2Pointer = currentl2Pointer.next;
        }
        if(overflow) {
            currentResultNode.next = new ListNode(1);
        }
        return result;
    }

}
