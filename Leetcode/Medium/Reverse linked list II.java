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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        
        // edge case - list is empty
        if(head==null) return null;
        
        // find starting point
        ListNode prev = null, curr = head;
        for(int i=left; i>1; --i){
            prev = curr;
            curr = curr.next;
            //right--; // subtract right by the size of left-1
        }
        
        // keep head and tail node
        ListNode first = prev, last = curr;
        
        // reverse nodes withing diff between left and right
        ListNode tmp = null;
        for(int i=right-(left-1); i>0; --i){
            tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp; // move to next
        }
        
        // connect head
        if(first==null) head = prev; // original head in range
        else first.next = prev; // no head in range
        
        // connect tail
        last.next = curr; // it can be the last node or null
        
        return head;
        
    }
}
