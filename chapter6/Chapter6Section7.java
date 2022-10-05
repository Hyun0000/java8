package com.fastcampus.functionalprogramming.chapter6;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.OrderLine;
import com.fastcampus.functionalprogramming.chapter6.model.OrderLine.OrderLineType;

public class Chapter6Section7 {
	public static void main(String[] args) {
		// 1.
		// 목표 : 이차원 배열 cities를 도시들을 담고있는 List로 만들어보자
		String[][] cities = new String[][] {
			{ "Seoul", "Busan" },
			{ "San Francisco", "New York" },
			{ "Madrid", "Barcelona" }
		};
		
		// 1-1. map method를 이용해보자
		Stream<String[]> cityStream = Arrays.stream(cities); // Arrays.stream() method를 이용하면 배열을 Stream으로 만들 수 있다.
		/*
		 * 현재 cityStream에는 아래와 같은 형태로 데이터가 들어있다.(String[]가 하나씩 들어있다.)
		 * { "Seoul", "Busan" }				--> 한 덩어리(String[] 형태로 들어있다.)
		 * { "San Francisco", "New York" } 	--> 한 덩어리(String[] 형태로 들어있다.)
		 * { "Madrid", "Barcelona" }		--> 한 덩어리(String[] 형태로 들어있다.)
		 */
		
		Stream<Stream<String>> cityStreamStream = cityStream.map(x -> Arrays.stream(x));
		/*
		 * 현재 cityStreamStream에는 아래와 같은 형태로 데이터가 들어있다.
		 * { "Seoul", "Busan" }				--> 이 한 덩어리가 Stream<String>이 되었다.
		 * { "San Francisco", "New York" } 	--> 이 한 덩어리가 Stream<String>이 되었다.
		 * { "Madrid", "Barcelona" }		--> 이 한 덩어리가 Stream<String>이 되었다.
		 * 
		 * Arrays.stream() method는 Stream을 return 하기 때문이다.
		 */
		
		// cityStreamStream을 List로 만들어보자
		List<Stream<String>> cityStreamList = cityStreamStream.collect(Collectors.toList());
		/*
		 * 현재 cityStreamList에는 아래와 같은 형태로 데이터가 들어있다.
		 * { "Seoul", "Busan" }				--> cityStreamList의 0번 index(Stream<String> 형태로 들어있다.)
		 * { "San Francisco", "New York" } 	--> cityStreamList의 1번 index(Stream<String> 형태로 들어있다.)
		 * { "Madrid", "Barcelona" }		--> cityStreamList의 2번 index(Stream<String> 형태로 들어있다.)
		 */
		System.out.println("cityStreamList : " + cityStreamList);
		/*
		 * - 결과적으로 List인데 그 안에 Stream<String>이 들어있는 이상한 List가 만들어졌다. --> List<Stream<String>>
		 * - cityStreamList 안에는 Stream이 들어있기 때문에 원하는 값을 출력할 수도 없다.
		 * - 나는 "Seoul", "Barcelona" 같은 도시 이름이 각각 하나씩 들어있는 List를 원한다.
		 * - 이때 바로 map method 대신 flatMap method를 사용하는 것이다.
		 */
// =============================================		
		// 1-2. flatMap
		Stream<String[]> cityStream2 = Arrays.stream(cities);
		Stream<String> flattenedCityStream = cityStream2.flatMap(x -> Arrays.stream(x));
		/*
		 * - (x -> Arrays.stream(x))을 통해 아래와 같이 된 것은 map method를 이용한 것과 동일하다.
		 *  
		 * { "Seoul", "Busan" }				--> 이 한 덩어리가 Stream<String>이 되었다.
		 * { "San Francisco", "New York" } 	--> 이 한 덩어리가 Stream<String>이 되었다.
		 * { "Madrid", "Barcelona" }		--> 이 한 덩어리가 Stream<String>이 되었다.
		 * 
		 * - 하지만 flatMap method로 인해 Stream<String>의 Stream이 모두 벗겨져 그 안에 있는 String들이 하나로 쭉 이어져서 납작하게 됐다.
		 * - 그렇기에 Stream<Stream<String>> 같은 형태가 아니라 Stream<String> 형태가 되었다.
		 */
		List<String> flattenedCityList = flattenedCityStream.collect(Collectors.toList());
		System.out.println("flattenedCityList : " + flattenedCityList);
// ===================================================================================================
		Order order1 = new Order()
				.setId(1001)
				.setOrderLines(Arrays.asList(
						new OrderLine()
							.setId(10001)
							.setType(OrderLineType.PURCHASE)
							.setAmount(BigDecimal.valueOf(5000)),
						new OrderLine()
							.setId(10002)
							.setType(OrderLineType.PURCHASE)
							.setAmount(BigDecimal.valueOf(4000))
				));
		Order order2 = new Order()
				.setId(1002)
				.setOrderLines(Arrays.asList(
						new OrderLine()
							.setId(10003)
							.setType(OrderLineType.PURCHASE)
							.setAmount(BigDecimal.valueOf(2000)),
						new OrderLine()
							.setId(10004)
							.setType(OrderLineType.DISCOUNT)
							.setAmount(BigDecimal.valueOf(-1000))
				));
		Order order3 = new Order()
				.setId(1003)
				.setOrderLines(Arrays.asList(
						new OrderLine()
							.setId(10005)
							.setType(OrderLineType.PURCHASE)
							.setAmount(BigDecimal.valueOf(2000))
				));
		// order1, order2, order3 각각의 orderLine List 속 OrderLine 들을 하나로 합쳐서 하나의 List<OrderLine>로 만들어보자
		
		// 1. 내 방법
		List<Order> orders = Arrays.asList(order1, order2, order3);
		Stream<Order> orderStream = orders.stream();
		Stream<OrderLine> orderLineStream = orderStream.flatMap(order -> order.getOrderLines().stream());
		List<OrderLine> orderList = orderLineStream.collect(Collectors.toList());
		System.out.println(orderList);
		
		// 2. 강의
		List<Order> orders2 = Arrays.asList(order1, order2, order3);
		List<OrderLine> orderList2 = orders2.stream()					// Stream<Order>
									.map(Order::getOrderLines)			// Stream<List<OrderLine>>
									.flatMap(order -> order.stream())	// Stream<OrderLine>	
																		// stream() : Collection을 Stream으로 바꿔주는 method(SET, LIST 모두 사용 가능)
																		// 여기서 그냥 map을 써버리면 --> .map(List::stream) --> Stream<Stream<OrderLine>>이 된다.
																		// 따라서 Stream을 벗겨주는 flatMap을 사용해야 한다. 
									.collect(Collectors.toList());
		System.out.println(orderList2);
	}
}