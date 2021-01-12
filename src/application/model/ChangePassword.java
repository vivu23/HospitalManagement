package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChangePassword {
	
	public boolean changePassword(String userID, String password, String user, String newPassword) {
		boolean check = false;
		Statement statement = null;
		ResultSet resultSet = null;
		Statement statement1 = null;
		int result =0;
		Connection c = null;	
		String sql =null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
			
			if(user == "doc") {
				sql = "select * from doctor where password = \'" + password + "\' and docid = \'" + userID + "\'";
			}
			else {
				sql = "select * from " + user + " where password = \'" + password + "\' and " + user + "id = \'" + userID + "\'";
			}
			System.out.println(sql);
			statement = c.createStatement();
			resultSet = statement.executeQuery(sql);
			statement1 = c.createStatement();
	
			if(!resultSet.next()){
				check = false;
			}
			else {
				if(user == "doc") {
					result = statement1.executeUpdate("update doctor set password = \'" + newPassword + "\' where docid = \'" + userID + "\'");
				}
				else if (user == "nurse") {
					result = statement1.executeUpdate("update nurse set password = \'" + newPassword + "\' where " + user + "id = \'" + userID + "\'");
				}
				if(result != 0) {
					check = true;
				}
				else {
					check = false;
				}
			}
			statement.close();
			statement1.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;	
	}
}
