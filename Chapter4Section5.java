package com.fastcampus.functionalprogramming.chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fastcampus.functionalprogramming.chapter4.model.User;

public class Chapter4Section5 {
	public static void main(String[] args) {
		List<User> users = new ArrayList<User>();
		users.add(new User(3, "Alice"));
		users.add(new User(1, "Charlie"));
		users.add(new User(5, "Bob"));
		System.out.println("sort 전");
		System.out.println(users);
		
		// id 순서대로 sort
		Comparator<User> idComparator = (User u1, User u2) -> {
			return u1.getId() - u2.getId();
		};
		Collections.sort(users, idComparator);
		System.out.println("id sort 후");
		System.out.println(users);
		
		// 이름 순서대로 sort
		// compareTo() : string을 비교할 때 사용(알파벳 순으로 정렬)
		// Comparator를 따로 만들지 않고 생성과 동시에 argument로 넣어버린다.
		Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
		System.out.println("name sort 후");
		System.out.println(users);
	}
}