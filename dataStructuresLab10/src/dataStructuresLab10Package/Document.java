package dataStructuresLab10Package;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import java.util.*;

public class Document implements IWithName{
	public String name;
	// TODO? You can change implementation of Link collection
	public LinkedList<Link> link;
	
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new LinkedList<Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new LinkedList<Link>();
		load(scan);
	}

	public void load(Scanner scan) {
		Pattern patt = Pattern.compile("(link=)([a-z]\\w*)(\\()?(\\d+)?(\\))?", Pattern.CASE_INSENSITIVE);
		Matcher match;
		while(scan.hasNextLine()) {
			String word = scan.nextLine();
			if(word.equals("eod")) 
			{
				return;
			}
			match = patt.matcher(word);
			while(match.find()) {
				if(match.group(3) == null) {
					link.add(new Link(match.group(2).toLowerCase()));
				} 
				else 
				{
					int value = Integer.valueOf(match.group(4));
					if(value > 0)
					{
						link.add(new Link(match.group(2).toLowerCase(), value));
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

	@Override
	public String toString() {
		String retStr="Document: "+name+"\n";
		//TODO?
		retStr+=link;		
		return retStr;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String getName() {
		return name;
	}
}
