package com.fastcampus.functionalprogramming.chapter3;

import java.util.function.Function;

// new Adder() 부분이 더 이상 필요없으므로 아래의 import 부분도 주석처리한다.
// import com.fastcampus.functionalprogramming.chapter3.util.Adder;

public class Chapter3Section2 {
	public static void main(String[] args) {
		// Function<Integer, Integer> myAdder = new Adder();
		// 위의 코드를 Lambda Expression을 이용해 바꿔보자

		// 1.
		Function<Integer, Integer> myAdder = (Integer x) -> {
			return x + 50;
		};
		
		// 2.
		// 위의 Lambda Expression을 더 단순하게 만들어보자.
		Function<Integer, Integer> myAdder2 = x -> x + 500;
		/*
		 * - 매개변수(parameter)의 타입이 유추 가능할 경우 타입 생략 가능
		 * ㄴ Function<Integer, Integer>를 이용해 input type이 Integer라는 것을 알 수 있으므로 parameter 타입 생략 가능
		 * 
		 * - 매개변수(parameter)가 하나일 경우 괄호 생략 가능
		 * ㄴ 현재 input parameter가 'x' 하나이므로 괄호 생략 가능
		 * 
		 * - 함수 body 내에서 다른 내용 없이 바로 값을 return 하는 경우 (중괄호 & return 문구) 생략 가능
		 * ㄴ body 내에서 다른 내용 없이 바로 return 하므로 (중괄호 & return 문구) 생략 가능
		 */
		
		int result = myAdder.apply(100);
		System.out.println("result : " + result);
		
		int result2 = myAdder2.apply(100);
		System.out.println("result2 : " + result2);
	}
}