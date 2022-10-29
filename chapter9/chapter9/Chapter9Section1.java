package com.fastcampus.functionalprogramming.chapter9;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Chapter9Section1 {
	public static void main(String[] args) {
		// 1. Scope & Closure
		// getStringSupplier를 호출해 return 되는 supplier를 받았다.
		Supplier<String> supplier = getStringSupplier();
		
		// return 값으로 받은 supplier를 호출
		System.out.println(supplier.get());
		
		/*
		 * [결과값] = HelloWorld
		 * - 일반적으로 생각하면 getStringSupplier method의 호출이 끝났을 때 hello 변수는 사라져야 한다.
		 * - 하지만 return 된 supplier가 계속해서 hello 변수를 사용해야하기 때문에
		 * - System.out.println(supplier.get())를 호출하는 시점에도 hello 변수는 여전히 남아있는 것이다.
		 */
// ===================================================================================================
		// 2. Curry
		BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
		int result = add.apply(3, 10);
		System.out.println("result : " + result);
		
		// x를 먼저 받고 나중에 y를 받는 식으로 함수를 쪼개고 싶다.
		Function<Integer, Function<Integer, Integer>> curriedAdd = x -> y -> x + y;
		Function<Integer, Integer> addThree = curriedAdd.apply(3);
		int result2 = addThree.apply(10);
		System.out.println("result2 : " + result2);
		/*
		 * curriedAdd.apply(3)을 통해 Function을 return 받은 addThree 변수는
		 * 어디를 가더라도 '3'이라는 숫자를 사용할 수 있다.
		 */
	}
	
	public static Supplier<String> getStringSupplier() {
		String hello = "Hello";
		Supplier<String> supplier = () -> {
			String world = "World";
			return hello + world;
		};
		
		return supplier;
	}
}