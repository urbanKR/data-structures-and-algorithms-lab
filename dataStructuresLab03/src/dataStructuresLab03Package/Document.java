package dataStructuresLab03Package;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Document{
	public String name;
	public TwoWayUnorderedListWithHeadAndTail<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name;
		link=new TwoWayUnorderedListWithHeadAndTail<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		
			Pattern pattern = Pattern.compile( "(link=)([a-z]\\w*)", Pattern.CASE_INSENSITIVE);
			Matcher match;
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				if(line.equals("eod") && line.length() == 3)
				{
				    return;
				}
				match = pattern.matcher(line);
				while(match.find()) {
					link.add(new Link(match.group(2).toLowerCase()));
				}
			}
	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static boolean correctLink(String links) {
		Pattern pattern = Pattern.compile("(link=)([a-z]\\w*)", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(links);
		if(match.find()) 
		{
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
	    String str = "Document: " + this.name;
	    for(Link link:link) {
			str = str + "\n" + link.ref;
		}
	    
		return str;
	}

	public String toStringReverse() {
		String retStr = "Document: " + name;
		return retStr + link.toStringReverse();
	}
	
}