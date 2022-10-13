package dataStructuresLab09Package;


public class DisjointSetForest implements DisjointSetDataStructure {

	private class Element{
		int rank;
		int parent;
	}

	Element []arr;

	public DisjointSetForest(int size) {
		//TODO
		this.arr = new Element[size];
	}

	@Override
	public void makeSet(int item) {
		//TODO
		Element el = arr[item] = new Element();
		el.parent = item;
		el.rank = 0;
	}

	@Override
	public int findSet(int item) {
		//TODO
		if(item != arr[item].parent)
		{
			arr[item].parent = findSet(arr[item].parent);
		}
		return arr[item].parent;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		//TODO
		if(findSet(itemA) == findSet(itemB))
		{
			return false;
		}
		conectTree(findSet(itemA), findSet(itemB));
		return true;
	}

	private void conectTree(int a, int b) {
		if(arr[a].rank > arr[b].rank)
		{
			arr[b].parent = a;
		}
		else
		{
			arr[a].parent = b;
			if(arr[a].rank == arr[b].rank)
			{
				arr[b].rank++;
			}
		}
	}

	@Override
	public String toString() {
		String retStr="Disjoint sets as forest:\n";
		int x = 0;
		for(int i=0; i<arr.length; i++) {
			x = arr[i].parent;
			retStr += i+" -> "+x;	
//			retStr = retStr.substring(0, retStr.length()-2);
			retStr += "\n";
		}
		return retStr.trim();
	}
}
