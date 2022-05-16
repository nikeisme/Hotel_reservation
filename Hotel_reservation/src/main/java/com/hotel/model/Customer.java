package com.hotel.model;

import java.sql.Date;

import lombok.Builder;

@Builder
public class Customer {
	private int customer_id; // 고객 id
	private String callNumber; // 고객 연락처
	private String name; // 고객 이름
	private Date birth; // 고객 생일
	private int age; // 고객 나이
}
