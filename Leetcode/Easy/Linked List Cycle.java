/* Brute Force */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        if(head==null) return false;
        
        HashSet<ListNode> set = new HashSet<>();
        
        while(head!=null){
            if(set.contains(head)) return true;
             set.add(head);
             head = head.next;
        }
        
        return false;       
        
    }
}


/* Floyd's Cycle Detection Algorithm  (Tortoise and Hare) */

// Detect a start node of cycle


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        if(head==null) return false;
        
        ListNode rabbit = head.next;
        ListNode turtle = head;
        
        while(rabbit!=turtle){
            if(rabbit==null || rabbit.next==null) return false;
             rabbit = rabbit.next.next;
             turtle = turtle.next;
        }
        
        return true;       
        
    }
}
