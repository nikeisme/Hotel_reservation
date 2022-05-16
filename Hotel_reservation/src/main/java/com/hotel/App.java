package com.hotel;


import java.sql.Date;
import java.util.Scanner;

import com.hotel.controller.HotelController;
import com.hotel.model.Customer;
import com.hotel.model.Reservation;
import com.hotel.view.HotelView;

public class App 
{
    public static void main( String[] args )
    {
    	
    	boolean menuChoice=true; 
    	HotelController hotelController = new HotelController();
    	Scanner input = new Scanner(System.in);
    	Customer member;
    	Reservation resrv;
    	while(menuChoice) {
    		//호텔 Main 화면 호출
	    	HotelView.mainList();
	    	
	    	switch(input.nextInt()) {
			case 1 : //고객 명단 확인
				HotelView.customerSubMenu();
				
				switch(input.nextInt()) {
					case 1 : //전체 고객 조회
						hotelController.membersList();
						break;
					case 2 : //특정 고객 조회
						System.out.println("=====특정 고객 조회=====");
						System.out.printf("고객 명 :  ");
						String findName = input.next();
						hotelController.findMember(findName);
						break;
					case 3 : //고객 추가
						System.out.println("=====고객 등록=====");
						System.out.printf("이름 : ");
						String name = input.next();
						System.out.printf("\n연락처 : ");
						String callNumber = input.next();
						System.out.printf("\n생년월일 : ");
						String birth = input.next();
						
						Customer newMember = Customer.builder()
											.name(name)
											.callNumber(callNumber)
											.birth(Date.valueOf(birth))
											.age(birthOfAge(Date.valueOf(birth)))
											.build();
						
						hotelController.addMember(newMember);
				}
			
				break;

				
			case 2 : //객실 리스트 확인
				hotelController.findRoom();
				break;
			case 3 : //예약 리스트 확인
				hotelController.findReservation();
				break;
			case 4 : //객실 예약
				hotelController.emptyRoom();
				
				System.out.println("빈 객실 호수 입력 :  ");
				int roomNumber = input.nextInt();
				
				//고객 정보 입력
				System.out.println("고객 이름 입력 :  ");
				String name = input.next();
				System.out.println("고객 연락처 입력 :  ");
				String callNumber = input.next();
				
				//예약 정보 입력
				System.out.println("입실 날짜 입력 :  ");
				String checkIn = input.next();
				System.out.println("퇴실 날짜 입력 :  ");
				String checkOut = input.next();
				System.out.println("예약 인원 :  ");
				int numOfPeople = input.nextInt();
				resrv = Reservation.builder().checkIn(Date.valueOf(checkIn)).checkOut(Date.valueOf(checkOut)).numOfPeople(numOfPeople).build();
				
				hotelController.reservationRoom(roomNumber, callNumber, name, resrv);
				break;
			case 0 :
				menuChoice = false;
				System.out.println("호텔 예약 시스템 종료");
				break;
	    	}
    	}
    
    }

    private static int birthOfAge(Date birth) {
    	return 2022 - birth.toLocalDate().getYear()+1;
	}
    
 
    
}

