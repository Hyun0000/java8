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
	    System.out.println(orderStatusToSumOfAmountMap);
	}
}