/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;
        
        if(head.next==null){
            return head.next;
        }
        
        int gap = -1;
        while(first != null){
            first = first.next;
            
            if(++gap > n){
                second = second.next;
            }
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
