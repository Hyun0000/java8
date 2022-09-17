package com.fastcampus.functionalprogramming.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Chapter4Section2 {
	public static void main(String[] args) {
		Consumer<String> stringDefiler = (String str) -> {
			System.out.println("result1 : " + str);
		};
		stringDefiler.accept("hello");
		
		// 간단히
		// void 여부(return 여부)와 상관 없이 한 줄 밖에 없으면 중괄호 생략 가능
		Consumer<String> stringDefiler2 = str -> System.out.println("result2 : " + str);
		stringDefiler2.accept("world");
// ===================================================================================================
		// cf) Arrays.asList()로 만들어지 List는 immutable하다.(= 변형할 수 없다.)
		// ex) integerInputs.add()를 이용해 List에 새로운 요소를 추가할 수 없다.
		// integerInputs.add(5); --> 오류 발생
		List<Integer> integerInputs = Arrays.asList(4, 2, 3);
		Consumer<Integer> integerDefiler1 = x -> System.out.println("processing integer1 : " + x);
		Consumer<Integer> integerDefiler2 = x -> System.out.println("processing integer2 : " + (x*2));
		
		// 호출
		process(integerInputs, integerDefiler1);
		process(integerInputs, integerDefiler2);
// ===================================================================================================
		List<Integer> integerInputs2 = Arrays.asList(10, 20, 30);
		Consumer<Integer> integerDefiler3 = x -> System.out.println("processing integer : " + (x+5));
		process2(integerInputs2, integerDefiler3);
		
		List<Character> charInputs = Arrays.asList('a', 'b', 'c');
		Consumer<Character> stringDefiler3 = x -> System.out.println("processing character : " + String.valueOf(x));
		process2(charInputs, stringDefiler3);
	}
	
	public static void process(List<Integer> inputs, Consumer<Integer> processer) {
		for (Integer input : inputs) {
			processer.accept(input);
		}
	}
	
	// Generic을 이용해 다양한 형태를 받아보자, 훨씬 더 유연한 프로그래밍이 가능해진다.
	public static <T>void process2(List<T> inputs, Consumer<T> processer) {
		for (T input : inputs) {
			processer.accept(input);
		}
	}
}