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
    
    int pIdx;
    HashMap<Integer, Integer> set = new HashMap<>();
    
    private TreeNode build(int[] postorder, int left, int right){
        
        // base case
        if(left>right) return null;
        
        // find root
        int rt = postorder[pIdx--];
        TreeNode root = new TreeNode(rt);
        
        // find index of root
        int rIdx = set.get(rt);
        
        // right subtree
        root.right = build(postorder, rIdx+1, right);
        // left subtree
        root.left = build(postorder, left, rIdx-1);
        
        return root;
        
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        pIdx = postorder.length-1;
        
        for(int i=0; i<inorder.length; ++i){
            set.put(inorder[i], i);
        }
        
        return build(postorder, 0, inorder.length-1);
        
    }
}
