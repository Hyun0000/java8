package com.fastcampus.functionalprogramming.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import com.fastcampus.functionalprogramming.chapter5.model.User;

public class Chapter5Section2 {
	public static void main(String[] args) {
		// [ClassName::instanceMethodName - '해당 클래스의 객체(인스턴스)'를 매개변수(parameter)로 넘겨 해당 객체(인스턴스)의 instance method 메서드를 실행해주는 함수]
	
		// 1.
		// String class의 instance을 받아서 해당 instance 안에 있는 length를 호출해 주는 함수
		Function<String, Integer> strLength = String::length;
		System.out.println(strLength.apply("Hello world!")); // "Hello world!"가 'String class의 instance'인 것이다.
		System.out.println(strLength.apply("bye world!"));	 // "bye world!"가 'String class의 instance'인 것이다.
		
		// 2.
		// String을 받아서 해당 String의 .equals() 를 호출해서 또 다른 String과 비교해주는 함수
		BiPredicate<String, String> strEquals = String::equals;
		System.out.println(strEquals.test("hello", "apple")); // "apple"이 .equals()가 받을 String 이다.
		/*
		 * "hello"를 먼저 받아서 "hello"의 .equals() 를 호출
		 * .equals()의 인자로 "apple"을 받는다.
		 * 이런 모습 : "hello".equals("apple")
		 */
		System.out.println(strEquals.test("hello", "hello")); // 두 번째 "hello"가 .equals()가 받을 String 이다.
// ===================================================================================================
		List<User> users = new ArrayList<User>();
		users.add(new User(3, "Alice"));
		users.add(new User(1, "Charlie"));
		users.add(new User(5, "Bob"));
		
		// printUserField(users, (User user) -> user.getId());
		// printUserField(users, (User user) -> user.getName());
		// 위를 더 간단하게 해주는게 아래 코드이다.
		printUserField(users, User::getId);
		printUserField(users, User::getName);
	}
	
	// User의 field를 뽑아 오는 method
	public static void printUserField(List<User> users, Function<User, Object> getter) {
		// getId는 Integer, getName은 String을 return 하기에 최고 조상인 Object를 타입으로 지정한 것이다.
		// Function<User, Object> getter = User::getId;
		// Function<User, Object> getter = User::getName;
		for (User user : users) {
			System.out.println(getter.apply(user));
		}
	}
}