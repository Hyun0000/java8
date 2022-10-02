package com.fastcampus.functionalprogramming.chapter6;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.Order.OrderStatus;

public class Chapter6Section6 {
	public static void main(String[] args) {
		// 1. 중복된 숫자 제거
		List<Integer> numbers = Arrays.asList(3, -5, 4, -5, 2, 3);
		List<Integer> distinctNumbers = numbers.stream()
										.distinct()
										.collect(Collectors.toList());
		System.out.println(distinctNumbers);
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
		
		/* 
		 * 0. orders List에서
		 * 1. createdByUserId만 추출해서
		 * 2. 중복되는 createdByUserId는 제거하고
		 * 3. 정렬된
		 * 4. createdByUserId가 담긴 List를 만들자.
		 */
		// OOP 방식으로 했다면 코드가 아래 내용에 비해 매우 길어졌을 것이다.
		List<Long> userIds = orders.stream()
								.map(Order::getCreatedByUserId)
								.distinct()
								.sorted()
								.collect(Collectors.toList());
		System.out.println(userIds);
	}
}