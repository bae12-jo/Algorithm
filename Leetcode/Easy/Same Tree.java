// TreeTraversal

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
    
    private void preorder(TreeNode root, List<String> order){
        if(root!=null){
            order.add(String.valueOf(root.val));
            preorder(root.left, order);
            preorder(root.right, order);
        }else{
            order.add("null");
        }
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<String> order1 = new ArrayList<>();
        List<String> order2 = new ArrayList<>();
        preorder(p, order1);
        preorder(q, order2);
        
        for(int i=0; i<order1.size(); ++i){
            if(!order1.get(i).equals(order2.get(i))) return false;
        }
        
        return true;
    }
}

// Recursion

class Solution {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    // p and q are both null
    if (p == null && q == null) return true;
    // one of p and q is null
    if (q == null || p == null) return false;
    if (p.val != q.val) return false;
    return isSameTree(p.right, q.right) &&
            isSameTree(p.left, q.left);
  }
}

