package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Patient {
	
	private String name;
	private String id;
	private String diagnose;
	
	public Patient() {
		this.name = "";
		this.id = "";
		this.diagnose = "";
	}
	
	public Patient(String id, String name, String diagnose) {
		this.id = id;
		this.name = name;
		this.diagnose = diagnose;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	
	public void showPatient(ArrayList<String> id, ArrayList<String> name, ArrayList<String> diagnose) {
		boolean check = false;
		Patient patientInfo = new Patient();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
			
			sql = "select * from patient";
			System.out.println(sql);
			statement = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				id.add(resultSet.getString("patientid"));
				name.add(resultSet.getString("pname"));
				diagnose.add(resultSet.getString("diagnose"));
			}
			
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean addPatient(String id, String name, String diagnose) {
		boolean check = false;
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
			
			sql = "select * from patient where patientid = \'" + id + "\'";
			System.out.println(sql);
			statement = c.createStatement();
			Statement statement1 = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				check = false;
			}
			else {
				int test =statement1.executeUpdate("insert into patient (patientid, pname, diagnose) values (\'" + id + "\', \'" + name + "\', \'" + diagnose + "\')");
				
				if(test != 0) {
					check = true;
				}
				else {
					check = false;
				}
			}
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public boolean deletePatient(ArrayList<String> id, ArrayList<String> name, ArrayList<String> diagnose, String Pid, int index) {
		boolean check = false;
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
			
			sql = "select * from patient where patientid = \'" + Pid + "\'";

			System.out.println(sql);
			statement = c.createStatement();
			Statement statement1 = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if(!resultSet.next()) {
				check = false;
			}
			else {
				id.remove(resultSet.getString("patientid"));
				name.remove(resultSet.getString("pname"));
				diagnose.remove(resultSet.getString("diagnose"));
				int test =statement1.executeUpdate("delete from patient where patientid = \'" + Pid + "\'");
				if(test != 0) {
					check = true;
					
				}
				else {
					check = false;
				}
			}
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
}
