package com.hotel.view;

import java.util.List;

import com.hotel.model.Customer;
import com.hotel.model.Reservation;
import com.hotel.model.Room;

public class HotelView {
	
	//호텔 Main 화면 
	public static void mainList() {
		System.out.println();
		System.out.println("-+-+-+-+-호텔 예약 시스템-+-+-+-+-");
		System.out.println("‡   1. 고객 명단 확인                        ‡");
		System.out.println("‡   2. 객실 리스트                            ‡");
		System.out.println("‡   3. 예약 리스트                            ‡");
		System.out.println("‡   4. 객실 예약                               ‡");
		System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
	}
	
	public static void customerSubMenu() {
		System.out.println("-+-+-+-+-회원 관리 시스템-+-+-+-+-");
		System.out.println("1. 전체 회원 조회");
		System.out.println("2. 특정 회원 조회");
		System.out.println("3. 회원 등록");
	}
	
	// 호텔 고객 리스트
	public void membersList(List<Customer> members) {
		System.out.println("=====호텔 고객 리스트=====");
		for (Customer customer : members) {
			//toString();
			System.out.println(customer.toString());
		}
	}

	//특정 고객 조회
	public void findMember(String name, List<Customer> customer) {
		System.out.println("'" + name +"'" + "조회 결과 입니다");
		for (Customer customer2 : customer) {
			System.out.println(customer2.toString());
		}
		
	}

	//회원 등록
	public void addMemberSuccess() {
		System.out.println("회원 등록에 성공하였습니다.");
	}
	
	public void findRoom(List<Room> rooms) {
		System.out.println("=====전체 객실 조회 결과=====");
		for (Room room : rooms) {
			System.out.println(room.toString());
		}
	}

	public void successPage() {
		System.out.println("정상 등록 되었습니다.");
	}

	public void errorPage() {
		System.out.println("문제가 발생하였습니다.");
	}

	
	
	
	public void reservationRoom(Reservation rsv) {
//		System.out.println("'" + rsv.getCustomer_id() +"'");
		
		
		
		
	}

	public void findReservation(List<Reservation> findReservation) {
		System.out.println("===예약 리스트===");
		for (Reservation reservation : findReservation) {
			System.out.println(reservation.toString());
		}
	}

	public void emptyRoom(List<Room> emptyRoom) {
		System.out.println("========빈 객실 리스트==========");
		
		for (Room room : emptyRoom) {
			System.out.println(room.toString());
		}
		
	}
	
	
}
