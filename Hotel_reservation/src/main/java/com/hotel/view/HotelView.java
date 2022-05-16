package com.hotel.view;

import java.util.Scanner;


public class HotelView {
	
	//호텔 Main 화면 
	public static void mainList() {
		Scanner sc = new Scanner(System.in);

		System.out.println("-+-+-+-+-호텔 예약 시스템-+-+-+-+-");
		System.out.println("‡   1. 고객 명단 확인                        ‡");
		System.out.println("‡   2. 객실 리스트                            ‡");
		System.out.println("‡   3. 예약 리스트                            ‡");
		System.out.println("‡   4. 객실 예약                               ‡");
		System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		switch(sc.nextInt()) {
			case 1 : 
				System.out.println("1번");
				break;
			case 2 :
				System.out.println("2번");
				break;
			case 3 :
				System.out.println("3번");
				break;
			case 4 :
				System.out.println("4번");
				break;
		}
		
	}
}
