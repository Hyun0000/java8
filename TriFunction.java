package com.fastcampus.functionalprogramming.chapter3.util;

// input이 3개인 interface를 만들어보자 
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
	// @FunctionalInterface가 붙었기에 단 하나의 abstract method를 가져야한다.
	R apply(T t, U u, V v);
	// method 이름이 반드시 apply 일 필요는 없다.
	// Function interface, BiFunction interface 와의 통일성을 위해 apply라고 지은 것일 뿐이다.
}