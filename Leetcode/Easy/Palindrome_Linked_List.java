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
    public boolean isPalindrome(ListNode head) {
        
        ListNode middle=head, end=head;
        
        // find middle
        while(end!=null && end.next!=null){
            end = end.next.next;
            middle = middle.next;
        }
        
        // reverse half of list
        ListNode pre = null;
        while(middle!=null){
           ListNode tmp = middle.next;
            middle.next = pre;
            pre = middle;
            middle = tmp;
        }
        
        // find palindrome
        ListNode first = head, second = pre;
        while(second!=null){
            if(first.val != second.val) return false;
            first = first.next;
            second = second.next;
        }
        
        return true;
        
    }
}
