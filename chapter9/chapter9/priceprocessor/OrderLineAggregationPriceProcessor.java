package com.fastcampus.functionalprogramming.chapter9.priceprocessor;

import java.math.BigDecimal;
import java.util.function.Function;

import com.fastcampus.functionalprogramming.chapter9.model.Order;
import com.fastcampus.functionalprogramming.chapter9.model.OrderLine;

// 전체 가격을 합한 후 해당 가격을 order에 set 해주는 Processor
public class OrderLineAggregationPriceProcessor implements Function<Order, Order> {

	@Override
	public Order apply(Order order) {
		return order.setAmount(order.getOrderLines().stream()
				.map(OrderLine::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add));
	}
}