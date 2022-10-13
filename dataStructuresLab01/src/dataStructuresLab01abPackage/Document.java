package dataStructuresLab01abPackage;

import java.util.Scanner;

public class Document {
	public static void loadDocument(String name, Scanner scan) {
			String line = scan.next();
			while(!line.equals("eod")) {
				int m = line.indexOf("link=") + 6;
				if(line.toLowerCase().contains("link="))
				{
					if(line.length() >= 6 && line.charAt(m) != ' ' && Character.isLetter(line.charAt(5)))
					{
						line = line.toLowerCase();
						String link = line.substring(line.indexOf("link=") + 5, line.length());
						System.out.print(link);
						System.out.print("\n");
					}
				}
				line = scan.next();
			}
	}
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static boolean correctLink(String link) {
		link.toLowerCase();
		return true;
	}

}
