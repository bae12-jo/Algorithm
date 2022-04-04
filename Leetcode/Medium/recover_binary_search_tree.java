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

// idea: every elements should be in increasing order with inorder traversal

class Solution {
    
    public void inorder(TreeNode root, List<Integer> list){
        if(root==null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
    
    public int[] findTwoSwappedNums(List<Integer> list){
        
        int n = list.size();
        int x = 0, y = 0;
        
        boolean firstSwapped = false;
        
        for(int i=0; i<n-1; ++i){
            if(list.get(i+1) < list.get(i)){
                y = list.get(i+1);
                if(!firstSwapped){
                    x = list.get(i);
                    firstSwapped = true;
                }else{
                    break;
                }
            }
        }
        
        return new int[]{x, y};
        
    }
    
    public void recover(TreeNode root, int count, int x, int y){
        if(root!=null){
            if(root.val == x || root.val == y){
                root.val = root.val == x ? y : x;
                if(--count == 0) return;
            }
        }
        if(root.left!=null) recover(root.left, count, x, y);
        if(root.right!=null) recover(root.right, count, x, y);
    }
    
    public void recoverTree(TreeNode root) {
        
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int[] swapped = findTwoSwappedNums(list);
        recover(root, 2, swapped[0], swapped[1]);
    }
}

// optimization

class Solution {
    TreeNode prev = null, cur = null, x = null, y = null;
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        cur = root;
        if (prev != null && cur.val < prev.val) {
            if (x == null) {
                x = prev;
                y = cur;
            } else {
                y = cur;
            }
        }
        prev = cur;
        inorder(root.right);
    }
}
