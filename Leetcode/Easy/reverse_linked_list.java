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
    public ListNode reverseList(ListNode head) {
        
        // edge case covering
        if(head==null) return null;
        
        ListNode tmp, pre = null, curr = head;
        
        tmp = curr.next;
        curr.next = null;
        pre = curr;
        curr = tmp;
        
        while(curr!=null){
            tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        
        return pre;
        
    }
}
