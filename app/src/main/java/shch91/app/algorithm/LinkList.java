package shch91.app.algorithm;


public class LinkList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        int lenA = 0, lenB = 0, pre = 0, min = 0;
        //链表长度
        ListNode ha = headA, hb = headB;
        while (ha != null) {
            lenA++;
            ha = ha.next;
        }
        while (hb != null) {
            lenB++;
            hb = hb.next;
        }
        ha = headA;
        hb = headB;
        if (lenA > lenB) {
            pre = lenA - lenB;
            while (pre > 0) {
                pre--;
                ha = ha.next;
            }
        } else if (lenB > lenA) {
            pre = lenB - lenA;
            while (pre > 0) {
                pre--;
                hb = hb.next;
            }
        }
        min = Math.min(lenA, lenB);
        while (min > 0) {
            if (ha == hb) {
                return ha;
            }
            min--;
            ha = ha.next;
            hb = hb.next;

        }
        return null;
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}