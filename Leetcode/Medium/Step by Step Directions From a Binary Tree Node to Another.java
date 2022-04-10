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
    
    /* DFS를 돌면서 루트부터 타겟까지 역순으로 문자열 만드는 코드 */
    private boolean isPath(TreeNode root, int destValue, StringBuilder path){
        if(root.val==destValue) return true;
        
        if(root.left!=null && isPath(root.left, destValue, path)) path.append("L");
        else if(root.right!=null && isPath(root.right, destValue, path)) path.append("R");        
        
        return path.length()>0;
    }
    
    public String getDirections(TreeNode root, int startValue, int destValue) {
        
        StringBuilder sPath = new StringBuilder(), dPath = new StringBuilder();
        
        isPath(root, startValue, sPath);
        isPath(root, destValue, dPath);
        
        //System.out.println(sPath.toString());
        //System.out.println(dPath.toString());
        
        int suffix = 0, maxLen = Math.min(sPath.length(), dPath.length());
        while(suffix<maxLen && sPath.charAt(sPath.length()-1-suffix)==dPath.charAt(dPath.length()-1-suffix)) suffix++;
        
        return "U".repeat(sPath.length()-suffix).toString() + dPath.reverse().substring(suffix);
        
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    
  /* DFS를 돌면서 루트부터 타겟까지 순서대로 문자열 만드는 코드 */
    private boolean isPath(TreeNode root, int destValue, StringBuilder path){
        if(root==null) return false;
        if(root.val==destValue) return true;
        
        if(root.left!=null){
            path.append("L");
            if(isPath(root.left, destValue, path)) return true;
            path.deleteCharAt(path.length()-1);
        }
        if(root.right!=null){
            path.append("R");
            if(isPath(root.right, destValue, path)) return true;
            path.deleteCharAt(path.length()-1);
        }
        
        return false;
    }
    
    public String getDirections(TreeNode root, int startValue, int destValue) {
        
        StringBuilder sPath = new StringBuilder(), dPath = new StringBuilder();
        
        isPath(root, startValue, sPath);
        isPath(root, destValue, dPath);
        
        //System.out.println(sPath.toString());
        //System.out.println(dPath.toString());
        
        int suffix = 0, maxLen = Math.min(sPath.length(), dPath.length());
        while(suffix<maxLen && sPath.charAt(suffix)==dPath.charAt(suffix)) suffix++;
        
        for(int i=suffix; i<sPath.length(); ++i) sPath.setCharAt(i, 'U');
        
        return sPath.substring(suffix)+dPath.substring(suffix);
        
    }
}
