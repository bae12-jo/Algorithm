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
    public ListNode deleteDuplicates(ListNode head) {
        
        ListNode dummy = new ListNode(); // temp node before first node
        ListNode res = dummy;
        ListNode pre = null, curr = head;
        
        while(curr!=null){
            
            if((pre==null || pre.val!=curr.val) && (curr.next==null || curr.val!=curr.next.val)){
                res.next = curr; // append node to result list
                res = curr; // move res pointer to curr node
            }
            
            pre = curr; // move pre pointer to curr node
            curr = curr.next; // move next node
            
        }       
        
        res.next = null; // to reduce duplicate ones on last
        return dummy.next;
    }
}
