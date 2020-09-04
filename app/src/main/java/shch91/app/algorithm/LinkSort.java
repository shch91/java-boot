package shch91.app.algorithm;

public class LinkSort {

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    //bubbo sort 冒泡排序
    public ListNode sortList(ListNode head) {
        ListNode h = head, next;
        int length = 0, time;
        //链表长度
        while (h != null) {
            length++;
            h = h.next;
        }
        //冒泡次数
        for (int i = 1; i < length; i++) {
            time = length - i;
            h = head;
            while (time > 0) {
                time--;
                next = h.next;
                if (h.val < next.val) {
                    int tmp = h.val;
                    h.val = next.val;
                    next.val = tmp;
                }
                h=next;
            }
        }
        return head;
    }
}
