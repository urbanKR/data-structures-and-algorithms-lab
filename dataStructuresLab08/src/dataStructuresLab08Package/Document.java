package dataStructuresLab08Package;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

	

public class Document implements IWithName{
	private static final int MODVALUE=100000000;
	public String name;
	public BST<Link> link;
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		//TODO
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
	public static Link createLink(String link) {
		//TODO
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
//	@Override
//	public boolean equals(Object obj) {
//		if(!(obj instanceof Document))
//		{
//			return false;
//		}
//		else
//		{
//			return((Document)obj).getName().equals(this.name);
//		}
//	}
	@Override
	public String toString() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringInOrder();		
		return retStr;
	}

	public String toStringPreOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPreOrder();
		return retStr;
	}

	public String toStringPostOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPostOrder();
		return retStr;
	}
	
	@Override
	public int hashCode() {
		int hashC=0;
		int sequenceArr[] = {7,11,13,17,19};
		char ch;
		int i = 0;
		int j = 1;
		hashC = name.charAt(i)%MODVALUE;
		while(j < name.length()) {
			ch = name.charAt(j);
			hashC = (hashC * sequenceArr[i])%MODVALUE;
			hashC = (hashC + ch)%MODVALUE;
			i++;
			j++;
			if(i == sequenceArr.length)
			{
				i = 0;
			}
		}
		return hashC;
	}	

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Document))
		{
			return false;
		}
		else
		{
			return((Document)obj).getName().equals(this.name);
		}
	}
	@Override
	public String getName() {
		return name;
	}
}
