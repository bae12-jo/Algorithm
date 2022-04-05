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
    
    public int getHeight(TreeNode root, List<List<Integer>> result){
        if(root==null) return -1;
        int height = Math.max(getHeight(root.left, result), getHeight(root.right, result))+1;
        
        if(height>=result.size()) result.add(new ArrayList<Integer>());
        result.get(height).add(root.val);
        
        return height;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(root==null) return result;
        
        getHeight(root, result);
        
        return result;
        
    }
}
