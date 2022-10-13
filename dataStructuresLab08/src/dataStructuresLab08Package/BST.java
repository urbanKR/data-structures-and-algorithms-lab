package dataStructuresLab08Package;

import java.util.NoSuchElementException;

public class BST<T> {
	private class Node{
		T value;
		Node left,right,parent;
		public Node(T v) {
			value=v;
		}
		public Node(T value, Node left, Node right, Node parent) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}		
	private Node root=null;

	public BST() {
	}

	public T getElement(T toFind) {
		// TODO
		Node el = root;
		while(el != null) {
            if(((Link) (el.value)).compareTo((Link) toFind) < 0)
            {
            	el = el.right;
            }
            else if(((Link) (el.value)).compareTo((Link) toFind) > 0)
            {
            	el = el.left;
            }
            else return el.value;
        }
        return null;
	}
	
	public Node getElementN(T toFind) {
		// TODO
		Node el = root;
		while(el != null) {
            if(((Link) el.value).compareTo((Link) toFind) < 0)
            {
            	el = el.right;
            }
            else if(((Link) el.value).compareTo((Link) toFind) > 0)
            {
            	el = el.left;
            }
            else return el;
        }
        return null;
	}


	public T successor(T elem) {
		// TODO
		Node el =  getElementN(elem);
		if(el == null)
		{
			return null;
		}
		if(el.right != null)
		{
			el = el.right;
			while(el.left != null) {
				el = el.left;
			}
				return el.value;
		}
		Node elParent = el.parent;
		while(el == elParent.right && elParent != null) {
			el = elParent;
			elParent = elParent.parent;
		}
		return elParent.value;
	}
	
	public Node successorN(T elem) {
		// TODO
		Node el = getElementN(elem);
		if(el == null)
		{
			return null;
		}
		if(el.right != null)
		{
			el = el.right;
			while(el.left != null) {
				el = el.left;
			}
			return el;
		}
		Node elParent = el.parent;
		while(el == elParent.right && elParent != null) {
			el = elParent;
			elParent = elParent.parent;
		}
		return elParent;
	}
		
	public String inOrder(Node el) {
		String retStr="";
		if(el == null)
		{
			return retStr;
		}
		retStr += inOrder(el.left);
		retStr += el.value.toString()+", ";
		retStr += inOrder(el.right);
	    return retStr;
	}

	public String preOrder(Node el) {
		String retStr="";
		if(el == null)
		{
			return "";
		}
		retStr += el.value.toString()+", ";
		retStr += preOrder(el.left);
		retStr += preOrder(el.right);
	    return retStr;
	}
	
	public String postOrder(Node el) {
		String retStr="";
		if(el == null)
		{
			return "";
		}
		retStr += postOrder(el.left);
		retStr += postOrder(el.right);
		retStr += el.value.toString()+", ";
	    return retStr;
	}
	
	public String toStringInOrder() {
		// TODO
		String retStr = inOrder(root);
		retStr = retStr.substring(0, retStr.length()-2);
		return retStr;
	}

	public String toStringPreOrder() {
		// TODO
		String retStr = preOrder(root);
		retStr = retStr.substring(0, retStr.length()-2);
		return retStr;
	}

	public String toStringPostOrder() {
		// TODO
		String retStr = postOrder(root);
		retStr = retStr.substring(0, retStr.length()-2);
		return retStr;
	}


	public boolean add(T elem) {
		// TODO
		Node el = new Node(elem, null, null, null);
		Node curr = null;
		Node elRoot = root;
		while (elRoot != null) {
			curr = elRoot;
			if(((Link) el.value).compareTo((Link) curr.value) < 0)
			{
				elRoot = elRoot.left;
			}
			else 
			{
				elRoot = elRoot.right;
			}
		}
		el.parent = curr;
		if(curr == null)
		{
			root = el;
		}
		else if(((Link) el.value).compareTo((Link) curr.value) < 0)
		{
			curr.left = el;
		}
		else curr.right = el;
		return true;
	}

	public T remove(T value) {
		Node el = getElementN(value);
		Node y, x;
		if(el == null)
		{
			return null;
		}
		if(el.left == null || el.right == null)
		{
			y = el;
		}
		else y = successorN(el.value);
		
		if(y.left != null)
		{
			x = y.left;
		}
		else x = y.right;
		if(x != null)
		{
			x.parent = y.parent;
		}
		if(y.parent == null)
		{
			root = x;
		}
		else if(y == y.parent.left)
		{
			y.parent.left = x;
		}
		else
		{
			y.parent.right = x;
		}
		if(y != el)
		{
			T tempo = el.value;
			el.value = y.value;
			y.value = tempo;
		}
		return y.value;
	}
	
	public void clear() {
		// TODO
		root = null;
		root.left = null;
		root.right = null;
		root.value = null;
	}

	public int size() {
		// TODO
		return size(root);
	}
	
	int size(Node el)
	{
		if(el != null)
		{
			return(size(el.right) + 1 + size(el.left));
		}
		else return 0;
	}
}