package com.fastcampus.functionalprogramming.chapter7;

import java.util.Optional;

import com.fastcampus.functionalprogramming.chapter7.model.User;

public class Chapter7Section2 {
	public static void main(String[] args) {
		// 1. ifPresent
		// maybeUser안에 User가 있을 수도 있고 없을 수도 있다.
		Optional<User> maybeUser1 = Optional.ofNullable(maybeGetUser(true));
		maybeUser1.ifPresent(user -> System.out.println("maybeUser1 : " + user));

		// maybeUser2가 null이기에 ifPresent 안의 내용은 실행되지 않는다.
		Optional<User> maybeUser2 = Optional.ofNullable(maybeGetUser(false));
		maybeUser2.ifPresent(user -> System.out.println("maybeUser2 : " + user));
// ===================================================================================================
		// 2. map
		Optional<Integer> maybeId1 = Optional.ofNullable(maybeGetUser(true))
		.map(user -> user.getId());
		maybeId1.ifPresent(id -> System.out.println("maybeId1 : " + id));
		System.out.println(maybeId1);
		
		// maybeId2가 null이기에 map 안의 내용은 실행되지 않는다.
		Optional<Integer> maybeId2 = Optional.ofNullable(maybeGetUser(false))
		.map(user -> user.getId());
		maybeId2.ifPresent(id -> System.out.println("maybeId2 : " + id));
		System.out.println(maybeId2);

		// 2-1
		// Optional의 map 또한 Stream의 map과 마찬가지로 여러 method들을 연속해서 사용할 수 있다.
		String username = Optional.ofNullable(maybeGetUser(true))
		.map(User::getName)
		.map(name -> "The name is " + name)
		.orElse("The name is empty");
		System.out.println(username);
		
		// username2가 null이기에 map 안의 내용은 실행되지 않는다.
		String username2 = Optional.ofNullable(maybeGetUser(false))
				.map(User::getName)
				.map(name -> "The name is " + name)
				.orElse("The name is empty");	// username2는 비어있는 Optional이기에 orElse의 default 값이 출력된다.
		System.out.println(username2);
// ===================================================================================================
		// 3. flatMap
		// Optional 안에 또 하나의 Optional이 있는 형태
		Optional<Optional<String>> maybeEmail = Optional.ofNullable(maybeGetUser(true)).map(User::getEmailAddress);
		System.out.println(maybeEmail);
		
		// 내가 원하는 형태는 Optional<Optional<String>>이 아니라 Optional<String>
		// 이럴때 flatMap을 사용한다.
		Optional<String> maybeEmai2 = Optional.ofNullable(maybeGetUser(true)).flatMap(User::getEmailAddress);
		maybeEmai2.ifPresent(email -> System.out.println("maybeEmai2 : " + email));
		System.out.println(maybeEmai2);
		
		Optional<String> maybeEmai3 = Optional.ofNullable(maybeGetUser(false)).flatMap(User::getEmailAddress);
		maybeEmai3.ifPresent(email -> System.out.println("maybeEmai3 : " + email));
		System.out.println(maybeEmai3);
	}
	
	// 오브젝트를 return(할 수도 OR 아닐 수도) 있는 메소드
	public static User maybeGetUser(boolean returnUser) {
		if (returnUser) {
			return new User()
					  .setId(1001)
					  .setName("Alice")
					  .setEmailAddress("alice@fastcampus.co.kr")
					  .setVerified(false);
		}
		return null;
	}
}