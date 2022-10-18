package com.fastcampus.functionalprogramming.chapter9;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter9Section2 {
	public static void main(String[] args) {
		if(true || returnFlase()) { // returnFlase method는 실행조차 되지 않는다.
			System.out.println("true");
		}
		
		if(returnTrue() || returnFlase()) { // returnFlase method는 실행조차 되지 않는다.
			System.out.println("true");
		}
// ===================================================================================================
		if(or(returnTrue(), returnFlase())) {
			System.out.println("true_all");
		}
// ===================================================================================================
		// Supplier를 거쳐서 값을 받아오도록 했다.
		if(lazyOr(() -> returnTrue(), () -> returnFlase())) {
			System.out.println("true_all_two");
		}
// ===================================================================================================
		Stream<Integer> integerStream = Stream.of(3, -2, 5, 8, -3, 10)
			.filter(x -> x > 0)
			.peek(x -> System.out.println("peeking " + x))
			.filter(x -> x % 2 == 0);
		System.out.println("Before collect");
		
		List<Integer> integers = integerStream.collect(Collectors.toList());
		System.out.println("After collect: " + integers);
		/*
		 * peek
		 * - Consumer를 받아서 단순하게 Consumer를 실행만 하고 그대로 넘어가는 Stream method
		 * - 잠깐 힐긋 보는 느낌의 method이다.
		 * - 어차피 peek method 배우는 글이 아니니까 지금은 이 정도로만 알자
		 */
		
		/*
		 * - Stream은 종결 처리가 이루어지기 전까지 모든 계산을 미루기 때문에 "Before collect"가 먼저 출력됐다.
		 * 
		 * 자세히
		 * - Stream이 계산을 미루고 미루다가 종결 처리를 만나고 나서야 '이 값들을 계산할 필요가 있구나' 하고 그제서야
		 * 한꺼번에 계산을 하기 시작한다.
		 * - 위 코드에서 종결처리 = 'collect(Collectors.toList())'
		 */
	}
	
	public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
		return x.get() || y.get();
	}
	
	public static boolean or(boolean x, boolean y) {
		return x || y;
	}
	
	public static boolean returnTrue() {
		System.out.println("Returning true");
		return true;
	}
	
	public static boolean returnFlase() {
		System.out.println("Returning false");
		return false;
	}
}