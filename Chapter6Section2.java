package com.fastcampus.functionalprogramming.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.User;
import com.fastcampus.functionalprogramming.chapter6.model.Order.OrderStatus;

public class Chapter6Section2 {
	public static void main(String[] args) {
		// 1.
		// 양수만 걸러내는 filter를 Stream 형식으로 만들어보자
		Stream<Integer> numberStream = Stream.of(-5, -3, 3, 7, 10);
		Stream<Integer> filterdNumberStream = numberStream.filter(x -> x > 0);
		List<Integer> filterdNumbers = filterdNumberStream.collect(Collectors.toList());
		System.out.println(filterdNumbers);
		
		// 간단
		List<Integer> filterdNumbers2 = Stream.of(-5, -3, 3, 7, 10)		// Stream을 만들고
										.filter(x -> x > 0)				// filter를 하고
										.collect(Collectors.toList());	// List로 만든다.
		System.out.println(filterdNumbers2);
// ===================================================================================================
		// 2.
		// 이메일이 검증(true)된 유저만 걸러내기, 검증 여부는 User class의 isVerified 필드로 구분
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
		List<User> verifiedUsers = users.stream().filter(user -> user.isVerified()).collect(Collectors.toList());
		System.out.println(verifiedUsers);
		// cf) stream() method : Collection을 Stream으로 바꿔주는 method(SET과 LIST 모두 사용 가능)
		
		// Method Reference를 이용해 더 간단하게
		// filter(user -> user.isVerified()) --> filter(User::isVerified)
		List<User> users2 = Arrays.asList(user1, user2, user3);
		List<User> verifiedUsers2 = users.stream().filter(User::isVerified).collect(Collectors.toList());
		System.out.println(verifiedUsers2);
		
		// 검증 되지 않은 user만 뽑기
		List<User> users3 = Arrays.asList(user1, user2, user3);
		List<User> unVerifiedUsers = users.stream()
				.filter(user -> !user.isVerified())
				.collect(Collectors.toList());
		System.out.println(unVerifiedUsers);
// ===================================================================================================
		// 3.
		// Stream을 이용해 ERROR 상태인 order만 걸러내기
		Order order1 = new Order();
		order1.setId(1001);
		order1.setStatus(OrderStatus.CREATED);	   // enum 사용
		
		Order order2 = new Order();
		order2.setId(1002);
		order2.setStatus(OrderStatus.ERROR);	   // enum 사용
		
		Order order3 = new Order();
		order3.setId(1003);
		order3.setStatus(OrderStatus.PROCESSED);   // enum 사용
		
		Order order4 = new Order();
		order4.setId(1004);
		order4.setStatus(OrderStatus.ERROR);	   // enum 사용
		
		Order order5 = new Order();
		order5.setId(1005);
		order5.setStatus(OrderStatus.IN_PROGRESS); // enum 사용
		
		List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);
		List<Order> filteredOrders = orders.stream().filter(order -> order.getStatus() == OrderStatus.ERROR).collect(Collectors.toList());
		System.out.println(filteredOrders);
	}
}