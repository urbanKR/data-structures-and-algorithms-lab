package dataStructuresLab07Package;

import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.Iterator;

public class HashTable{
//	TwoWayCycledOrderedListWithSentinel arr[];
	LinkedList arr[]; // use pure array
	private final static int defaultInitSize=8;
	private final static double defaultMaxLoadFactor=0.7;
	private int size;	
	private final double maxLoadFactor;
	public HashTable() {
		this(defaultInitSize);
	}
	public HashTable(int size) {
		this(size,defaultMaxLoadFactor);
	}

	public HashTable(int initCapacity, double maxLF) {
		//TODO
		this.maxLoadFactor=maxLF; 
		arr = new LinkedList[initCapacity];
		for(int i=0; i<arr.length; i++) {
			arr[i] = new LinkedList();
		}
	}

	public boolean add(Object elem) {
		//TODO
		int n = this.arr.length;
		if(this.arr[elem.hashCode()%n].contains(elem))
		{
			return false;
		}
		this.size++;
		this.arr[elem.hashCode()%n].add(elem);
		if((double)this.size/n > maxLoadFactor) 
		{
			doubleArray();
		}
		return true;
	}
	private void doubleArray() {
		//TODO
		int n = arr.length;
		LinkedList[]copy = this.arr.clone();
		this.arr = new LinkedList[n*2];
		this.size = 0;
		int count = 0;
		for(int i=0; i<arr.length; i++) {
			arr[i] = new LinkedList();
		}
		while(count < n) {
			for(int j=0; j<copy[count].size(); j++) {
				add(copy[count].get(j));
			}
			count++;
		}
	}


	@Override
	public String toString() {
		//TODO
		String retStr="";
//		if(isEmpty())
//		{
//			return "";
//		}
		for(int i=0; i<arr.length; i++) {
			retStr += i+":";
			for(int j=0; j<arr[i].size(); j++) {
				IWithName x = (IWithName) arr[i].get(j);
				if(j + 1 == arr[i].size())
				{
					retStr+=" "+x.getName();
					break;
				}
				retStr+=" "+x.getName()+",";
			}
			retStr+="\n";
		}
		return retStr;
	}

	public Object get(Object toFind) {
		//TODO
		int n = this.arr.length;
		if(this.arr[toFind.hashCode()%n].indexOf(toFind) != -1)
		{
			return this.arr[toFind.hashCode()%n].get(this.arr[toFind.hashCode()%n].indexOf(toFind));
		}
		return null;
	}	
}