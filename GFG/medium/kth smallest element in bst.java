class Solution {
    // Return the Kth smallest element in the given BST
    public int KthSmallestElement(Node root, int K) {
        // Write your code here
        
        Stack<Node> stack = new Stack<>();
        Node node = root;
        int count = 0;
        
        while(true){
            if(node!=null){
                stack.push(node);
                node = node.left;
            }else{
                if(stack.isEmpty()) break;
                node = stack.peek();
                stack.pop();
                count++;
                if(count==K) return node.data;
                node = node.right;
            }
        }
        
        return -1;
    }
}
