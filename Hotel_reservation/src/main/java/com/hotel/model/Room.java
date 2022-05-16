package com.hotel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Room {
	private int roomNumber; //객실 호수
	private String roomType; //객실 등급
	private boolean isCleand; //청소 상태
	
	@Override
	public String toString() {
		return "Room [객실 호수=" + roomNumber + ", 객실 등급=" + roomType + ", 청소 상태=" + isCleand + "]";
	}
	
	
}
