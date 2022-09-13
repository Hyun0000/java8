package com.fastcampus.functionalprogramming.chapter3;

import java.util.function.Function;

import com.fastcampus.functionalprogramming.chapter3.util.Adder;

public class Chapter3Section1 {
	public static void main(String[] args) {
		// Function interface를 이용해
		// Integer를 받아 (+10)을 해서 return을 해주는 '함수를 object의 형태로 구현'했다.
		Function<Integer, Integer> myAdder = new Adder();
		
		int result = myAdder.apply(5);
		System.out.println("result : " + result);
	}
}