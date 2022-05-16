package com.hotel.model;

import java.sql.Date;

import lombok.Builder;

@Builder
public class Reservation {
	private String reservation_No; //예약번호
	private int customer_id; //회원ID
	private int roomNumber; //객실 호수
	private Date checkIn; //입실일
	private Date checkOut; //퇴실일
	private boolean isStaying; //숙박여부
	private int numOfPeople; //예약인원
}
