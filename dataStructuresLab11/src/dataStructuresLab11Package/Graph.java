package dataStructuresLab11Package;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;

public class Graph {
	int arr[][];
	//TODO? Collection to map Document to index of vertex 
	HashMap<String,Integer> name2Int;
	@SuppressWarnings("unchecked")
	//TODO? Collection to map index of vertex to Document
	// You can change it
	//	Entry<String, Document>[] arrDoc = (Map.Entry<String, Document>[])new Map.Entry[0];
	String[] arrDoc;

	// The argument type depend on a selected collection in the Main class
	public Graph(SortedMap<String,Document> internet) {

		int sizeI = internet.size();
		Document x[] = internet.values().toArray(new Document[0] );
		arr = new int[sizeI][sizeI];
		arrDoc = new String[x.length];
		name2Int = new HashMap<String, Integer>(x.length);
		for(int i=0; i<x.length; i++) {
			for(int j=0; j<sizeI; j++) {
				arr[i][j] = -1;
			}
			arrDoc[i] = x[i].getName();
			name2Int.put(x[i].getName(), i);
			arr[i][i] = 0;
		}      
		for(int k=0; k<x.length; k++) {
			for(int l=0; l<x[k].link.size(); l++) {
				//    
			
				int z = x[k].link.get(l).weight;
				int y = name2Int.get(x[k].link.get(l).ref);      
				arr[k][y] = z;
//				arr[y][k] = z;              
			}
		}
	}

	public String bfs(String start) {
		if(name2Int.get(start) != null)
		{
			StringBuilder sb;     
			String retStr = "";    
			boolean[]isW = new boolean[arr.length];
			for(int i=0; i<arr.length; i++) {
				isW[i] = true;
			}
			int x = name2Int.get(start);
			isW[x] = false;
			
			LinkedList<Integer> list = new LinkedList<Integer>();
			list.add(x);
			while(list.isEmpty() == false) {
				int y = list.poll();
				for(int i=0;i<arr.length;i++) {
					if(isW[i] && arr[y][i] > 0){
						list.add(i);
						isW[i] = false;
					}
				}
				isW[y] = false;
				retStr += arrDoc[y]+", ";
			}
			if(retStr.length() >= 3)
			{
				retStr = retStr.substring(0, retStr.length()-2);
			}
			return retStr;
		}
		return null;
	}

	
	public String dfs(String start) {
		StringBuilder retStr = new StringBuilder();
		boolean flagArr[] = new boolean[arr.length];
		for(int i=0; i<flagArr.length; i++) {
			flagArr[i] = false;
		}
		flagArr[name2Int.get(start)] = true;
		retStr.append(dfsR(flagArr, name2Int.get(start)));
		if(retStr.length() >= 3)
		{
			String s = retStr.toString();
			retStr = new StringBuilder();
			retStr.append(s.substring(0, s.length()-2));
		}
		return retStr.toString();
	}

	private String dfsR(boolean[] arrVisited, int actEl){
		StringBuilder retStr = new StringBuilder();
		retStr.append(arrDoc[actEl]);
		retStr.append(", ");
		for(int i = 0; i<arr.length; i++) {
			if(arrVisited[i] == false)
			{
				if(arr[actEl][i] > 0)
				{
					arrVisited[i]=true;
					retStr.append(dfsR(arrVisited, i));
				}
			}
		}
		return retStr.toString();
	}
	



	private void DjikstraR(int beginn, String s[], int[] sumW){
		for(int i=0; i<arr.length; i++) {
			int sum = arr[beginn][i] + sumW[beginn];
			if(sum < sumW[i] && arr[beginn][i] > 0)
			{
				sumW[i] = sumW[beginn] + arr[beginn][i];
				s[i] = s[beginn] + "->" + arrDoc[i];
				DjikstraR(i, s, sumW);
			}
		}
	}
	
	public String DijkstraSSSP(String startPoint) {
		if(name2Int.get(startPoint) != null)
		{
			int size = arr.length;
			String s[] = new String[size];
			for(int i=0; i<size; i++) {
				s[i] = "";
			}
			s[name2Int.get(startPoint)] = startPoint;
			int sumW[] = new int[arr.length];
			for(int j=0; j<sumW.length; j++) {
				sumW[j] = Integer.MAX_VALUE;
			}
			StringBuilder retStr = new StringBuilder();
			sumW[name2Int.get(startPoint)] = 0;
			
			int start = name2Int.get(startPoint);
			DjikstraR(start, s, sumW);
			for(int k=0; k<size; k++) {
				if(s[k] == "")
				{
					retStr.append("no path to " + arrDoc[k] + "\n");
				}
				else
				{
					retStr.append(s[k] + "=" + sumW[k]);
					retStr.append("\n");
				}

			}
			String out = retStr.toString();
			return out;
		}
		return null;
	}
	
	public int connectedComponents() {
		DisjointSetForest x = new DisjointSetForest(arr.length);
		for(int i=0; i<arr.length; i++) {
			x.makeSet(i);
		}
		for(int j=0; j<arr.length; j++) {
			for(int k=0; k<arr.length; k++) {
				if(arr[j][k] >= 1)
				{ 
					x.union(j, k);
				}
			}
		}
		int result = x.countSets();
		return result;
	}

}
