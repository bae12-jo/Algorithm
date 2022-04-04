/*
* Successor : 나보다 큰 노드 중에서 가장 작은 노드
* Predesscor : 나보다 작은 노드 중에서 가장 큰 노드
*/


// a class for representing the nodes of a binary tree
class BTreeNode{
	int val;
	
	BTreeNode left, right;
	
	BTreeNode(int item){
		val = item;
		left = right = null;
	}
}

public class BTreeMorrisInorder{

	BTreeNode root;
	
	void MorrisTraversalInorder(BTreeNode root){
		
		BTreeNode curr, pred; // current, predecessor
		
		if(root==null) return;
		
		curr = root;
		
		while(curr!=null){
			if(curr.left==null){
				System.out.print(curr.val + " ");
				curr = curr.right; // go back to Successor
			}else{
				// finding the inorder predecessor of the current node
				pred = curr.left;
				while(pred.right!=null && pred.right!=curr){
					pred = pred.right;
				}
				// making the current as the right child of its inorder predecessor (vitual links)
				if(pred.right==null){
					pred.right = curr;
					curr = curr.left;
				}else{ // restoring tree
					pred.right = null;
					System.out.print(curr.val + " ");
					curr = curr.right;
				}
			}
		}
		
	}
	
	// The binary tree constructed in this code  
//                        6  
//                      /   \  
//         			   8     9  
//                   /   \     \  
//                 1      4      7  
//                       /  
//                      5 
	
	public static void main(String args[]){
		
		BTreeMorrisInorder tree = new BTreeMorrisInorder(); // creating an object of the class BTree
		
		tree.root = new BTreeNode(6); // creating the root node
		
		tree.root.left = new BTreeNode(8); // creating the remaining nodes of the binary tree
		tree.root.right = new BTreeNode(9);
		tree.root.left.left = new BTreeNode(1);
		tree.root.left.right = new BTreeNode(4);
		tree.root.right.right = new BTreeNode(7);
		tree.root.left.right.left = new BTreeNode(5);
		
		System.out.print("The inorder traversal of the binary tree is: \n" ); // The print statements  
		tree.MorrisTraversalInorder(tree.root);  		
		
	}

}
