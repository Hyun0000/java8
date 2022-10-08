package com.fastcampus.functionalprogramming.chapter8;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.fastcampus.functionalprogramming.chapter8.model.Order;
import com.fastcampus.functionalprogramming.chapter8.model.Order.OrderStatus;
import com.fastcampus.functionalprogramming.chapter8.model.User;

public class Chapter8Section1 {
	public static void main(String[] args) {
		// 1. max
		Optional<Integer> max = Stream.of(5 ,3, 6, 2, 1).max((x, y) -> x - y);
		Optional<Integer> max2 = Stream.of(5 ,3, 6, 2, 1).max(Integer::compareTo);
		System.out.println(max.get());
		System.out.println(max2.get());
// ===================================================================================================
		// 2. min
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
	    		.setVerified(true)
	    		.setEmailAddress("david@fastcampus.co.kr");
		
		List<User> users = Arrays.asList(user1, user2, user3);
		
		// 이름순으로 정렬했을 때 맨 앞에 오는 user 이름 찾기(가나다순)
		Optional<String> minName = users.stream().map(User::getName).min((u1, u2) -> u1.compareTo(u2));
		System.out.println(minName.get());
		
		// 이름순으로 정렬했을 때 맨 앞에 오는 user 찾기(가나다순)
		Optional<User> firstUser = users.stream().min((u1, u2) -> u1.getName().compareTo(u2.getName()));
		System.out.println(firstUser.get());
// ===================================================================================================
		// 3. count
		// 양수의 개수
		Long positiveIntegerCount = Stream.of(1, -4, 5, -3, 6).filter(x -> x > 0).count();
		System.out.println("양수의 개수 : " + positiveIntegerCount);
		
		// 최근 24시간 이내 가입한 user 중 아직 검증이 되지 않은 user의 수
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
	    user1.setCreatedAt(now.minusDays(2));	// 현재 기준 2일 전에 가입
	    user2.setCreatedAt(now.minusHours(10));	// 현재 기준 10시간 전에 가입
	    user3.setCreatedAt(now.minusHours(1));	// 현재 기준 1시간 전에 가입
	    user4.setCreatedAt(now.minusHours(27));	// 현재 기준 27시간 전에 가입
	    
	    List<User> users2 = Arrays.asList(user1, user2, user3, user4);
	    /*
	     * 총 3단계
	     * 1. 최근 24시간 이내 가입한 user 중
	     * 2. 아직 검증이 되지 않은
	     * 3. user의 수
	     */
	    Long unverfiedUsersIn24Hrs = users2.stream()
	    			.filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
	    			.filter(user -> !(user.isVerified()))
	    			.count();
	    System.out.println("user 수 : " + unverfiedUsersIn24Hrs);
// ===================================================================================================
	    // 숙제
    	Order order1 = new Order()
			.setId(1001L)
			.setAmount(BigDecimal.valueOf(2000))
			.setStatus(OrderStatus.CREATED);
    	Order order2 = new Order()
			.setId(1002L)
			.setAmount(BigDecimal.valueOf(4000))
			.setStatus(OrderStatus.ERROR);
    	Order order3 = new Order()
			.setId(1003L)
			.setAmount(BigDecimal.valueOf(3000))
			.setStatus(OrderStatus.ERROR);
    	Order order4 = new Order()
			.setId(1004L)
			.setAmount(BigDecimal.valueOf(7000))
			.setStatus(OrderStatus.PROCESSED);
    	
        List<Order> orders = Arrays.asList(order1, order2, order3, order4);
        
        // ERROR 상태인 order들 중에서 Amount가 가장 큰 order 찾아내기
	    /*
	     * 총 3단계
	     * 1. ERROR 상태인 order들 중에서
	     * 2. Amount가 가장 큰
	     * 3. order 찾아내기
	     */
        Optional<Order> errorMaxAmountUser = orders.stream()
        					.filter(order -> order.getStatus() == OrderStatus.ERROR)
        					.max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()));
        
        System.out.println(errorMaxAmountUser.get());

        // ERROR 상태인 order들 중에서 Amount가 가장 큰 order의 금액 출력
        BigDecimal errorMaxAmount = orders.stream()
        								.filter(order -> order.getStatus() == OrderStatus.ERROR)
        								.map(Order::getAmount)
        								.max(BigDecimal::compareTo)
        								.orElse(BigDecimal.ZERO); // default 값으로 '0' 설정
        System.out.println(errorMaxAmount);
	}
}