package com.fastcampus.functionalprogramming.chapter9;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.fastcampus.functionalprogramming.chapter9.model.Order;
import com.fastcampus.functionalprogramming.chapter9.model.OrderLine;
import com.fastcampus.functionalprogramming.chapter9.priceprocessor.OrderLineAggregationPriceProcessor;
import com.fastcampus.functionalprogramming.chapter9.priceprocessor.TaxPriceProcessor;

public class Chapter9Section3 {
	public static void main(String[] args) {
		// 아래의 두 함수를 (x * 2 + 10)으로 합성해보자
		Function<Integer, Integer> multiplyByTwo = x -> x * 2;
		Function<Integer, Integer> addTen = x -> x + 10;
		
		Function<Integer, Integer> composedFunction = multiplyByTwo.andThen(addTen);
		System.out.println(composedFunction.apply(3));
		/*
		 * 순서
		 * (1). multiplyByTwo : 3 * 2
		 * (2). addTen		  : 6 + 10
		 */
// ===================================================================================================
		// 각자의 기능을 갖고 있는 함수들을 합성해서 새로운 함수를 만들어보자
		// 목표 : order를 받아 해당 order의 가격을 process 해주는 함수를 만들어보자
		/*
		 * 현재 가격을 process 해주는 함수가 2개 존재하는 상황이다.
		 * 1. OrderLine의 가격 총합을 Order에 set 해주는 함수
		 * 2. Tax Rate를 적용해 Order 가격에 반영하는 함수
		 */
		Order unprocessedOrder = new Order()
								.setId(1001L)
								.setOrderLines(Arrays.asList(
										new OrderLine().setAmount(BigDecimal.valueOf(1000)),	// 1000원 짜리 주문
										new OrderLine().setAmount(BigDecimal.valueOf(2000))));	// 2000원 짜리 주문
		
		// 두 개의 PriceProcessor를 return 받았다.
		List<Function<Order, Order>> priceProcessors = getPriceProcessors();
		
		// getPriceProcessors method를 통해 받은 PriceProcessor 들을 하나로 합쳐서 '합성된 PriceProcessor'로 만들자
		Function<Order, Order> mergedPriceProcessors = priceProcessors.stream()
				.reduce(Function.identity(), Function::andThen); // Function::andThen을 이용해 코드를 더 간단하게
		// .reduce(Function.identity(), (priceProcessor1, priceProcessor2) -> priceProcessor1.andThen(priceProcessor2));
		/*
		 * Function.identity()를 이용해 reduce 작업의 초기값 제공
		 * 초기값 = Order를 받아 그대로 Order를 return 하는 Function<Order, Order> (다른 작업은 없다.)
		 */
				
		Order processedOrder = mergedPriceProcessors.apply(unprocessedOrder);
		System.out.println(processedOrder.getAmount());
	}
	
	// 두 개의 PriceProcessor를 return 하는 함수
	public static List<Function<Order, Order>> getPriceProcessors() {
		return Arrays.asList(
				new OrderLineAggregationPriceProcessor(), 
				new TaxPriceProcessor(new BigDecimal("9.375"))); // "9.375" = 적용할 세율
	}
}