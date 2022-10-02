package com.fastcampus.functionalprogramming.chapter6;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.Order.OrderStatus;
import com.fastcampus.functionalprogramming.chapter6.model.User;

public class Chapter6Section4 {
	public static void main(String[] args) {
		// 1. 유저리스트에서 검증되지 않은 유저의 이메일만 뽑아내자
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
		user3.setVerified(false);
		user3.setEmailAddress("Charlie@garden.com");
		
		List<User> users = Arrays.asList(user1, user2, user3);
		
		// 기존에 하던 방식
		List<String> emails = new ArrayList<String>();
		for (User user : users) {
			if(!user.isVerified()) {
				emails.add(user.getEmailAddress());
			}
		}
		System.out.println(emails);
		
		// 함수형 프로그래밍 방식
		List<String> emails2 = users.stream()
							.filter(user -> !user.isVerified())
							.map(User::getEmailAddress)
							.collect(Collectors.toList());
		System.out.println(emails2);
// ===================================================================================================
		// 대한민국 시간 설정
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		
		Order order1 = new Order();
		order1.setId(1001);
		order1.setStatus(OrderStatus.CREATED);	   // enum 사용
		order1.setCreatedByUserId(101);
		order1.setCreatedAt(now.minusHours(4));	   // 4시간 전에 order 생성
		
		Order order2 = new Order();
		order2.setId(1002);
		order2.setStatus(OrderStatus.ERROR);	   // enum 사용
		order2.setCreatedByUserId(103);
		order2.setCreatedAt(now.minusHours(1));	   // 1시간 전에 order 생성
		
		Order order3 = new Order();
		order3.setId(1003);
		order3.setStatus(OrderStatus.PROCESSED);   // enum 사용
		order3.setCreatedByUserId(102);
		order3.setCreatedAt(now.minusHours(36));   // 36시간 전에 order 생성
		
		Order order4 = new Order();
		order4.setId(1004);
		order4.setStatus(OrderStatus.ERROR);	   // enum 사용
		order4.setCreatedByUserId(104);
		order4.setCreatedAt(now.minusHours(15));   // 15시간 전에 order 생성
		
		Order order5 = new Order();
		order5.setId(1005);
		order5.setStatus(OrderStatus.IN_PROGRESS); // enum 사용
		order5.setCreatedByUserId(101);
		order5.setCreatedAt(now.minusHours(10));   // 10시간 전에 order 생성
		
		List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);
		
		// 2-1. ERROR 상태인 order만 골라내서 해당 order의 createdByUserId만 추출해 List로 생성
		List<Long> userIds = orders.stream()
							.filter(order -> order.getStatus() == OrderStatus.ERROR)
							.map(Order::getCreatedByUserId)
							.collect(Collectors.toList());
		System.out.println(userIds);
		
		// 2-2. ERROR 상태인 order & 주문 생성 시간이 현 시간 기준 24시간 이내인 order만 추출해 List로 생성
		List<Order> userIds2 = orders.stream()
							.filter(order -> order.getStatus() == OrderStatus.ERROR)
							.filter(order -> order.getCreatedAt().isAfter(now.minusHours(24)))
							.collect(Collectors.toList());
		System.out.println(userIds2);
	}
}