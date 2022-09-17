package com.fastcampus.functionalprogramming.chapter4;

import java.util.function.Supplier;

public class Chapter4Section1 {
	public static void main(String[] args) {
		// Supplier interface는 input parameter가 없기에 '() ->'로 작성
		// 아래의 Supplier는 항상 "hello world!"라는 String을 반환해준다.
		Supplier<String> stringSupplier = () -> {
			return "hello world!"; 
		};
		// Supplier interface의 abstract method 이름은 'get' 이다.
		// System.out.println("Supplier1 : " + stringSupplier.get());
		
		// 간단히
		// 아래의 Supplier는 항상 "bye world!"라는 String을 반환해준다.
		Supplier<String> stringSupplier2 = () -> "bye world!";
		// System.out.println("Supplier2 : " + stringSupplier2.get());
		
		// 호출을 할 때마다 랜덤한 double을 주는 Supplier를 만들어 보자
		Supplier<Double> randomDoubleSupplier = () -> Math.random();
		// System.out.println("Supplier3 : " + randomDoubleSupplier.get());
		
		// count 만큼 randomDoubleSupplier를 호출
		printRandomDoubles(randomDoubleSupplier, 5);
	}
	
	// printRandomDoubles 함수의 parameter로 원하는 형식의 random number를 출력하는 함수만 argument로 적절히 넘겨주면
	// printRandomDoubles 함수의 내용을 굳이 직접 수정할 필요 없이 원하는 결과를 얻을 수 있다. 
	public static void printRandomDoubles(Supplier<Double> randomDouble, int count) {
		// count 만큼 randomDoubleSupplier를 호출해서 출력을 해주는 기능을 만들어보자
		int i = 0;
		while(i < count) {
			System.out.println("printRandom : " + randomDouble.get());
			i++;
		}
	}
}