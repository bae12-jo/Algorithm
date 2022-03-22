/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        
        ListNode nextNode = node.next;
        
        // replace value with next node of given node
        
        node.val = nextNode.val;
        // replace next node with next node of given node
        
        node.next = nextNode.next;
        
    }
}
