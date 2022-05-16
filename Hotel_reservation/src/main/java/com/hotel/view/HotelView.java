package com.hotel.view;

import java.util.List;

import com.hotel.model.Customer;

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
	public void findMember(Customer customer) {
		System.out.println("'" + customer.getName() +"'" + "조회 결과 입니다");
		System.out.println(customer.toString());
		
	}

	public void addMemberSuccess() {
		System.out.println("회원 등록에 성공하였습니다.");
		
	}
}
