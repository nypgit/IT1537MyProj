package staywell.entities.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import staywell.entities.Customer;

public class CustomerDao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs1 = null;
	static PreparedStatement pstmt1 = null;
	
	public Customer register(Customer customer) {
		Statement stmt = null;
		
		// get the last member ID 
		try {
			
			currentCon = DBConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			String getMax = "select Max(customerid) from customerids";
			rs1 = stmt.executeQuery(getMax);
			rs1.next();
			int maxId = rs1.getInt(1);
			System.out.println(maxId);
			int nextId = maxId + 1;

			String query1 = "insert into customerids values(?)";
			pstmt1 = currentCon.prepareStatement(query1);
			pstmt1.setInt(1, nextId);
			pstmt1.executeUpdate();
			
			String customerId = "S" + Integer.toString(nextId);
			customer.setCustomerId(customerId);
            System.out.println("New Member ID = " + customerId);
            // query for inserting into the table
            String query = "insert into Customer(customerid,customername, customertel) values(?,?,?)";
            pstmt = currentCon.prepareStatement(query);
            // inserting values
            pstmt.setString(1,customer.getCustomerId());
            pstmt.setString(2,customer.getCustomerName());
            pstmt.setString(3, customer.getCustomerTel());  
            pstmt.executeUpdate();
            
		} catch (Exception ex) {

			System.out.println("Registration failed: An Exception has occurred! "
					+ ex);
		}

		// exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		return customer;

	}
	
	
	public static Customer searchByName(String name) {
		Customer member = null;
		Statement stmt = null;
        String searchQuery = "select * from member where name ='"
                + name + "' ";

        try {
            // connect to DB
            currentCon = DBConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more) {
              System.out.println("member with the name = " + name + " does not exst");
            }

            // if user exists set the isValid variable to true
            else if (more) {
                String memberId = rs.getString("member_id");
                String memberName = rs.getString("name");
                String memberTel = rs.getString("tel");
                member = new Customer();
                member.setCustomerId(memberId);
                member.setCustomerName(memberName);
                member.setCustomerTel(memberTel);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

		return  member;
	}
}
