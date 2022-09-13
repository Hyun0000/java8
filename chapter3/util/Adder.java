package com.fastcampus.functionalprogramming.chapter3.util;

import java.util.function.Function;

public class Adder implements Function<Integer, Integer>{
	@Override
	public Integer apply(Integer x) { // function interface를 구현할 함수
		return x + 10; 
	}
}