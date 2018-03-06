package Searching;

import java.util.ArrayList;
import java.util.Arrays;

public class User {
	public String username;
	public String password;
	public String imageURL;
	public String [] followers;
	public String [] following;
	public Library library;
	public void addread(String title) {
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(library.read));
		if (!arrayList.contains(title)) {
			arrayList.add(title);
		}
		library.read = arrayList.toArray(new String[0]);	
	}
	public void addfav(String title) {
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(library.favorite));
		if (!arrayList.contains(title)) {
			arrayList.add(title);
		}
		library.favorite = arrayList.toArray(new String[0]);	
	}
}
