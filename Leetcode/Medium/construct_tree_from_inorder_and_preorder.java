/* submit 통과 코드 */

class Solution
{
    public static HashMap<Integer,Integer> m=new HashMap<>();
    
    public static Node buildTree(int inorder[], int preorder[], int n)
    {
        for(int i=0;i<inorder.length;i++){
            m.put(inorder[i],i);
        }
        Node root=buildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
        return root;
    }
    static Node buildTree(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd){
        if(preStart>preEnd || inStart>inEnd) return null;
        
        Node root=new Node(preorder[preStart]);
        int inRoot=m.get(root.data);
        int numsLeft=inRoot-inStart;
        root.left=buildTree(preorder,preStart+1,numsLeft+preStart,inorder,inStart,inRoot-1);
        root.right=buildTree(preorder,numsLeft+preStart+1,preEnd,inorder,inRoot+1,inEnd);
        return root;
    } 
}

/*

submit 통과 못한 코드, custom input으로 run 하면 잘 작동함

Test Cases Passed: 1 /  304

Input: 

5
10 1 30 40 20
1 10 20 30 40


Your Output:

Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 7 out of bounds for length 7
at Solution.buildTree(File.java:70)
at Solution.buildTree(File.java:64)
at GFG.main(File.java:35)


Its Correct output is : 

10 40 30 20 1

*/

class Solution
{
    
    public static HashMap<Integer,Integer> m=new HashMap<>();
    public static int preStart = 0;
    
    public static Node buildTree(int inorder[], int preorder[], int n)
    {
        for(int i=0;i<inorder.length;i++){
            m.put(inorder[i],i);
        }
        Node root=buildTree(preorder, inorder, 0, inorder.length-1);
        return root;
    }
    static Node buildTree(int[] preorder, int[] inorder, int inStart, int inEnd){
        if(inStart>inEnd || preStart > preorder.length) return null;
        
        Node root=new Node(preorder[preStart++]);
        
        if(inStart==inEnd) return root;
        
        int inRoot=m.get(root.data);
        root.left=buildTree(preorder, inorder, inStart, inRoot-1);
        root.right=buildTree(preorder, inorder, inRoot+1, inEnd);
        
        return root;
    } 
}
