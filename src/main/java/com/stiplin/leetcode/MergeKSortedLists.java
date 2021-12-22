package com.stiplin.leetcode;

public class MergeKSortedLists {

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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode current = lists[0];
        for (int i = 1; i < lists.length; i++) {
            current = merge(current, lists[i]);
        }
        return current;
    }

    private ListNode merge(ListNode first, ListNode second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first.val > second.val) {
            ListNode tempList = first;
            first = second;
            second = tempList;
        }
        if (first.next == null) {
            first.next = second;
            return first;
        }
        ListNode mainList = first;
        ListNode remainList = second;

        int remainVal = remainList.val;
        ListNode prevMain = mainList;
        mainList = mainList.next;
        while (mainList != null) {
            int mainVal = mainList.val;
            while (mainVal <= remainVal) {
                prevMain = mainList;
                mainList = mainList.next;
                if (mainList == null) {
                    prevMain.next = remainList;
                    return first;
                }
                mainVal = mainList.val;
            }

            prevMain.next = remainList;
            remainList = mainList;
            mainList = prevMain.next;
            remainVal = remainList.val;
        }
        return first;
    }

}
