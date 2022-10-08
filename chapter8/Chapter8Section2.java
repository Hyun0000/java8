package com.fastcampus.functionalprogramming.chapter8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.fastcampus.functionalprogramming.chapter8.model.Order;
import com.fastcampus.functionalprogramming.chapter8.model.Order.OrderStatus;
import com.fastcampus.functionalprogramming.chapter8.model.User;

public class Chapter8Section2 {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(3, -4, 2, 7, 9);
		
		// '모든' 숫자가 양수인가?
		boolean allPositive = numbers.stream().allMatch(number -> number > 0);
		System.out.println("allPositive? : " + allPositive);
		
		// 음수가 '1개 이상' 있는가?
		boolean anyNegative = numbers.stream().anyMatch(number -> number < 0);
		System.out.println("anyNegative? : " + anyNegative);
// ===================================================================================================
		User user1 = new User()
				.setId(101)
				.setName("Alice")
				.setVerified(true)
				.setEmailAddress("alice@fastcampus.co.kr");
		User user2 = new User()
				.setId(102)
				.setName("Bob")
				.setVerified(false)
				.setEmailAddress("bob@fastcampus.co.kr");
		User user3 = new User()
				.setId(103)
				.setName("Charlie")
				.setVerified(false)
				.setEmailAddress("charlie@fastcampus.co.kr");
		
		List<User> users = Arrays.asList(user1, user2, user3);
		
		// TODO : user들이 '전부' 검증이 됐는지 알아보자
		// 내 풀이
		Boolean areAllUserVerified = users.stream().map(User::isVerified).allMatch(x -> x == true);
		System.out.println("allVerified1? : " + areAllUserVerified);
		
		// 강사 풀이 - 훨씬 더 간단하다.
		Boolean areAllUserVerified2 = users.stream().allMatch(User::isVerified);
		System.out.println("allVerified2? : " + areAllUserVerified2);
// ===================================================================================================
		// 숙제
		Order order1 = new Order()
			.setId(1001L)
			.setAmount(BigDecimal.valueOf(2000))
			.setStatus(OrderStatus.CREATED);
    	Order order2 = new Order()
			.setId(1002L)
			.setAmount(BigDecimal.valueOf(4000))
			.setStatus(OrderStatus.ERROR);
    	Order order3 = new Order()
			.setId(1003L)
			.setAmount(BigDecimal.valueOf(3000))
			.setStatus(OrderStatus.ERROR);
    	Order order4 = new Order()
			.setId(1004L)
			.setAmount(BigDecimal.valueOf(7000))
			.setStatus(OrderStatus.PROCESSED);
    	
        List<Order> orders = Arrays.asList(order1, order2, order3, order4);
        
        // ERROR 상태인 order가 '최소 1개 이상'이면 true를 출력
        Boolean isErrorOrderExist = orders.stream().anyMatch(order -> order.getStatus() == OrderStatus.ERROR);
        System.out.println(isErrorOrderExist);
	}
}