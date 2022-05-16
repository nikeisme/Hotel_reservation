package com.hotel.controller;

import java.util.List;

import com.hotel.model.Customer;
import com.hotel.model.Room;
import com.hotel.service.HotelService;
import com.hotel.view.HotelView;

public class HotelController {
	private List<Customer> members;
	private List<Room> rooms;
	private final HotelView hotelView;
	private final HotelService hotelService;
	
	//기본 생성자
	public HotelController() {
		this.hotelView = new HotelView();
		this.hotelService = new HotelService();
	}
	
	//전체 회원 리스트 출력
	public void membersList() {
		members = hotelService.membersList();
		hotelView.membersList(members);
		
	}

	//특정 고객 조회
	public void findMember(String name) {
		hotelView.findMember(hotelService.findMember(name));
	}

	//고객 등록
	public void addMember(Customer newMember) {
		if( hotelService.addMember(newMember) > 0) {
			hotelView.addMemberSuccess();
		}
		
	}
	
	
	
	public void findRoom() {
		System.out.println("Controller 실행");
		rooms = hotelService.findRoom();
		hotelView.findRoom(rooms);
		
	}
	
}
