package dataStructuresLab09Package;

import java.util.LinkedList;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;
	}

	private static final int NULL=-1;

	Element arr[];

	public DisjointSetLinkedList(int size) {
		//TODO
		this.arr = new Element[size];
	} 

	@Override
	public void makeSet(int item) {
		//TODO
		Element el = arr[item] = new Element();
		el.last = item;
		arr[item].length = 1;
		arr[item].next = -1;
		arr[item].representant = item;
	}

	@Override
	public int findSet(int item) {
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		int x,y;
		if(arr[itemA].length >= arr[itemB].length)
		{
			x = findSet(itemA);
			y = findSet(itemB);
		}
		else
		{
			y = findSet(itemA);
			x = findSet(itemB);
		}
		if(x == y)
		{
			return false;
		}
		arr[arr[x].last].next = y;
		int z = y;
		while(z != -1) {
			arr[z].representant = x;
			z = arr[z].next;
		}
		arr[x].last = arr[y].last;
		arr[x].length += arr[y].length;
		return true;
	}
	@Override
	public String toString() {
		String retStr="Disjoint sets as linked list:\n";
		boolean flagArr[] = new boolean[arr.length];
		for(int j=0; j<flagArr.length; j++) {
			flagArr[j] = false;
		}
		int x = 0;
		for(int i=0; i<arr.length-1; i++) {
			x = findSet(i);
			if(flagArr[x] == false)
			{
				flagArr[x] = true;
				while(x != -1) {
					retStr += x+", ";
					x = arr[x].next;
				}
				retStr = retStr.substring(0, retStr.length()-2);
				retStr += "\n";
			}
			
		}
		return retStr.trim();
	}

}
