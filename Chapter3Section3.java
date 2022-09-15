package com.fastcampus.functionalprogramming.chapter3;

import java.util.function.BiFunction;

public class Chapter3Section3 {
	public static void main(String[] args) {
		BiFunction<Integer, Integer, Integer> add = (Integer x, Integer y) -> {
			return x + y;
		};
		int result = add.apply(10, 10);
		System.out.println("result : " + result);
		
		// 조금 더 간단하게
		BiFunction<Integer, Integer, Integer> add2 = (x, y) -> x + y + 10;
		int result2 = add2.apply(10, 10);
		System.out.println("result2 : " + result2);
	}
}