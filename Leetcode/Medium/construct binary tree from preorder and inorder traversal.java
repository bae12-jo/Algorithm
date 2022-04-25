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
class Solution {
    
    private int rIdx;
    private int iIdx;
    
    private TreeNode build(int[] preorder, int[] inorder, int exit){
        
        // edge case - empty tree
        if(rIdx>=preorder.length) return null;
        
        
        // base case - there is no place to traversal in subtree
        if(inorder[iIdx]==exit){
            iIdx++;
            return null;
        }
        
        int rt = preorder[rIdx++];
        TreeNode root = new TreeNode(rt);
        
        // set maximum value of subtree based on inorder
        root.left = build(preorder, inorder, rt);
        root.right = build(preorder, inorder, exit);
        
        return root;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        rIdx = 0;
        iIdx = 0;
        return build(preorder, inorder, Integer.MAX_VALUE);
        
    }
}
