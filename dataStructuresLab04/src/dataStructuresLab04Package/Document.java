package dataStructuresLab04Package;

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
	private static Link createLink(String link) {
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
}