package com.hotel.controller;

import java.util.List;

import com.hotel.model.Customer;
import com.hotel.model.Reservation;
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
		Customer member = hotelService.findMember(name).get(0);
		if(member != null) {
			hotelView.findMember(name, hotelService.findMember(name));
		}else {
			hotelView.errorPage();
		}
	}

	//고객 등록
	public void addMember(Customer newMember) {
		if( hotelService.addMember(newMember) > 0) {
			hotelView.addMemberSuccess();
		}else {
			hotelView.errorPage();
		}
		
	}

	//전체 객실 조회
	public void findRoom() {
		rooms = hotelService.findRoom();
		hotelView.findRoom(rooms);
		
	}
	
	//객실 예약(등록)
//	public void reservationRoom(Reservation newRsv) {
//		int result = hotelService.reservationRoom(newRsv);
//		if(result > 0) {
//			hotelView.successPage();
//			hotelView.reservationRoom(newRsv);
//		} else {
//			hotelView.errorPage();
//		}
//		
//	}
	
	// 객실 예약 리스트
	public void findReservation() {
		hotelView.findReservation(hotelService.findReservation());
	}
	
	// 빈 객실 리스트
	public void emptyRoom() {
		hotelView.emptyRoom(hotelService.emptyRoom());
	}
	//객실 예약(등록)
	public void reservationRoom(int roomNumber, long c_ID, String name, Reservation resrv) {
		hotelService.reservationRoom(roomNumber, c_ID, name, resrv);
		
	}
	
}
