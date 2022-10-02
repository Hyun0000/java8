package com.fastcampus.functionalprogramming.chapter5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import com.fastcampus.functionalprogramming.chapter5.model.Car;
import com.fastcampus.functionalprogramming.chapter5.model.Sedan;
import com.fastcampus.functionalprogramming.chapter5.model.Suv;
import com.fastcampus.functionalprogramming.chapter5.model.User;
import com.fastcampus.functionalprogramming.chapter5.model.Van;

public class Chapter5Section3 {
	public static void main(String[] args) {
		// 기존 방법으로 User class의 instance는 아래와 같은 방법으로 만든다.
		User user = new User(1, "Alice");
		
		/*
			public User(int id, String name) {
				super();
				this.id = id;
				this.name = name;
			}
		*/
		
		/*	
		* User class의 constructor는
		* (Integer와 String) 2개의 parameter를 받아 User라는 return type을 가지는 함수라고 볼 수 있다.
		* 즉, BiFunction을 가지고 User class의 constructor를 나타낼 수 있는 것이다.
		*/
		// 1.
		BiFunction<Integer, String, User> userCreator = (Integer id, String name) -> new User(id, name);
		// 2.
		BiFunction<Integer, String, User> userCreator2 = User::new;
		/*
		 * 1번과 같은 방식으로도 constructor를 나타낼 수 있으나
		 * Method Reference를 이용해 2번과 같은 방식으로 간단하게 나타낼 수 있다.
		 */
		User charlie = userCreator2.apply(3, "Charlie");
		System.out.println(charlie);
// ===================================================================================================
		// 목표 : 아래의 input을 바탕으로 car List를 만들어서 return 해보자
		String[][] inputs = new String[][] {
			// 종류, 이름, 브랜드 순서
			{ "sedan", "Sonata", "Hyundai" },
			{ "van", "Sienna", "Toyota" },
			{ "sedan", "Model S", "Tesla" },
			{ "suv", "Sorento", "KIA" }
		};
		
		// 각 자동차의 타입을 받아서 constructor를 꺼내주는 Map
		Map<String, BiFunction<String, String, Car>> carTypeToConstructorMap = new HashMap<>();
		carTypeToConstructorMap.put("sedan", Sedan::new);
		carTypeToConstructorMap.put("suv", Suv::new);
		carTypeToConstructorMap.put("van", Van::new);
		
		List<Car> cars = new ArrayList<>();
		for (int i = 0; i < inputs.length; i++) {
			String[] item = inputs[i];
			String carType = item[0];
			String name = item[1];
			String brand = item[2];
			
			cars.add(carTypeToConstructorMap.get(carType).apply(name, brand));
			/*
			 * 순서
			 * 1. carTypeToConstructorMap.get(carType)를 통해 carType에 맞는 constructor 꺼낸다.
			 * 2. 해당 constructor에 apply(name, brand)를 이용해 값을 넣어준다.
			 */
		}
		
		for (Car car : cars) {
			car.drive();
		}
	}
}