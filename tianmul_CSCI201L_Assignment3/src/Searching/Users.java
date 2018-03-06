package Searching;

import java.util.ArrayList;
import java.util.Arrays;

public class Users {
	public User [] users;
	public void addUser(User u) {
		ArrayList<User> arrayList;
		if (users == null) {
			arrayList = new ArrayList<User>();
		}
		else {
			arrayList = new ArrayList<User>(Arrays.asList(users));
		}
		arrayList.add(u);
		users = arrayList.toArray(new User[0]);
	}
	public void updateUser(User u) {
		ArrayList<User> arrayList = new ArrayList<User>(Arrays.asList(users));
		int i = this.findUser(u.username);
		if (i!=-1) {
			users[i] = u;
		}
	}
	public int findUser(String name) {
		for (int i=0;i<users.length;i++) {
			if (users[i].username.equals(name)) {
				return i;
			}
		}
		return -1;
	}
}
