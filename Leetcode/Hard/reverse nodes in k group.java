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
    
    private ListNode reverseList(ListNode head, int k){
        
        ListNode prev = null, curr = head;
        
        while(k>0){
            
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            
            curr = tmp; // move to next
            
            k--;
        }
        
        return prev; // new head of reverse list
        
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        
        ListNode curr = head;
        ListNode tail = null;
        
        ListNode newHead = null; // add reverse list to another reverse or null
        
        while(curr!=null){
            
            int count = 0;
            curr = head; // starting from new head
            
            while(count<k && curr!=null){ // find the head of next k nodes
                curr = curr.next;
                count++;
            }
            
            if(count==k){ // if number of left nodes is over k
                
                ListNode revHead = reverseList(head, k);
                if(newHead==null) newHead=revHead; // keep new head of whole reverse list
                
                if(tail!=null) tail.next=revHead; // add new reverse list to prev tail
                
                tail=head; // unreverse head is same with tail
                head=curr; // update head to curr
                
            }
        }
        
        if(tail!=null) tail.next=head; // if there is reverse list
        
        return newHead == null ? head : newHead;
        
    }
}
