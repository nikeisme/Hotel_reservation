package com.hotel.model;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Reservation {
	private int reservation_No; //예약번호
	private int customer_id; //회원ID
	private int roomNumber; //객실 호수
	private Date checkIn; //입실일
	private Date checkOut; //퇴실일
	private boolean isStaying; //숙박여부
	private int numOfPeople; //예약인원
	
	
	@Override
	public String toString() {
		return "Reservation [예약번호=" + reservation_No + ", 회원ID=" + customer_id + ", 객실호수="
				+ roomNumber + ", 입실날짜=" + checkIn + ", 퇴실날짜=" + checkOut + ", 숙박여부=" + isStaying
				+ ", 예약인원=" + numOfPeople + "]";
	}
	
	
}
