package com.hotel.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Customer {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private int customer_id; // 회원_ID
	private String callNumber; // 고객 연락처
	private String name; // 고객 이름
	private Date birth; // 고객 생일
	private int age; // 고객 나이

	
	
	
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", callNumber=" + callNumber + ", name=" + name + ", birth="
				+ birth + ", age=" + age + "]";
	}






	
	
	
}
