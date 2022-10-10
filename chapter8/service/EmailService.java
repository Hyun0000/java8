package com.fastcampus.functionalprogramming.chapter8.service;

import com.fastcampus.functionalprogramming.chapter8.model.User;

public class EmailService {
	// 친구랑 놀아라
	public void sendPlayWithFriendsEmail(User user) {
		user.getEmailAddress().ifPresent(email -> 
			System.out.println("Sending 'Play With Friends' email to " + email));
	}
	// getEmailAddress method의 반환타입 = Optional<String>
	
	// 친구 좀 만들어
	public void sendMakeMoreFriendsEmail(User user) {
		user.getEmailAddress().ifPresent(email -> 
			System.out.println("Sending 'Make More Friends' email to " + email));
	}
	// getEmailAddress method의 반환타입 = Optional<String>
	
	// 이메일 확인 메세지 전송
	public void sendVerifyYourEmailEmail(User user) {
		user.getEmailAddress().ifPresent(email -> 
			System.out.println("Sending 'Verify Your Email' email to " + email));
	}
	// getEmailAddress method의 반환타입 = Optional<String>
}