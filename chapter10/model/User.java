package com.fastcampus.functionalprogramming.chapter10.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class User {
	private int id;
	private String name;
	private String emailAddress;
	private boolean isVerified;
	private LocalDateTime createdAt;
	private List<Integer> friendUserIds;
	
	// User class constructor
	// 모든 필드의 값을 Builder에서 가져와 객체를 생성하는 것이다.
	public User(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.emailAddress = builder.emailAddress;
		this.isVerified = builder.isVerified;
		this.createdAt = builder.createdAt;
		this.friendUserIds = builder.friendUserIds;
	}
	
	// Builder class의 instance를 만들 수 있게 해주는 역할
	// Builder class의 constructor는 외부에서 접근할 수 없으므로(private) 해당 method가 필요하다.
	// cf) private는 같은 class 내에서는 접근가능하므로 여기서는 당연히 Builder class constructor에 접근할 수 있다.
	public static Builder builder(int id, String name) {
		// (id, name)은 Builder constructor가 parameter로 가지고 있으므로 반드시 넣어줘야 한다. 
		return new Builder(id, name);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Optional<String> getEmailAddress() {
		return Optional.ofNullable(emailAddress);
	}
	
	public boolean isVerified() {
		return isVerified;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public List<Integer> getFriendUserIds() {
		return friendUserIds;
	}
	
	// Builder라는 inner class를 정의
	public static class Builder {
//		// Builder class도 User class와 동일한 field를 갖는다.
//		private int id;
//		private String name;
//		private String emailAddress;
//		private boolean isVerified;
//		private LocalDateTime createdAt;
//		private List<Integer> friendUserIds;
		
		// 밖에서 접근이 가능해야 하므로 접근제한자를 public으로 변경 by withAllField method
		public int id;
		public String name;
		public String emailAddress;
		public boolean isVerified;
		public LocalDateTime createdAt;
		public List<Integer> friendUserIds;
		
		// Builder class의 instance를 만들어 주는 constructor
		// 생성 시 id와 name을 필수로 받도록 parameter로 넣었다.
		// 결론적으로 User class의 instance를 만들때 id와 name은 필수로 있어야한다는 것
		private Builder(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		// 단 하나의 setter method(모든 필드의 값을 세팅할 수 있다.)
		public Builder withAllField(Consumer<Builder> consumer) {
			// consumer 안에서 Builder class의 모든 필드들을 set 할 것이다.
			consumer.accept(this);
			return this;
		}
		
		
//		// 필수가 아닌 필드를 정의하는 method 1
//		public Builder withEmailAddress(String emailAddress) {
//			this.emailAddress = emailAddress;
//			return this;
//		}
//		
//		// 필수가 아닌 필드를 정의하는 method 2
//		public Builder withVerified(boolean isVerified) {
//			this.isVerified = isVerified;
//			return this;
//		}
//		
//		// 필수가 아닌 필드를 정의하는 method 3
//		public Builder withCreatedAt(LocalDateTime createdAt) {
//			this.createdAt = createdAt;
//			return this;
//		}
//		
//		// 필수가 아닌 필드를 정의하는 method 4
//		public Builder withFriendUserIds(List<Integer> friendUserIds) {
//			this.friendUserIds = friendUserIds;
//			return this;
//		}
//		
		// Builder class와 User constructor를 연결해주는 역할
		public User build() {
			// 자기 자신(this)을 념겨 User object를 만드는 것이다.
			// User constructor의 parameter가 (Builder builder)이기에 this를 argument로 넣어줘야한다.
			return new User(this);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", " + (name != null ? "name=" + name + ", " : "")
				+ (emailAddress != null ? "emailAddress=" + emailAddress + ", " : "") + "isVerified=" + isVerified
				+ ", " + (createdAt != null ? "createdAt=" + createdAt + ", " : "")
				+ (friendUserIds != null ? "friendUserIds=" + friendUserIds : "") + "]";
	}
}