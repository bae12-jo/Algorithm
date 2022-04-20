/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Approach 1: Top-down DFS
// Time : O(N)
// Space: O(N)

class Solution {
    
    private boolean validate(TreeNode root, Integer low, Integer high){
        if(root==null) return true;
        if((low!=null) && (root.val<=low) || (high!=null) && (root.val>=high)) return false;
        
        
        return validate(root.left, low, root.val) && validate(root.right, root.val, high);      
    }
    
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
}


// Approach 2: Inorder Traversal
// Time : O(N)
// Space: O(N)


class Solution {
    
    private Integer prev;
    private boolean inorder(TreeNode root){
        
        if(root==null) return true;
        if(!inorder(root.left)) return false;
        if(prev!=null && root.val<=prev) return false;
        prev = root.val;
        return inorder(root.right);
    }
    
    public boolean isValidBST(TreeNode root) {
        prev = null;
        return inorder(root);
    }
}


