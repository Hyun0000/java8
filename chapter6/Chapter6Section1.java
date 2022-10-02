package com.fastcampus.functionalprogramming.chapter6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter6Section1 {
	public static void main(String[] args) {
		// 1. String type의 Stream을 만들어보자(String을 계속 흘려보내주는 Stream)
		Stream<String> nameStream = Stream.of("Alice", "Bob", "Charlie");
		/*
		 * nameStream에서 ("Alice", "Bob", "Charlie") 같은 이름들이 하나씩 졸졸 흘러나오는 것이다.
		 * ------------------------------------------------------------------------------------- 
		 * of method : Stream interface의 static method
		 * - Stream을 생성하는 가장 기본적인 method
		 * - of method에 String type의 object들을 원하는 만큼 넣어줄 수 있다.
		 */
		List<String> names = nameStream.collect(Collectors.toList());
		/*
		 * - Stream을 바로 출력하는 방법은 없다. 따라서 Stream을 List 형태로 바꾼 후 출력해야한다.
		 * - 'collect(Collectors.toList())'로 인해 Stream 안에 있는 것들이 List에 담겨서 return 된다.
		 * - nameStream Stream 안에 있는 것들이 하나씩 흘러나와서 names List에 담기는 것이다.
		 * cf) collect, Collectors 같은건 나중에 배울 것이다.
		 */
		System.out.println(nameStream);
		System.out.println(names);
// ===================================================================================================	
		// 2. Array를 이용해 Stream 만들기
		String[] cityArray = new String[] {"Seoul", "Tokyo", "LA"};
		
		// cityArray라는 String type의 array를 stream으로 바꿔보자
		// stream() method --> array를 받아서 Stream으로 만들어주는 method
		Stream<String> cityStream = Arrays.stream(cityArray); // cityArray Array가 Stream 형태로 바뀐다.
		
		// Stream을 List 형태로 바꾸자
		// cityStream Stream 안에 있는 것들이 하나씩 흘러나와서 cityList List에 담기는 것이다.
		List<String> cityList = cityStream.collect(Collectors.toList());
		
		System.out.println(cityArray);
		System.out.println(cityStream);
		System.out.println(cityList);
// ===================================================================================================	
		// 3. Set을 이용해 Stream 만들기
		
		// HashSet은 생성시 argument로 Collection을 넣어줄 수 있다.(List를 만들어 넣어주었다.)
		Set<Integer> numberSet = new HashSet<Integer>(Arrays.asList(3, 5, 7));
		
		// stream : java Collection에서 Collection을 Stream을 바꿔주는 method
		// numberSet.stream()으로 인해 numberSet이 (SET 형태 --> stream 형태)로 바뀌었다.
		Stream<Integer> numberStream = numberSet.stream();

		// Stream을 List 형태로 바꾸자
		// numberStream Stream 안에 있는 것들이 하나씩 흘러나와서 numberList List에 담기는 것이다.
		List<Integer> numberList = numberStream.collect(Collectors.toList());
		
		System.out.println(numberSet);
		System.out.println(numberStream);
		System.out.println(numberList);
	}
}