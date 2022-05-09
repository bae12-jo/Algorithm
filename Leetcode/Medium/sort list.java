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
    public ListNode sortList(ListNode head) {
        
        // edge case - if list is empty or has only one element
        if(head==null || head.next==null) return head;
        
        // find mid index
        ListNode slow = head, fast = head.next;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // sort left and right
        ListNode head2 = slow.next;
        slow.next=null;
        head = sortList(head);
        head2 = sortList(head2);
        
        // merge
        return merge(head, head2);
        
    }
    
    private ListNode merge(ListNode left, ListNode right){
        
        // edge case - when one list is empty
        if(left==null) return right;
        if(right==null) return left;
        
        // define sentinel node and pointer
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        
        // append node one by one in ascending order
        while(left!=null && right!=null){
            if(left.val>right.val){
                curr.next = right;
                right = right.next;
            }else{
                curr.next = left;
                left = left.next;
            }
            curr = curr.next;
        }
        
        // append last nodes
        curr.next = left == null ? right : left;
        
        return dummy.next;
        
    }
    
}
