package com.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.Customer;
import com.hotel.model.Customer.CustomerBuilder;
import com.hotel.model.Reservation;
import com.hotel.model.Reservation.ReservationBuilder;
import com.hotel.model.Room;
import com.hotel.model.Room.RoomBuilder;
import com.hotel.utils.DBUtils;

public class HotelDAO {
	SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private CustomerBuilder builderMember;
	private Customer member;
	private RoomBuilder roomBuilder;
	private Room room;
	
	//전체 회원 조회
	public List<Customer> membersList() {
		final String allMemberSelect = "SELECT * FROM customer";
		final List<Customer> members = new ArrayList<>();
		
		try (//connection 
			Connection conn = DBUtils.getConnection();
			//쿼리 전달 객체
			Statement stmt = conn.createStatement();
			//실제 쿼리 수행
			ResultSet rs = stmt.executeQuery(allMemberSelect);){
			
			while(rs.next()) {
				int customer_id = rs.getInt("회원ID");
				String callNumber = rs.getString("연락처");
				String name = rs.getString("이름");
				Date birth = rs.getDate("생년월일");
				int age = rs.getInt("나이");
				
				builderMember = Customer.builder().customer_id(customer_id)
						.callNumber(callNumber).name(name).birth(birth).age(age);
				members.add(builderMember.build());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return members;
	}

	public List<Customer> findMemeber(String name) {
		final String findMemberQuery = "SELECT * FROM customer WHERE 이름 = ?";
		final List<Customer> members = new ArrayList<>();
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(findMemberQuery);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = Customer.builder()
								  .customer_id(rs.getInt("회원ID"))
								  .callNumber(rs.getString("연락처"))
								  .name(rs.getString("이름"))
								  .birth(rs.getDate("생년월일"))
								  .age(rs.getInt("나이")).build();
				members.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { //자원 해제
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return members;
	}

	public int addMember(Customer newMember) {
		final String insertMember = "INSERT INTO customer (연락처, 이름, 생년월일, 나이) VALUES(?, ?, ?, ?)";
		int addedRows = 0;
		try(Connection conn = DBUtils.getConnection();
			PreparedStatement pstmt = createPreparedStatement(conn, insertMember, newMember);)
		{
			addedRows = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return addedRows;
	}
	
	private PreparedStatement createPreparedStatement(Connection connection, String sql, Customer newMember) throws SQLException {
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, newMember.getCallNumber());
		pstmt.setString(2, newMember.getName());
		pstmt.setDate(3, newMember.getBirth());
		pstmt.setInt(4, newMember.getAge());
		return pstmt;
	}
	
	//전체 객실 조회
	public List<Room> findRoom() {
		final String selectQuery = "SELECT * FROM room";
		List<Room> rooms = new ArrayList<>();

		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);

			while (rs.next()) {
				room = Room.builder().roomNumber(rs.getInt("객실호수"))
						.roomType(rs.getString("객실등급"))
						.isCleand(rs.getBoolean("청소상태"))
						.build();

				rooms.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rooms;
	}
	
	

	public List<Reservation> findReservation() {
		final String reservationSearch = "SELECT * FROM reservation";
		final List<Reservation> resvList = new ArrayList<>();
		
		
		try (Connection conn = DBUtils.getConnection();
				//쿼리 전달 객체
				Statement stmt = conn.createStatement();
				//실제 쿼리 수행
				ResultSet rs = stmt.executeQuery(reservationSearch);){
			
				
				while(rs.next()) {
					int reservation_No = rs.getInt("예약번호");
					int customer_id = rs.getInt("회원ID");
					int roomNumber = rs.getInt("객실호수");
					Date checkIn = rs.getDate("입실일");
					Date checkOut = rs.getDate("퇴실일");
					boolean isStaying = rs.getBoolean("숙박여부");
					int numOfPeople = rs.getInt("예약인원");
					
					ReservationBuilder reservation = Reservation.builder()
							.reservation_No(reservation_No)
							.customer_id(customer_id)
							.roomNumber(roomNumber)
							.checkIn(checkIn)
							.checkOut(checkOut)
							.isStaying(isStaying)
							.numOfPeople(numOfPeople);
					resvList.add(reservation.build());
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resvList;
	}

	public List<Room> emptyRoom() {
		final String emptyRoomQuery = "SELECT r.객실호수, r.객실등급, r.청소상태 FROM "
				+ "room AS r JOIN reservation AS resvs ON r.객실호수 != resvs.객실호수 WHERE r.청소상태 = 1 GROUP BY r.객실호수 ORDER BY r.객실호수";
		final List<Room> emptyRooms = new ArrayList<>();
		
		try (Connection conn = DBUtils.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(emptyRoomQuery)){
			while(rs.next()) {
				roomBuilder = Room.builder().roomNumber(rs.getInt("객실호수")).roomType(rs.getString("객실등급")).isCleand(rs.getBoolean("청소상태"));
				emptyRooms.add(roomBuilder.build());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emptyRooms;
	}

	
	public int reservationRoom(int roomNumber, long c_ID, String name, Reservation resrv) {
		String insertQuery = "INSERT INTO reservation (객실호수, 회원ID, 입실일, 퇴실일, 숙박여부, 예약인원)  + VALUES ("+ roomNumber + ", (SELECT 회원ID FROM customer WHERE 이름=" + name + " OR 회원ID= +" + c_ID +"), ?, ?, ?, ?)";
		
//		String iQuery = "INSERT INTO reservation (객실호수, 회원ID, 입실일, 퇴실일, 숙박여부, 예약인원) VALUES (101, (SELECT 회원ID FROM customer WHERE 이름="+ name +" OR 회원ID=100011), ?, ?, ?, ?);";
		
		int addedRows = 0;
		
		try (Connection conn = DBUtils.getConnection();
			PreparedStatement psmt = createPreparedStatement(conn, insertQuery, resrv);)
		{
			addedRows = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return addedRows;
	}
	
	private PreparedStatement createPreparedStatement(Connection connection, String sql, Reservation rsv) throws SQLException {
		pstmt = connection.prepareStatement(sql);
		
		pstmt.setDate(1, rsv.getCheckIn());
		pstmt.setDate(2, rsv.getCheckOut());
		pstmt.setBoolean(3, rsv.isStaying());
		pstmt.setInt(4, rsv.getNumOfPeople());
		return pstmt;
	}

	

}
