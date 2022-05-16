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
import com.hotel.utils.DBUtils;

public class HotelDAO {
	SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private CustomerBuilder builderMember;
	private Customer member;
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
				int customer_id = rs.getInt("회원_ID");
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

	public Customer findMemeber(String name) {
		final String findMemberQuery = "SELECT * FROM customer WHERE 이름 = ?";
		
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(findMemberQuery);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = Customer.builder()
								  .customer_id(rs.getInt("회원_ID"))
								  .callNumber(rs.getString("연락처"))
								  .name(rs.getString("이름"))
								  .birth(rs.getDate("생년월일"))
								  .age(rs.getInt("나이")).build();
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
		return member;
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

}
