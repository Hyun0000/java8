package com.fastcampus.functionalprogramming.chapter8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fastcampus.functionalprogramming.chapter8.model.Order;
import com.fastcampus.functionalprogramming.chapter8.model.Order.OrderStatus;
import com.fastcampus.functionalprogramming.chapter8.model.User;

public class Chapter8Section6 {
	public static void main(String[] args) {
		// Stream.of(3, 5, -4, 2, 6)
		// 위 Stream의 각 Integer를 key로, "Number is ..."을 value로 갖는 Map을 만들어보자
		Map<Integer, String> numberMap = Stream.of(3, 5, -4, 2, 6)
									.collect(Collectors.toMap(x -> x, x -> "Number is " + x));
		System.out.println(numberMap);
		System.out.println("numberMap1 : " + numberMap.get(3));
		System.out.println("numberMap1 : " + numberMap.get(5));
		
		Map<Integer, String> numberMap2 = Stream.of(3, 5, -4, 2, 6)
									.collect(Collectors.toMap(Function.identity(), x -> "Number is " + x));
		// (x -> x)와 같이 단순한 함수의 경우 Function.identity()를 대신 사용해도 된다.
		// identity : Returns a function that always returns its input argument.
		System.out.println(numberMap2);
		System.out.println("numberMap2 : " + numberMap.get(-4));
		System.out.println("numberMap2 : " + numberMap.get(2));
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
	    
	    // 각 user들의 object가 담겨있는 List를 이용해 (id, user obejct) 형식의 Map을 만들어보자
	    Map<Integer, User> userIdToUserMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
	    
	    System.out.println(userIdToUserMap);
	    System.out.println(userIdToUserMap.get(101));
	    System.out.println(userIdToUserMap.get(102));
	    System.out.println(userIdToUserMap.get(103));
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
        
        // TODO : (id, OrderStatus) 형식의 Map을 만들어보자
        Map<Long, OrderStatus> idOrderStatusMap = orders.stream().collect(Collectors.toMap(Order::getId, Order::getStatus));
        System.out.println(idOrderStatusMap);
        System.out.println(idOrderStatusMap.get(1001L));
        System.out.println(idOrderStatusMap.get(1002L));
        System.out.println(idOrderStatusMap.get(1003L));
        System.out.println(idOrderStatusMap.get(1004L));
	}
}