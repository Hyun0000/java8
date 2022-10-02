package com.fastcampus.functionalprogramming.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.Order.OrderStatus;
import com.fastcampus.functionalprogramming.chapter6.model.User;

public class Chapter6Section3 {
	public static void main(String[] args) {
		Function<Integer, Integer> doubleFunc = (Integer x) -> {
			return x * 2;
		};
		
		List<Integer> numberList = Arrays.asList(-4, 3, 6);
		
		// 1.
		// x2가 되게 해보자
		Stream<Integer> numberStream = numberList.stream();
		Stream<Integer> numberStream2 = numberStream.map(doubleFunc);
		// 간단 : Stream<Integer> numberStream2 = numberStream.map(x -> x * 2);
		List<Integer> numberListX2 = numberStream2.collect(Collectors.toList());
		System.out.println(numberListX2);
		
		// 1-1.
		// x3이 되게 해보자
		List<Integer> numberListX3 = numberList.stream().map(x -> x * 3).collect(Collectors.toList());
		System.out.println(numberListX3);
		
		// 2.
		// output 타입을 다르게(input : Integer / output : String)
		Stream<Integer> numberListStream = numberList.stream();
		Stream<String> strStream = numberListStream.map(x -> "Number is " + x);
		List<String> strList = strStream.collect(Collectors.toList());
		System.out.println(strList);
// ===================================================================================================
		// User object가 담긴 List를 이용해 user의 이메일만 담겨있는 List를 만들자
		User user1 = new User();
		user1.setId(101);
		user1.setName("Alice");
		user1.setVerified(true);
		user1.setEmailAddress("Alice@garden.com");
		
		User user2 = new User();
		user2.setId(102);
		user2.setName("Bob");
		user2.setVerified(false);
		user2.setEmailAddress("Bob@garden.com");
		
		User user3 = new User();
		user3.setId(103);
		user3.setName("Charlie");
		user3.setVerified(true);
		user3.setEmailAddress("Charlie@garden.com");
		
		List<User> users = Arrays.asList(user1, user2, user3);
		List<String> emailAddresses = users.stream().map(User::getEmailAddress).collect(Collectors.toList());
		// User::getEmailAddress --> Method Reference : 각 object 마다 object 안에 있는 instance method 적용
		// 다른 방법 : List<String> emailAddresses = users.stream().map(user -> user.getEmailAddress()).collect(Collectors.toList());
		System.out.println(emailAddresses);
// ===================================================================================================
		// Order object가 담긴 List를 이용해 createdByUserId 만 모여있는 List를 만들자
		Order order1 = new Order();
		order1.setId(1001);
		order1.setStatus(OrderStatus.CREATED);	   // enum 사용
		order1.setCreatedByUserId(101);
		
		Order order2 = new Order();
		order2.setId(1002);
		order2.setStatus(OrderStatus.ERROR);	   // enum 사용
		order2.setCreatedByUserId(103);
		
		Order order3 = new Order();
		order3.setId(1003);
		order3.setStatus(OrderStatus.PROCESSED);   // enum 사용
		order3.setCreatedByUserId(102);
		
		Order order4 = new Order();
		order4.setId(1004);
		order4.setStatus(OrderStatus.ERROR);	   // enum 사용
		order4.setCreatedByUserId(104);
		
		Order order5 = new Order();
		order5.setId(1005);
		order5.setStatus(OrderStatus.IN_PROGRESS); // enum 사용
		order5.setCreatedByUserId(101);
		
		List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);
		List<Long> createdByUserIds = orders.stream().map(Order::getCreatedByUserId).collect(Collectors.toList());
		System.out.println(createdByUserIds);
	}
}