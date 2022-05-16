package com.hotel;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.hotel.controller.HotelController;
import com.hotel.model.Customer;
import com.hotel.view.HotelView;

public class App 
{
    public static void main( String[] args )
    {
    	
    	boolean menuChoice=true; 
    	HotelController hotelController = new HotelController();
    	Scanner input = new Scanner(System.in);
    	
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
				System.out.println("2번");
				break;
			case 3 : //예약 리스트 확인
				System.out.println("3번");
				break;
			case 4 : //객실 예약
				System.out.println("4번");
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

