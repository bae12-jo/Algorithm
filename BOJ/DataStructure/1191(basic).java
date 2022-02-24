import java.io.*;
import java.util.*;

class Node{
	String data;
	Node left;
	Node right;
	
	public Node(String data){
		this.data = data;
	}
}

class Tree{
	public Node root;
	
	public Node getRoot(){
		return root;
	}
	
	public void addNode(String data, String left, String right){
		if(root==null)
		{
			if(!data.equals(".")) root = new Node(data);
			if(!left.equals(".")) root.left = new Node(left);
			if(!right.equals(".")) root.right = new Node(right);
		}else{
			findNode(root, data, left, right);
		}
	}
	
	public void findNode(Node root, String data, String left, String right){
		if(root==null) return;

		else if(root.data.equals(data))
		{
			if(!left.equals(".")) root.left = new Node(left);
			if(!right.equals(".")) root.right = new Node(right);
		}
		else
		{
			findNode(root.left, data, left, right);
			findNode(root.right, data, left, right);
		}
	}
	
	public void inorder(Node node){
		if (node!=null){
			inorder(node.left);
			System.out.print(node.data);
			inorder(node.right);
		}
	}
	
	public void preorder(Node node){
		if(node!=null){
			System.out.print(node.data);
			preorder(node.left);
			preorder(node.right);
		}
	}
	
	public void postorder(Node node){
		if(node!=null){
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.data);			
		}
	}
}

public class BOJ_1991{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Tree t = new Tree();
		
		for(int i=0; i<n; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			t.addNode(st.nextToken(), st.nextToken(), st.nextToken());
		}
		
		//System.out.println("==preorder==");
		t.preorder(t.getRoot());
		System.out.println();
		//System.out.println("==inorder==");
		t.inorder(t.getRoot());
		System.out.println();
		//System.out.println("==postorder==");
		t.postorder(t.getRoot());
		
	}
}
