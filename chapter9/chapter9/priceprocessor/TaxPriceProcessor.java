package com.fastcampus.functionalprogramming.chapter9.priceprocessor;

import java.math.BigDecimal;
import java.util.function.Function;

import com.fastcampus.functionalprogramming.chapter9.model.Order;

// 현재 가격에 세율을 적용하는 Processor
public class TaxPriceProcessor implements Function<Order, Order>{
	private final BigDecimal taxRate;
	
	public TaxPriceProcessor(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	@Override
	public Order apply(Order order) {
		return order.setAmount(order.getAmount()
				.multiply(taxRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)));
		// cf)
		// 세금 계산식은 굳이 이해를 하지 말자, 어차피 이게 메인도 아니다.
		// 무엇보다 사실 봐도 잘 모르겠다.
	}
}