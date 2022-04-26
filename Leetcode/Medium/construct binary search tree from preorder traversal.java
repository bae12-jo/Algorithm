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
    
//     8 5 1 7 10
    
        
//     8 (root) 
//     elements of left subtree of root is always smaller than root (upper bound of l.s)
//     elements of right subtree of root is always bigger than root (lower bound of r.s)
    
    
    // helper method in recursive way
    
    private int rIdx;
    
    private TreeNode builder(int[] preorder, int lower, int upper){
        
        // base case
        if(lower>upper || rIdx==preorder.length) return null;
        
        // get root
        int rt = preorder[rIdx];
        // check root is vaild or not
        if(rt<lower || rt>upper) return null;
        
        // if it's valid, update root index
        rIdx++;
        
        // add root to tree
        TreeNode root = new TreeNode(rt);
        
        // left subtree
        root.left = builder(preorder, lower, rt);
        // right subtree
        root.right = builder(preorder, rt, upper);        
        
        return root;            
    }
    
    public TreeNode bstFromPreorder(int[] preorder) {
        
        rIdx = 0;
        // preorder: root - left - right (leftsubtree less than root, rightsubtree bigger than root)
        // tree, lower bound of tree, upper bound of tree        
        return builder(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
    }
}
