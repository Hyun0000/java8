package com.fastcampus.functionalprogramming.chapter3;

import com.fastcampus.functionalprogramming.chapter3.util.TriFunction;

public class Chapter3Section4 {
	public static void main(String[] args) {
		// TriFunction interface를 Lambda를 이용해 구현해보자
		TriFunction<Integer, Integer, Integer, Integer> addThree = (Integer x, Integer y, Integer z) -> {
			return x + y + z;
		};
		int result = addThree.apply(10, 10, 10);
		System.out.println("result : " + result);
		
		// 조금 더 간단하게
		TriFunction<Integer, Integer, Integer, Integer> addThree2 = (x, y, z) -> x + y + z + 77;
		int result2 = addThree2.apply(10, 10, 10);
		System.out.println("result2 : " + result2);
	}
}