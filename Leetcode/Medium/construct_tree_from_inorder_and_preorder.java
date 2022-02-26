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
