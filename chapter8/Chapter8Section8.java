package com.fastcampus.functionalprogramming.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.fastcampus.functionalprogramming.chapter8.model.User;
import com.fastcampus.functionalprogramming.chapter8.service.EmailService;

public class Chapter8Section8 {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
		
		// 짝수, 홀수 그룹으로 나눠보자(짝수 = true, 홀수 = false)
		Map<Boolean, List<Integer>> numberPartitions = numbers.stream().collect(Collectors.partitioningBy(number -> number % 2 == 0));
		System.out.println(numberPartitions);
		System.out.println("짝수 : " + numberPartitions.get(true));
		System.out.println("홀수 : " + numberPartitions.get(false));
// ===================================================================================================
		User user1 = new User()
				.setId(101)
				.setName("Alice")
				.setEmailAddress("alice@fastcampus.co.kr")
				.setFriendUserIds(Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214));
	    User user2 = new User()
	    		.setId(102)
	    		.setName("Bob")
	    		.setEmailAddress("bob@fastcampus.co.kr")
	    		.setFriendUserIds(Arrays.asList(204, 205, 206));
	    User user3 = new User()
	    		.setId(103)
	    		.setName("Charlie")
	    		.setEmailAddress("charlie@fastcampus.co.kr")
	    		.setFriendUserIds(Arrays.asList(204, 205, 207, 218));
	    
	    List<User> users = Arrays.asList(user1, user2, user3);
	    
	    /*
	     * TODO
	     * - user의 친구 숫자가 5명 초과 = "친구랑 놀아라"
	     * - user의 친구 숫자가 5명 이하 = "친구 좀 만들어"
	     * - 친구 숫자 = private List<Integer> friendUserIds
	     */
	    Map<Boolean, List<User>> userPartitions = users.stream().collect(Collectors.partitioningBy(user -> user.getFriendUserIds().size() > 5));
	    
	    EmailService emailService = new EmailService();
	    for (User user : userPartitions.get(true)) {
	    	emailService.sendPlayWithFriendsEmail(user);
		}
	    
	    for (User user : userPartitions.get(false)) {
	    	emailService.sendMakeMoreFriendsEmail(user);
	    }
// ===================================================================================================
	    // 이전에 배운거 응용해 봤다.
	    Consumer<User> play = user -> {
	    	emailService.sendPlayWithFriendsEmail(user);
	    };
	    
	    Consumer<User> make = user -> {
	    	emailService.sendMakeMoreFriendsEmail(user);
	    };
	    
	    sendMail(userPartitions.get(true), play);
	    sendMail(userPartitions.get(false), make);
	}
	
	public static void sendMail(List<User> user, Consumer<User> processer) {
		for (User eachUser: user) {
			processer.accept(eachUser);
		}
	}
}