package shch91.app.algorithm;

public class CycleLink {

    /**
     * Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode s = head, p = head;
        while (p != null && p.next != null) {
            s=s.next;
            p = p.next.next;
            if(s==p){
                return true;
            }
        }
        return false;
    }

}
