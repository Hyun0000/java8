package com.fastcampus.functionalprogramming.chapter5;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Chapter5Section1 {
	public int subtract(int x, int y) {
		return x - y;
	}
	
	public static int multiply(int x, int y) {
		return x * y;
	}
	
	public static int calculate(int x, int y, BiFunction<Integer, Integer, Integer> operator) {
		return operator.apply(x, y);
		// BiFunction<Integer, Integer, Integer>로 지정했기에 return 타입이 Integer 이다.
		// 따라서 calculate 함수의 return에 operator.apply(x, y)를 적을 수 있다.
	}
	
	public void myMethod() {
		// myMethod 안에서 calculate method를 호출할 것이고 이때 인자(argument)로 subtract method를 지정하고 싶다.
		// class 안에서 class의 필드를 호출할 때 this를 이용하는 것과 동일
		System.out.println(calculate(10, 30, this::subtract));
	}
	
	public static void main(String[] args) {
		// [ClassName::staticMethodName - 클래스의 static method(정적메서드)를 지정할 때]
		/*
		 * - parseInt method는 String input 1개를 받아 Integer로 변환해 반환하는 method이다.
		 * - 따라서 이러한 특징과 맞는 Function interface를 이용하자
		 * - 뜬금없이 (Predicate<String> str2int = Integer::parseInt;) --> 이렇게 하면 안 된다는 것이다.
		 */
		Function<String, Integer> str2int = Integer::parseInt; // str2int 안에 parseInt method가 담겨있는 것이다.
		System.out.println(str2int.apply("100"));
// ===================================================================================================
		// [objectName::instanceMethodName - 이미 선언되어 있는 객체의 instance method를 지정할 때]
		/*
		 * - String이 같은지 다른지 비교할 것이기에 이에 적절한 Predicate interface를 이용하자
		 */
		String str = "hello";
		Predicate<String> equalsToHello = str::equals;
		System.out.println(equalsToHello.test("hello"));
		System.out.println(equalsToHello.test("bye"));
// ===================================================================================================
		// 1.
		// BiFunction<Integer, Integer, Integer> intSum = (x, y) -> x + y;
		// 위와 같은 식으로 BiFunction을 따로 만들지 않고 아예 argument 자리에 생성과 동시에 보내버린다.
		System.out.println(calculate(8, 2, (x, y) -> x + y));
		
		// 2.
		// 이미 만들어져 있는 static method를 지정할 때
		System.out.println(calculate(8, 2, Chapter5Section1::multiply));
		// [(x, y) -> x * y;] ==> 이게 넘어간거라고 생각하면 된다.
		// multiply method가 int 2개를 받고 int를 return 하는 형태이기에 BiFunction 형태로 넘길 수 있는 것이다.
		
		// 3.
		// 이미 만들어져 있는 instance method를 지정할 때
		Chapter5Section1 instance = new Chapter5Section1();
		System.out.println(calculate(8, 2, instance::subtract));
// ===================================================================================================
		instance.myMethod();
	}
}