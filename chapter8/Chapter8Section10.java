package com.fastcampus.functionalprogramming.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fastcampus.functionalprogramming.chapter8.model.User;
import com.fastcampus.functionalprogramming.chapter8.service.EmailService;

public class Chapter8Section10 {
	public static void main(String[] args) {
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
	    User user4 = new User()
	            .setId(104)
	            .setName("David")
	            .setEmailAddress("david@fastcampus.co.kr")
	            .setVerified(true);
        User user5 = new User()
        		.setId(105)
        		.setName("Eve")
        		.setEmailAddress("eve@fastcampus.co.kr")
        		.setVerified(false);
        User user6 = new User()
        		.setId(106)
        		.setName("Frank")
        		.setEmailAddress("frank@fastcampus.co.kr")
        		.setVerified(false);
        
	    // TODO  : 검증되지 않은 유저를 골라내어 이메일을 보내자
        /*
         * - 순서가 상관이 없는 작업을 한다면 parallelStream을 사용할 수 있다.
         * - 이메일 발송 작업은 순서가 중요한 작업이 아니므로 바로 이럴때 병렬처리를 사용하면 좋다.
         */
        
        // 순차처리
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);
        
        long startTime = System.currentTimeMillis();
        EmailService emailService = new EmailService();
	    users.stream()
    		.filter(user -> !user.isVerified())
    		.forEach(emailService::sendVerifyYourEmailEmail);	    
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential(순차처리) : " + (endTime - startTime) + "ms");
        // 순차처리는 처리 결과도 users List에 담긴 순서대로 처리된다.
	    
	    
	    // 병렬처리
        List<User> users2 = Arrays.asList(user1, user2, user3, user4, user5, user6);
        long startTime2 = System.currentTimeMillis();
        EmailService emailService2 = new EmailService();
        users2.stream().parallel()
			.filter(user -> !user.isVerified())
			.forEach(emailService2::sendVerifyYourEmailEmail);	
        long endTime2 = System.currentTimeMillis();
        System.out.println("Parallel(병렬처리) : " + (endTime2 - startTime2) + "ms");
        // 병렬처리는 users List에 담긴 순서대로 처리되지 않았다.
        // 메일을 보내는 것은 순서가 상관없으니 병렬처리를 사용해도 문제없다.
// ===================================================================================================
        // 순서가 상관이 있는 작업을 한다면 parallelStream을 사용할 수 없다.
        // TODO user의 이름을 대문자로 바꾼 후 (isVerified = true)로 설정하고 싶다.
        /*
         * 순서를 지켜야 하는 작업이다.
         * 1. user의 이름을 대문자로 바꾼 후
         * 2. (isVerified = true)로 설정하고 싶다.
         */
        List<User> users3 = Arrays.asList(user1, user2, user3, user4, user5, user6);
        
        // 아래의 작업은 users3 List에 담긴 순서와 상관 없이 실행될 때마다 순서가 뒤죽박죽으로 실행될 것이다.
        List<User> processedUsers = users3.parallelStream()
        		.map(user -> {
        			System.out.println("Capitalize user name for user " + user.getId());
        			user.setName(user.getName().toUpperCase());
        			return user;
        		})
        		.map(user -> {
        			System.out.println("Set 'isVerified' to true for user " + user.getId());
        			user.setVerified(true);
        			return user;
        		})
        		.collect(Collectors.toList());
				
       System.out.println("==================== 최종 결과물은 순서대로 출력 O ====================");
		
       // 최종 결과물을 출력하는 아래의 코드는 users3 List에 담긴 순서대로 출력될 것이다.
       for (User user : processedUsers) {
    	   System.out.println("Name : " + user.getName() + " / isVerified : " + user.isVerified());
       }
	}
}