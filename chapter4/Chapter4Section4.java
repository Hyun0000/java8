package com.fastcampus.functionalprogramming.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Chapter4Section4 {
	public static void main(String[] args) {
		Predicate<Integer> isPositive = x -> x > 0; // 양수 / 음수를 알려주는 기능
		// System.out.println("isPositive : " + isPositive.test(10));
// ===================================================================================================
		List<Integer> integerInputs = Arrays.asList(10, -5, 4, -2, 0, 3);
		System.out.println("Positive : " + filter(integerInputs, isPositive));
// ===================================================================================================
		// 1. 0과 같거나 0 보다 작은 수만 골라낸다.
		// negate()를 붙여줬기에 isPositive와 정반대의 행동을 한다.
		System.out.println("Non-Positive : " + filter(integerInputs, isPositive.negate()));
// ===================================================================================================
		// 2. 0 보다 '크거나' 0과 같은 숫자만 골라낸다.
		// (x -> x == 0)라는 Predicate를 새로 만들어서 or method에 넘겨주는 것이다.
		// 아래의 filter method에는 isPositive와 or(x -> x == 0)가 합쳐진 새로운 Predicate가 argument로 전달되는 것이다.
		System.out.println("Non-negative : " + filter(integerInputs, isPositive.or(x -> x == 0)));			
// ===================================================================================================
		// 3. 양수'이면서' 짝수만 골라낸다.
		// 아래의 filter method에는 isPositive와 and(x -> x % 2 == 0)가 합쳐진 새로운 Predicate가 argument로 전달되는 것이다.
		System.out.println("Even-Positive : " + filter(integerInputs, isPositive.and(x -> x % 2 == 0)));
	}
	
	// true 인 것만 뽑아 새로운 List를 만들어 return 하자
	public static <T> List<T> filter(List<T> inputs, Predicate<T> condition) {
		List<T> output = new ArrayList<T>();
		for (T input : inputs) {
			if(condition.test(input)) {
				output.add(input);
			}
		}
		return output;
	}
}