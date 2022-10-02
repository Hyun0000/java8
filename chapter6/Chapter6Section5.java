package com.fastcampus.functionalprogramming.chapter6;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.User;
import com.fastcampus.functionalprogramming.chapter6.model.Order.OrderStatus;

public class Chapter6Section5 {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(3, -5, 7, 4);
		List<Integer> sortedNumbers = numbers.stream()
									.sorted()
									.collect(Collectors.toList());
		System.out.println(numbers);
		System.out.println(sortedNumbers);
// ===================================================================================================
		User user1 = new User();
		user1.setId(101);
		user1.setName("Paul");
		user1.setVerified(true);
		user1.setEmailAddress("Alice@garden.com");
		
		User user2 = new User();
		user2.setId(102);
		user2.setName("David");
		user2.setVerified(false);
		user2.setEmailAddress("Bob@garden.com");
		
		User user3 = new User();
		user3.setId(103);
		user3.setName("John");
		user3.setVerified(false);
		user3.setEmailAddress("Charlie@garden.com");
		
		// user는 아무런 기준 없이 sort를 할 수 없기에 무엇을 기준으로 sort를 할 지 알려줘야한다.
		List<User> users = Arrays.asList(user1, user2, user3);
		
		// 이름 알파벳 순으로 user 정렬
		// ((u1 ,u2) -> u1.getName().compareTo(u2.getName())) == Comparator
		List<User> sortedUsers = users.stream()
								.sorted((u1 ,u2) -> u1.getName().compareTo(u2.getName()))
								.collect(Collectors.toList());
		for (User user : users) {
			System.out.println(user);
		}
		
		System.out.println("========= 정렬 후 =========");
			
		for (User user : sortedUsers) {
			System.out.println(user);
		}
// ===================================================================================================
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		Order order1 = new Order();
		order1.setId(1001);
		order1.setStatus(OrderStatus.CREATED);	   // enum 사용
		order1.setCreatedByUserId(101);
		order1.setCreatedAt(now.minusHours(4));
		
		Order order2 = new Order();
		order2.setId(1002);
		order2.setStatus(OrderStatus.ERROR);	   // enum 사용
		order2.setCreatedByUserId(103);
		order2.setCreatedAt(now.minusHours(1));
		
		Order order3 = new Order();
		order3.setId(1003);
		order3.setStatus(OrderStatus.PROCESSED);   // enum 사용
		order3.setCreatedByUserId(102);
		order3.setCreatedAt(now.minusHours(36));
		
		Order order4 = new Order();
		order4.setId(1004);
		order4.setStatus(OrderStatus.ERROR);	   // enum 사용
		order4.setCreatedByUserId(104);
		order4.setCreatedAt(now.minusHours(40));
		
		Order order5 = new Order();
		order5.setId(1005);
		order5.setStatus(OrderStatus.IN_PROGRESS); // enum 사용
		order5.setCreatedByUserId(101);
		order5.setCreatedAt(now.minusHours(10));
		
		List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);
		
		// createdAt 기준으로 sort
		List<Order> sortedOrders = orders.stream()
								.sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()))
								.collect(Collectors.toList());
		for (Order order : sortedOrders) {
			System.out.println(order);
		}
		/*
		 * 난 sorted에서 아래와 같이 했다.
		 * .sorted((o1, o2) -> String.valueOf(o1.getCreatedAt()).compareTo(String.valueOf(o2.getCreatedAt())))
		 */
	}
}