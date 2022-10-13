package dataStructuresLab02Package;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Document{
	public String name;
	public OneWayLinkedList<Link> links;
	public Document(String name, Scanner scan) {
			this.name = name;
			links = new OneWayLinkedList<Link>();
			load(scan);
		}
	
	public void load(Scanner scan) {
		try {
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
					links.add(new Link(match.group(2).toLowerCase()));
				}
			}
			scan.close();
		} catch(Exception ex) {
			System.out.print(ex.getLocalizedMessage());
		} 
	}
	public static boolean correctLink(String link) {
		Pattern pattern = Pattern.compile("(link=)([a-z]\\w*)", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(link);
		if(match.find()) 
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
	    String str = "Document: " + this.name;
	    for(Link link:links) {
			str = str + "\n" + link.ref;
		}
	    
		return str;
	}

	
}