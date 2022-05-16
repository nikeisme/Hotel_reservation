package com.hotel.service;

import java.util.List;

import com.hotel.dao.HotelDAO;
import com.hotel.model.Customer;
import com.hotel.model.Room;

public class HotelService {

	private final HotelDAO hotelDAO;
	
	public HotelService() {
		hotelDAO = new HotelDAO();
	}
	
	public List<Customer> membersList() {
		
		return hotelDAO.membersList();
	}

	public Customer findMember(String name) {
		return hotelDAO.findMemeber(name);
		
	}

	public int addMember(Customer newMember) {
		return hotelDAO.addMember(newMember);
	}

	public List<Room> findRoom() {
		return hotelDAO.findRoom();
	}

}
