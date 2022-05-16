package com.hotel.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.hotel.dao.HotelDAO;
import com.hotel.model.Customer;
import com.hotel.model.Reservation;
import com.hotel.model.Room;

public class HotelService {

	private final HotelDAO hotelDAO;
	
	public HotelService() {
		hotelDAO = new HotelDAO();
	}
	
	public List<Customer> membersList() {
		
		return hotelDAO.membersList();
	}

	public List<Customer> findMember(String name) {
		return hotelDAO.findMemeber(name);
		
	}

	public int addMember(Customer newMember) {
		return hotelDAO.addMember(newMember);
	}

	public List<Room> findRoom() {
		return hotelDAO.findRoom();
	}
	
	public int reservationRoom(int roomNumber, String callNumber, String name, Reservation resrv ) {
		Scanner input = new Scanner(System.in);
		Customer newMember;
		if(findMember(name) != null) {
			return hotelDAO.reservationRoom(roomNumber, callNumber, name, resrv);
		}else { //예약 등록 중 고객 이름이 없다면 새 회원 등록
			System.out.println("고객 이름 입력 :  ");
			String newName = input.next();
			System.out.println("고객의 생년월일 입력 :  ");
			String birth = input.next();
			int age = 2022 - Date.valueOf(birth).toLocalDate().getYear()+1;
			newMember = Customer.builder().name(newName).birth(Date.valueOf(birth)).age(age).build();
			addMember(newMember);
		}
		return 0;
		
	}

	public List<Reservation> findReservation() {
		return hotelDAO.findReservation();
	}

	public List<Room> emptyRoom() {
		return hotelDAO.emptyRoom();
	}

}
