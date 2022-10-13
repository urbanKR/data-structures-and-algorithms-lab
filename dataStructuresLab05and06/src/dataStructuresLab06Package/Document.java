package dataStructuresLab06Package;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name, Scanner scan) {
		this.name = name.toLowerCase();
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		while(scan.hasNext()) {
			for(String txt : scan.next().split(" ")) {
				if(txt.equals("eod")) 
				{
					return;
				}
				if(txt.toLowerCase().startsWith("link=")) 
				{
					txt = txt.substring(5);
					Link nLink = createLink(txt);
					if(nLink != null) 
					{
						this.link.add(nLink);
					}
				}
			}
		}
	}
	public static boolean isCorrectId(String id) {
		if(!(id.matches("^[A-Za-z][A-Za-z0-9_]*$"))) 
		{
			return false;
		}
		return true;
	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	static Link createLink(String link) {
		Pattern pattern = Pattern.compile("^([A-Za-z][A-Za-z0-9_]*)(?:\\((\\d+)\\))?$");
		Matcher match = pattern.matcher(link);
		if(match.matches()) 
		{
			if(match.group(2) == null) 
			{
				return new Link(match.group(1).toLowerCase());
			} 
			else 
			{
				return new Link(match.group(1).toLowerCase(), Integer.parseInt(match.group(2)));
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Document: " + name + "\n");
		int l = 0;
		for(Link lin : link) {
			s.append(lin);
			l++;
			if(l % 10 == 0)
			{
				s.append('\n');
			}
			else 
			{
				s.append(' ');
			}
		}
		s.delete(s.length()-1, s.length());
		return s.toString();
	}

	public String toStringReverse() {
		StringBuilder s = new StringBuilder();  
		s.append("Document: " + name + "\n");
		ListIterator<Link> it = link.listIterator();
		while(it.hasNext()) {
			it.next();
		}
		int l = 0;
		while(it.hasPrevious()) {
			l++;
			s.append(it.previous().toString());
			if(l % 10 == 0)
			{
				s.append('\n');
			}
			else 
			{
				s.append(' ');
			}
		}
		s.delete(s.length()-1, s.length());
		return s.toString();
	}

	public int[] getWeights() {
		//TODO
		int i = 0;
		for(Link lin : link) {
			i++;
		}
		int weightArr[] = new int[i];
		int j = 0;
		StringBuilder sb = new StringBuilder();
		String s = "";
		int nr;
		for(Link lin : link) {
			sb.append(lin);
			s = sb.toString();
			s = s.replaceAll("[^\\d]","");
			weightArr[j] =  Integer.parseInt(s);
			j++;
			sb.setLength(0);
		}
		return weightArr;
	}

	public static void showArray(int[] arr) {
		// TODO
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]);
			if(i != arr.length - 1)
			{
				System.out.print(" ");
			}
		}
		System.out.println();
	}

	void bubbleSort(int[] arr) {
		showArray(arr);
		//TODO
		for(int i = arr.length - 1; i > 0; i--) {
        	for(int j = arr.length - 1; j >0 ; j--) {
        		if(arr[j-1] > arr[j]) 
                {
                    int x = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = x;
                }
        	}
        	showArray(arr);
        }
	}

	public void insertSort(int[] arr) {
//		showArray(arr);
		//TODO
		int El;
		int x, j;
		for(int i=arr.length-1 ; i>=0; i--) {
			j = i+1;
			El = arr[i];
			while(j < arr.length && El > arr[j]) {
					x = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = x;
					j++;
			}
			showArray(arr);
		}
		
	}
	public void selectSort(int[] arr) {
		showArray(arr);
		//TODO
		int min, x;
		for(int i=arr.length-1; i>0; i--) {
			min = i;
			for(int j=i-1; j>=0; j--) {
				if(arr[j] > arr[min])
				{
					min = j;
				}
			}
			if(min != i) 
			{
				x = arr[min];
				arr[min] = arr[i];
				arr[i] = x;
			}
			showArray(arr);
		}
	}
	
	public static void mergeArr(int arr[], int leftBound, int rightBound, int mid) {
		   int i, j, k;
		   int length1 = mid + 1 - leftBound;
		   int length2 = rightBound-mid;
		   int[] leftA = new int[length1];
		   for(i=0;i<length1;i++) {
			   leftA[i] = arr[leftBound+i];
		   }
		   int[] rightA = new int[length2];
		   for(j=0; j<length2; j++) {
			   rightA[j] = arr[mid+1+j];
		   }
		   j = 0;
		   i = 0;
		   k = leftBound;
		   while(i<length1 && j<length2) {
		      if(leftA[i] <= rightA[j]) 
		      {
		         arr[k] = leftA[i];
		         i++;
		      } 
		      else 
		      {
		         arr[k] = rightA[j];
		         j++;
		      }
		      k++;
		   }	
		   while(i < length1) {
		      arr[k] = leftA[i];      
		      k++;
		      i++;
		   }
		   
		   while(j < length2) {
			   
		      arr[k] = rightA[j];
		      k++;
		      j++;
		   }
		   
		   
		}
	
	public void iterativeMergeSort(int[] arr) {
		showArray(arr);
	    for(int i=1; i<arr.length; i=i*2)  {
	        for(int j=0; j<arr.length; j+=i*2) {
	        	int mid;
	        	int bound = 0;
	          	if(i*2+j-1 < arr.length-1)
	        	{
	          		bound = i*2+j-1;
	        	}
	        	else
	        	{
	        		bound = arr.length-1;
	        	}
	        	if(i+j-1 < arr.length-1)
	        	{
	        		mid = i+j-1;
	        	}
	        	else
	        	{
	        		mid = arr.length-1;
	        	}
	          	mergeArr(arr, j, bound, mid);
	        }
	        showArray(arr);
	    }
	}
	
    static void countSort(int arr[], int dgt) {
    	int mult = 1;
    	for(int n=1; n<dgt; n++) {
    		mult = mult*10;
    	}
        
        int countArr[] = new int[10];
        for(int l=0; l<countArr.length; l++) {
        	countArr[l] = 0;
        }
        int resultArr[] = new int[arr.length];
        for(int i=0; i<arr.length; i++) {
        	countArr[arr[i]/mult%10]++;
        }

        for(int j=1; j<10; j++) {
        	countArr[j] = countArr[j] + countArr[j-1];
        }

        for(int k=arr.length-1; k>=0; k--) {
        	resultArr[countArr[arr[k]/mult%10] - 1] = arr[k];
            countArr[arr[k]/mult%10]--;
        }
        for(int m=0; m<arr.length; m++) {
        	arr[m] = resultArr[m];
        }    
    }
	
	public void radixSort(int[] arr) {
		showArray(arr);
		int max = 1;
		for(int j=0; j<arr.length; j++) {
			if(arr[j] > 99)
			{
				max = 3;
				break;
			}
			if(arr[j] > 9)
			{
				max = 2;
			}
		}
        for(int i=1; i<=max; i++) {
        	countSort(arr, i);
        	showArray(arr);
        }  
        showArray(arr);
	}
}
