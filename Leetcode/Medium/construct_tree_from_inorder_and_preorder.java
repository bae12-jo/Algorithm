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
        if(inStart>inEnd || preStart>preorder.length-1) return null;
        
        Node root=new Node(preorder[preStart++]);
        int inRoot=m.get(root.data);
        root.left=buildTree(preorder, inorder, inStart, inRoot-1);
        root.right=buildTree(preorder, inorder, inRoot+1, inEnd);
        
        return root;
    } 
}
