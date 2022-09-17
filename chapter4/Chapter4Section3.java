package com.fastcampus.functionalprogramming.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class Chapter4Section3 {
	public static void main(String[] args) {
		BiConsumer<Integer, Double> biConsumer =  
		(index, input) -> System.out.println("processing " + input + " at index " + index);
		
		List<Double> doubleInputs = Arrays.asList(10.0, 20.0, 30.0);
		process(doubleInputs, biConsumer);
// ===================================================================================================
		List<String> stringInputs = Arrays.asList("apple", "kiwi", "banana");
		BiConsumer<Integer, String> biConsumer2 =  
		(index, input) -> System.out.println("index : " + index + " input : " + input);
		process2(stringInputs, biConsumer2);
		
		List<Boolean> boolInputs = Arrays.asList(true, true ,false);
		BiConsumer<Integer, Boolean> biConsumer3 =  
		(index, input) -> System.out.println("index : " + index + " input : " + input);
		process2(boolInputs, biConsumer3);
	}
	
	public static <T>void process(List<T> inputs, BiConsumer<Integer, T> processor) {
		// BiConsumer<Integer, T> --> Integer는 index 개념으로 사용할 것이기에 자료형을 명시했다.
		for (int i = 0; i < inputs.size(); i++) {
			processor.accept(i, inputs.get(i));
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T, K, U>void process2(List<T> inputs, BiConsumer<Integer, U> processor) {
		for (int i = 0; i < inputs.size(); i++) {
			processor.accept(i , (U) inputs.get(i));
		}
	}
}