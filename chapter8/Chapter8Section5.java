package com.fastcampus.functionalprogramming.chapter8;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter8Section5 {
	public static void main(String[] args) {
		// 1. List : 순서 O, 중복 허용 O
		List<Integer> numberList = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.toList());
		System.out.println("1 : " + numberList);	// [3, 5, -3, 3, 4, 5]
// ===================================================================================================
		// 2. Set : 순서 X, 중복 허용 X
		Set<Integer> numberSet = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.toSet());
		System.out.println("2 : " + numberSet);		// [-3, 3, 4, 5]
// ===================================================================================================
		// 3. mapping to List
		// TODO : 절댓값을 적용시켜보자
		List<Integer> numberList2 = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.mapping(x -> Math.abs(x), Collectors.toList()));
		System.out.println("3 : " + numberList2);	// [3, 5, 3, 3, 4, 5]
		/*
		 * mapping(x -> Math.abs(x), Collectors.toList())의 순서
		 * 
		 * 1. x -> Math.abs(x) 	  : Stream 안의 Integer 들을 절댓값으로 바꾼다.
		 * 2. Collectors.toList() : 절댓값으로 바뀐 데이터들을 List에 담는다.
		 * 
		 * - map이 먼저 적용된 후 그 결과값이 List에 담기는 것이다.
		 * - 즉, map method와 Collectors.toList()를 따로따로 호출해서 적용시키는 것과 동일한 결과가 나오는 것이다.
		 */ 
// ===================================================================================================
		// 4. mapping to Set
		// TODO : 절댓값을 적용시켜보자
		Set<Integer> numberSet2 = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.mapping(x -> Math.abs(x), Collectors.toSet()));
		System.out.println("4 : " + numberSet2);	// [3, 4, 5]
// ===================================================================================================
		// 5. reducing
		// TODO : 전체 합을 구하자
		Integer sum = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.reducing(0, (x, y) -> x + y)); // 초기값은 '0'으로 제공
		System.out.println("5 : " + sum);			// 17
	}
}