package com.fastcampus.functionalprogramming.chapter8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.fastcampus.functionalprogramming.chapter8.model.Order;
import com.fastcampus.functionalprogramming.chapter8.model.OrderLine;
import com.fastcampus.functionalprogramming.chapter8.model.User;

public class Chapter8Section4 {
	public static void main(String[] args) {
		// reduce를 이용해 Integer List의 합을 구해보자
		List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
		
		int sum = numbers.stream()
				.reduce((x, y) -> x + y)
				.get(); // Optional을 return 하기에 get()을 이용해 꺼낸다.
		System.out.println("총합 : " + sum);
		
		// min을 reduce를 이용해 구해보자
		int min = numbers.stream()
				.reduce((x, y) -> (x > y) ? y : x).get();
		System.out.println("가장 적은 수 : " + min);
// ===================================================================================================
		// 초기값을 제공
		List<Integer> numbers2 = Arrays.asList(1, 4, -2, -5, 3);
		int product = numbers2.stream()
					.reduce(1, (x, y) -> x * y); // 초기값으로 1 을 제공, 초기값이 있기에 get()을 쓰지 않아도 된다.
		System.out.println("곱 : " + product);
// ===================================================================================================
		// String으로 담겨있는 숫자의 합을 구하자
		List<String> numberStrList = Arrays.asList("3", "2", "5", "-4");
		
		// map + reduce
		int sumOfNumberStrList = numberStrList.stream()
				.map(x -> Integer.parseInt(x))
				.reduce(0, (x, y) -> x + y);
		System.out.println("sumOfNumberStrList : " + sumOfNumberStrList);
		
		// <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner); 이용
		// U type은 Integer / T type은 String 이다.
		// map + reduce 보다 복잡해 보이긴하다. 그래서 해당 method는 잘 사용하지는 않는다.
		int sumOfNumberStrList2 = numberStrList.stream()
				.reduce(0, (number, str) -> number + Integer.parseInt(str), (num1, num2) -> num1 + num2);
		System.out.println("sumOfNumberStrList2 : " + sumOfNumberStrList2);
// ===================================================================================================		
		User user1 = new User()
				.setId(101)
				.setName("Alice")
				.setFriendUserIds(Arrays.asList(201, 202, 203, 204));	// 친구 4명
	    User user2 = new User()
	    		.setId(102)
	    		.setName("Bob")
	    		.setFriendUserIds(Arrays.asList(204, 205, 206));		// 친구 3명
	    User user3 = new User()
	    		.setId(103)
	    		.setName("Charlie")
	    		.setFriendUserIds(Arrays.asList(204, 205, 207));		// 친구 3명
	    
	    List<User> users = Arrays.asList(user1, user2, user3);
	    
	    // 각 user들의 친구 수 총합을 구해보자
	    int sumOfNumberOfFriends = users.stream()
	    							.map(User::getFriendUserIds)
	    							.map(List::size)
	    							.reduce(0, (x, y) -> x + y);
	    System.out.println("친구 몇 명? " + sumOfNumberOfFriends);
// ===================================================================================================
	    // 숙제
	    Order order1 = new Order()
	    		.setId(1001L)
	    		.setOrderLines(Arrays.asList(
	    				new OrderLine().setAmount(BigDecimal.valueOf(1000)),
	    				new OrderLine().setAmount(BigDecimal.valueOf(2000))));
	    
        Order order2 = new Order()
        		.setId(1002L)
        		.setOrderLines(Arrays.asList(
        				new OrderLine().setAmount(BigDecimal.valueOf(2000)),
        				new OrderLine()
        				.setAmount(BigDecimal.valueOf(3000))));
        
        Order order3 = new Order()
        		.setId(1002L)
        		.setOrderLines(Arrays.asList(
        				new OrderLine().setAmount(BigDecimal.valueOf(1000)),
        				new OrderLine().setAmount(BigDecimal.valueOf(2000))));
        
        List<Order> orders = Arrays.asList(order1, order2, order3);
        
        // 각 order의 OrderLine 들의 총합을 구해보자
        BigDecimal sumOfAmounts = orders.stream()
        	.map(Order::getOrderLines)	// Stream<List<OrderLine>>
        	.flatMap(List::stream)		// Stream<OrderLine>
        	.map(OrderLine::getAmount)	// Stream<BigDecimal>
        	.reduce(BigDecimal.ZERO, BigDecimal::add);
        	// .reduce(BigDecimal.ZERO, (x, y) -> x.add(y));
        
        System.out.println("sumOfAmounts : " + sumOfAmounts);
	}
}