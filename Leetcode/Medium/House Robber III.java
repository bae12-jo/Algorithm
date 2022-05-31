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

// Top-Down (Recursion)

class Solution {
    public int rob(TreeNode root) {
        
        int[] result = robHelper(root); // with, without
        return Math.max(result[0], result[1]);
        
    }
    
    private int[] robHelper(TreeNode root){
        
        // base case
        if(root==null) return new int[]{0, 0};
        
        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);
        
        int with = root.val + left[1] + right[1]; // rob now and grand children
        int without = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // rob children or grandchildren
        
        return new int[] {with, without};
    }
}

