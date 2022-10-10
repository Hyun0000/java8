package com.fastcampus.functionalprogramming.chapter8;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fastcampus.functionalprogramming.chapter8.model.User;
import com.fastcampus.functionalprogramming.chapter8.service.EmailService;

public class Chapter8Section9 {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(3, 5, 2, 1);
		
		numbers.stream().forEach(number -> System.out.println("Number is " + number));
		
		// Stream 중간 처리를 하지 않을 경우에는 굳이 Stream으로 만들 필요 없이 Set, List에서 바로 forEach를 호출하면 된다.
		List<Integer> numbers2 = Arrays.asList(3, 5, 2, 1);
		numbers2.forEach(number -> System.out.println("No Stream Number is " + number));
// ===================================================================================================
		User user1 = new User()
				.setId(101)
				.setName("Alice")
				.setVerified(true)
				.setEmailAddress("alice@fastcampus.co.kr");
	    User user2 = new User()
	    		.setId(102)
	    		.setName("Bob")
	    		.setVerified(false)
	    		.setEmailAddress("bob@fastcampus.co.kr");
	    User user3 = new User()
	    		.setId(103)
	    		.setName("Charlie")
	    		.setVerified(false)
	    		.setEmailAddress("charlie@fastcampus.co.kr");
	    
	    EmailService emailService = new EmailService();
		
	    // 1. (isVerified = false)인 user에게만 메일을 보내자
	    List<User> users = Arrays.asList(user1, user2, user3);
	    Map<Boolean, List<User>> falseUserMap = users.stream().collect(Collectors.partitioningBy(user -> user.isVerified() == true));
	    falseUserMap.get(false).forEach(user -> emailService.sendVerifyYourEmailEmail(user));
	    
	    // 2. (isVerified = false)인 user에게만 메일을 보내자
	    // 중간 처리인 filter method를 사용했기 때문에 stream() method를 반드시 사용해야한다.
	    List<User> users2 = Arrays.asList(user1, user2, user3);
	    users2.stream()
	    .filter(user -> !user.isVerified())
	    .forEach(emailService::sendVerifyYourEmailEmail); // Method Reference를 이용해 더 간단하게
// ===================================================================================================
	    // index가 필요한 for문의 경우
	    List<User> users3 = Arrays.asList(user1, user2, user3);

	    // 기존 for문
	    for (int i = 0; i < users3.size(); i++) {
	    	User user = users3.get(i);
	    	System.out.println("이름 : " + user.getName() + " / 인덱스 번호 : " + i);
		};
	    
	    /*
	     * - IntStream : 입력한 범위의 숫자를 Stream을 통해서 하나씩 제공해준다.
	     * (0, users3.size()) = (왼쪽에 입력한 범위 포함 O, 오른쪽에 입력한 범위 포함 X)
	     */
	    IntStream.range(0, users3.size()).forEach(i -> {
	    	User user = users3.get(i);
	    	System.out.println("이름 : " + user.getName() + " / 인덱스 번호 : " + i);
	    });
	}
}