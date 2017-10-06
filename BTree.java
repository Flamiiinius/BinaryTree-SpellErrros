import java.io.*;
import java.util.*;

class Node{
	String word;
	Node left;
	Node right;	

	public Node(String word){
		this.word = word;
		left = null;
		right = null;
	}
}

class BTree{
	public static Node root; //Root Node

	public BTree(){
		this.root=null;
	}

	public BTree(Node root){
		this.root=root;
	}

	public static boolean isEmpty(){ //O(1)
		return (root==null);
	}

	public static int size(){ 
		return size(root);
	}
	private static int size(Node n){ 
		if(n==null) return 0;
		else return 1 + size(n.left) + size(n.right);
	}

	public static int depth (){ 
		return depth(root);
	}
	private  static int depth(Node n){
		if(n==null) return 0;
		else{
			int count =  1 + Math.max(depth(n.left),depth(n.right));
			return count;
		}
	}

	
	public static void insert(String w){ //O(log n)
		root = insert(root,w);
		
	}
	private static Node insert(Node n,String w){
		if(n==null)	return new Node(w);
		else{
			int cmp=w.compareTo(n.word);
			if(cmp==0)	return n; //its already on the list
			else if(cmp < 0) n.left = insert(n.left,w);
			else n.right=insert(n.right,w);
		}

		return n;
	}

	public static boolean contains(String w){ //O(log n)
		return contains(root,w);
	}
	private static boolean contains(Node n,String w){
		if(n==null) return false;
		else{
			int cmp=w.compareTo(n.word);
			if(cmp==0) return true;
			else if(cmp < 0) return contains(n.left,w);
			else return contains(n.right,w);	
		}
	}

	public static void remove(String w){ //O(log n)
		root = remove(root,w);
	}
	private static Node remove(Node n,String w){
		if(n!=null){
			int cmp=w.compareTo(n.word);
			if(cmp < 0) n.left = remove(n.left,w);
			else if(cmp > 0) n.right = remove(n.right,w);
			else if(n.left==null) n=n.right;
			else if(n.right==null) n=n.left;
			else{
				n.right = removeMin(n,n.right);
			}
		}
		return n;
	}
	private static Node removeMin(Node n1,Node n2){
		if(n2.left==null){
			n1.word=n2.word;
			return n2.right;
		}
		else
			n2.left = removeMin(n1,n2.left);
		return n2;
	}

	public static void view(){ //O(n) !!!SWITCH
		view(root);
	}
	private static void view(Node n){
		if(n.left!=null) view(n.left);
		System.out.println(n.word );
		if(n.right!=null) view(n.right);
		return;
	}

	public static String first(){ //O(log n)
		if(root==null) return ("Tree is empty!");
		return first(root);
	}
	private static String first(Node n){ 
		if(n.left==null) return n.word;
		else return (first(n.left));
	}

	public static String last(){ //O(log n)
		if(root==null) return ("Tree is empty!");
		return last(root);
	}
	private static String last(Node n){
		if(n.right==null) return n.word;
		else return (last(n.right));
	}

	public static long count(int d){ 
		return count(root,d,0);
	}
	private static long count(Node n,int d,int c){
		if(n==null) return 0;
		if(d==c) return 1;
		else{
			long l =count(n.left,d,c+1) + count(n.right,d,c+1);
			return l;
		}
	}
}

