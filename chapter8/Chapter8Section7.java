package com.fastcampus.functionalprogramming.chapter8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fastcampus.functionalprogramming.chapter8.model.Order;
import com.fastcampus.functionalprogramming.chapter8.model.Order.OrderStatus;

public class Chapter8Section7 {
	public static void main(String[] args) {
		// 같은 일의 자리수끼리 묶어보자 --> 형태 : Map<일의 자리수, 같은 일의 자리수를 가지는 숫자들>
		List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
		Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
				.collect(Collectors.groupingBy(number -> number % 10));
		System.out.println(unitDigitMap);
// ===================================================================================================		
		// Map<Integer, List<Integer>> 대신 Map<Integer, Set<Integer>>의 형태로 받아보자
		List<Integer> numbers2 = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
		Map<Integer, Set<Integer>> unitDigitSet = numbers2.stream()
				.collect(Collectors.groupingBy(number -> number % 10, Collectors.toSet()));
		System.out.println(unitDigitSet); // 중복값인 203이 하나만 들어있다.
// ===================================================================================================		
		List<Integer> numbers3 = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
		Map<Integer, List<String>> unitDigitStrMap = numbers3.stream()
				.collect(Collectors.groupingBy(number -> number % 10, Collectors.mapping(number -> "unit digit is " + number, Collectors.toList()))); 
		System.out.println(unitDigitStrMap.get(1));
		System.out.println(unitDigitStrMap.get(2));
		System.out.println(unitDigitStrMap.get(3));
		/*
		 * 순서
		 * 1. groupingBy method를 통해 List가 만들어진다.
		 * 2. 그 List에 (number -> "unit digit is " + number)를 적용시킨 후
		 * 3. Collectors.toList()를 통해 그것들을 담아 List<String> 형태로 반환한다. 
		 */
// ===================================================================================================
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
	    
	    // TODO OrderStatus 별로 Order 들을 묶어보자 --> Map<OrderStatus, List<Order>>
	    List<Order> orders = Arrays.asList(order1, order2, order3, order4);
	    Map<OrderStatus, List<Order>> orderStatusMap = orders.stream().collect(Collectors.groupingBy(Order::getStatus));
	    System.out.println(orderStatusMap.get(OrderStatus.CREATED));
	    System.out.println(orderStatusMap.get(OrderStatus.ERROR));
	    System.out.println(orderStatusMap.get(OrderStatus.PROCESSED));
// ===================================================================================================
	    // OrderStatus 별로 Order 들을 묶은 후 각 OrderStatus 별 Order들의 amount 합을 구해보자 --> Map<OrderStatus, BigDecimal>
	    List<Order> orders2 = Arrays.asList(order1, order2, order3, order4);
	    Map<OrderStatus, BigDecimal> orderStatusToSumOfAmountMap = orders2.stream().collect(
	    			Collectors.groupingBy(Order::getStatus, Collectors.mapping(
	    					Order::getAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))
	    		);
	    /*
	     * 내가 헷갈려서 순서 정리
	     * 
	     * 1. Collectors.groupingBy(Order::getStatus, ... 				--> OrderStatus 별로 각 order를 묶어라
	     * 2. Collectors.mapping 										--> OrderStatus 별로 묶인 order에 mapping을 할 것이다.
	     * 3. Order::getAmount 											--> OrderStatus 별로 묶인 order에서 amount만 가져와라
	     * 4. Collectors.reducing(BigDecimal.ZERO, BigDecimal::add) 	--> OrderStatus 별 amount를 더해라(기본값은 '0'으로 설정)
	     * 5. 그것을 Map<OrderStatus, BigDecimal> 형태로 반환해라
	     */
	    System.out.println(orderStatusToSumOfAmountMap);
	}
}