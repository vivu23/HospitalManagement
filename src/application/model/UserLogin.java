package application.model;

import java.sql.*;

public class UserLogin {
	
	public boolean checkPassword(String user, String userID, String password) {
		boolean check = false;
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
			if(user == "doc") {
				sql = "select * from doctor where docid = \'" + userID + "\' and password = \'" + password + "\'";
			}
			else {
				sql = "select * from " + user + " where " + user + "id = \'" + userID + "\' and password = \'" + password + "\'";
			}
			System.out.println(sql);
			statement = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if(!resultSet.next()) {
				check = false;
			}
			else {
				check = true;
			}
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public boolean createAccount(String user, String fullname, String userID, String password) {
		Statement statement1 = null;
		Statement statement2 = null;
		ResultSet resultSet1 = null;
		Connection c = null;	
		String sql =null;
		boolean i = false;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
			
			statement1 = c.createStatement();
			
			if(user == "doc") {
				resultSet1 = statement1.executeQuery("select * from doctor where docid = \'" + userID + "\'");
				sql = "insert into doctor (" + user + "id, dname, password) values (\'" + userID + "\',\'" + fullname + "\',\'" + password + "\')";
			}
			else if (user == "nurse") {
				resultSet1 = statement1.executeQuery("select * from nurse where nurseid = \'" + userID + "\'");
				String str = "select * from nurse where nurseid = \'" + userID + "\'";
				sql = "insert into " + user + " (nurseid, nname, password) values (\'" + userID + "\',\'" + fullname + "\',\'" + password + "\')";
			}
			
			if(resultSet1.next()) {
				i = false;
			}
			else {
				System.out.println(sql);
				statement2 = c.createStatement();
				int check = statement2.executeUpdate(sql);
				if(check != 0) {
					i = true;
					statement1.close();
					statement2.close();
					c.close();
				}
			}
			} catch (Exception e) {
				
			}
		return i;
	}
	
	public String getFullName(String user, String userID) {
		String fname = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
			if(user == "doc") {
				sql = "select * from doctor where docid = \'" + userID + "\'";
			}
			else {
				sql = "select * from " + user + " where " + user + "id = \'" + userID + "\'";
			}
			System.out.println(sql);
			statement = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				if(user == "doc") {
					fname = resultSet.getString("dname");
				}
				else if(user == "nurse") {
					fname = resultSet.getString("nname");
				}
			}
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(fname);
		return fname;
	}
}