package com.fastcampus.functionalprogramming.chapter10;

import com.fastcampus.functionalprogramming.chapter10.model.User;
import com.fastcampus.functionalprogramming.chapter10.model.User.Builder;

public class Chapter10Section1 {
	public static void main(String[] args) {
		// 첫 줄의 builder method는 static이므로 instance 생성 없이도 호출 가능
//		User user = User.builder(1, "Alice")
//					.withEmailAddress("Alice@garden.co.kr")
//					.withVerified(true)
//					.build(); // 최종적으로 Builder class의 build method를 호출해 User instance를 반환한다.
//		System.out.println(user);
// ===================================================================================================	
		User user2 = User.builder(2, "Bob")
					.withAllField(builder -> {
						builder.emailAddress = "Bob@garden.co.kr";
						builder.isVerified = true;
					})
					.build();
		System.out.println(user2);
	}
}