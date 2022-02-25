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
    
    final List<List<Integer>> list = new ArrayList<>();
    final Queue<TreeNode> q = new LinkedList<>();
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root!=null){
            q.offer(root);
        }
        TreeNode cur;
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<Integer>();
            for(int i=0; i<size; i++){
                cur = q.poll();
                level.add(cur.val);
                if(cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
            list.add(level);
        }
        
        return list;
        
        
    }
}
