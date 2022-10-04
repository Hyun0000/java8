package com.fastcampus.functionalprogramming.chapter7;

import java.util.Optional;

import com.fastcampus.functionalprogramming.chapter7.model.User;

public class Chapter7Section1 {
	public static void main(String[] args) {
		// 일부러 NullPointerException을 발생시켜보자
		User user1 = new User()
				.setId(1001)
				.setName("Alice")
				.setVerified(false);
		// user1은 setEmailAddress를 하지 않았다.
		  
		User user2 = new User()
				.setId(1001)
				.setName("Alice")
				.setEmailAddress("alice@fastcampus.co.kr")
				.setVerified(false);
		
		// 정상
		System.out.println(userEquals(user2, user1));
		
		// NullPointerException 발생
		System.out.println(userEquals(user1, user2));
// ===================================================================================================
		// ------------------ Optional 만들기 ------------------
		String someEmail = "some@email.com";
		String nullEmail = null;
		
		// 1.
		Optional<String> maybeEmail = Optional.of(someEmail);
		// 2.
		Optional<String> maybeEmail2 = Optional.empty(); 			   // 빈 상자
		// 3.
		Optional<String> maybeEmail3 = Optional.ofNullable(someEmail); // null인지 아닌지 모르겠다.
		// 4.
		Optional<String> maybeEmail4 = Optional.ofNullable(nullEmail); // null인지 아닌지 모르겠다.
		
		// ------------------ Optional 안에 있는 값 꺼내기 ------------------
		// 1.
		String email1 = maybeEmail.get();
		System.out.println("email1 : " + email1);
		
		// 2.
		if(maybeEmail2.isPresent()) {							 // Optional 안에 값이 있는지 없는지 먼저 체크
			System.out.println("email2 : " + maybeEmail2.get()); // maybeEmail2 Optional이 비어있기 때문에 해당 부분은 실행될 수 없다.
		}
		
		// 3.
		String defaultEmail = "default@email.com";
		String email3 = maybeEmail2.orElse(defaultEmail);
		String email3_1 = maybeEmail.orElse(defaultEmail);
		System.out.println("email3 : " + email3);
		System.out.println("email3_1 : " + email3_1);
		
		// 4.
		String email4 = maybeEmail2.orElseGet(() -> defaultEmail);
		String email4_1 = maybeEmail.orElseGet(() -> defaultEmail);
		System.out.println("email4 : " + email4);
		System.out.println("email4_1 : " + email4_1);
		
		// 5.
		String email5 = maybeEmail.orElseThrow(() -> new RuntimeException("email not present"));
		System.out.println("email5 : " + email5);
		
		String email5_1 = maybeEmail2.orElseThrow(() -> new RuntimeException("email not present"));
		System.out.println("email5_1 : " + email5_1);
	}
	
	  public static boolean userEquals(User u1, User u2) {
		  return u1.getId() == u2.getId()
				  && u1.getName().equals(u2.getName())
				  && u1.getEmailAddress().equals(u2.getEmailAddress())
				  && u1.isVerified() == u2.isVerified();
	  }
}