package com.fastcampus.functionalprogramming.chapter10;

import com.fastcampus.functionalprogramming.chapter10.model.Price;
import com.fastcampus.functionalprogramming.chapter10.service.BasicPriceProcessor;
import com.fastcampus.functionalprogramming.chapter10.service.DiscountPriceProcessor;
import com.fastcampus.functionalprogramming.chapter10.service.PriceProcessor;
import com.fastcampus.functionalprogramming.chapter10.service.TaxPriceProcessor;

public class Chapter10Section2 {
	public static void main(String[] args) {
		Price unprocessedPrice = new Price("Original Price");
		
		PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
		PriceProcessor disPriceProcessor = new DiscountPriceProcessor();
		PriceProcessor taxPriceProcessor = new TaxPriceProcessor();
		
		// PriceProcessor 들을 조합해보자
		
		// 1.
		// 기본 기능만 있는 basicPriceProcessor에 disPriceProcessor을 추가한 것이다.
		PriceProcessor decoratedPriceProcessor = basicPriceProcessor.andThen(disPriceProcessor);
		
		Price processedPrice = decoratedPriceProcessor.process(unprocessedPrice);
		System.out.println(processedPrice.getPrice());
// ===================================================================================================
		// 2.
		// 1번 기능에 taxPriceProcessor를 또 추가해보자
		PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor
												.andThen(disPriceProcessor)
												.andThen(taxPriceProcessor);
		Price processedPrice2 = decoratedPriceProcessor2.process(unprocessedPrice);
		System.out.println(processedPrice2.getPrice());
// ===================================================================================================
		// 3. class 파일을 또 만들지 말고 Lambda를 이용해 PriceProcessor를 만들자
		PriceProcessor decoratedPriceProcessor3 = basicPriceProcessor
												.andThen(taxPriceProcessor)
												.andThen(price -> new Price(price.getPrice() + ", then apply another procedure"));
		Price processedPrice3 = decoratedPriceProcessor3.process(unprocessedPrice);
		System.out.println(processedPrice3.getPrice());
	}
}