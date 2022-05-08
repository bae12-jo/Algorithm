// O(N^2) insertion sort

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
    public ListNode insertionSortList(ListNode head) {
        
        ListNode dummy = new ListNode();
        ListNode curr = head; // to iterate without manipulating original list
        
        while(curr!=null){ // insert all elements into resulting list
            
            ListNode prev = dummy; // always start from dummy
            
            while(prev.next !=null && prev.next.val<curr.val){ // until result list is end and find tail of result list which is smaller than new element of original list
                prev = prev.next; // update prev
            }    
            
            // swap part
            ListNode tmp = curr.next;
            curr.next = prev.next;
            prev.next = curr;

            curr = tmp; // update curr
        }
        
        return dummy.next;
    }
}

// O(n log n) Divide and conquer

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
    public ListNode insertionSortList(ListNode head) {
        
        // edge case - if list is empty or has only one element
        if(head==null || head.next==null) return head;
        
        // 1. find middle node of given list
        ListNode slow = head, fast = head.next;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 2. sort both lists
        ListNode head2 = slow.next;
        slow.next = null; // to split linked list arount slow pointer (if not, go through infinite loop)
        head = insertionSortList(head);
        head2 = insertionSortList(head2);        
        
        // 3. merge two lists
        return mergeList(head, head2);
    }
    
    private ListNode mergeList(ListNode left, ListNode right){
        
        // edge case - if list is empty
        if(left==null) return right;
        if(right==null) return left;
        
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        
        while(left!=null && right!=null){
            
            if(left.val<right.val){
                curr.next = left;
                left = left.next;
            }else{
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
            
        }
        
        curr.next = left == null ? right : left;
        
        return dummy.next;
         
    }
}
