package application.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Operation {
	private String operationID;
	private String operationName;
	private LocalDate scheduledate;
	private String docid;
	private String patientid;
	private int numofStaff;
	private int count =0;
	private String pname;
	private String Strdate;
	
	public Operation() {
		this.operationID = "";
		this.operationName = "";
		this.numofStaff = 0;
		this.scheduledate = LocalDate.of(2021,1,1);
		this.docid ="";
		this.patientid ="";
		this.count =0;
		this.Strdate = "";
	}
	
	public Operation(String opID, String opName, String docid, String patientid, LocalDate scheduledate, int count,int numOfStaff) {
		this.operationID = opID;
		this.operationName = opName;
		this.numofStaff = numOfStaff;
		this.docid = docid;
		this.patientid = patientid;
		this.count = count;
		Strdate = convertToStrDate(scheduledate);
	}
	public String getStrdate() {
		return Strdate;
	}

	public void setStrdate(String strdate) {
		Strdate = strdate;
	}

	public String getOperationID() {
		return operationID;
	}

	public void setOperationID(String operationID) {
		this.operationID = operationID;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public LocalDate getScheduledate() {
		return scheduledate;
	}

	public void setScheduledate(LocalDate scheduledate) {
		this.scheduledate = scheduledate;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public int getNumofStaff() {
		return numofStaff;
	}

	public void setNumofStaff(int numofStaff) {
		this.numofStaff = numofStaff;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPname() {
		return pname;
	}
	
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public String convertToStrDate(LocalDate scheduledate) {
		String sDate = "";
		sDate = scheduledate.toString();
		return sDate;
	}
	

	
	/* 
	 * public void showOperation
	 * input: 4 ArrayList<String>, 1 ArrayList<Date>, 1 ArrayList<Integer>
	 * output: none
	 * 
	 * This function will take the operation information from the database and add it in the corresponding ArrayList from the parameter
	 * 
	 */
	public void showOperation(ArrayList<String> doctors,ArrayList<String> patients ,ArrayList<String> opID, ArrayList<String> opName, ArrayList<LocalDate> scheduleDate, ArrayList<Integer> numOfStaff ) {
		boolean check = false;
		Patient patientInfo = new Patient();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;
		String sql1 = null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
					
			sql = "select * from operation";
			System.out.println(sql);
			statement = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				opID.add(resultSet.getString("operationid"));
				opName.add(resultSet.getString("operationname"));
				patients.add(resultSet.getString("patientid"));
				doctors.add(resultSet.getString("docid"));
				scheduleDate.add(resultSet.getDate("scheduledate").toLocalDate());
				numOfStaff.add(resultSet.getInt("numofstaff"));
			}
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean addOperation(String docid, String patientid, String opid, String opName, LocalDate date, int numStaff) {
		boolean check = false;
		Statement statement = null;
		Statement statement1 = null;
		Statement statement2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		Connection c = null;	
		String sql =null;
			
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			sql = "select * from patient where patientid = \'" + patientid + "\'";
			statement = c.createStatement();
			statement1 = c.createStatement();
			statement2 = c.createStatement();
			resultSet = statement.executeQuery(sql);
			Date sqlDate = Date.valueOf(date);
			System.out.println(date);
			System.out.println("sqlDate: " + sqlDate);
			if(!resultSet.next()) {
				check = false;
			}
			else {
				resultSet1 = statement2.executeQuery("select * from operation where operationid = \'" + opid + "\'");
				if(resultSet1.next()) {
					check = false;
				}
				else {
					int test =statement1.executeUpdate("insert into operation (operationid, operationname, docid, patientid,"
						+ "scheduledate, numofstaff) values (\'" + opid + "\', \'" + opName + "\', \'" + docid + "\', \'" 
							+ patientid + "\', \'"+ sqlDate + "\', \'"	+ numStaff + "\')");
				
					if(test != 0) {
						check = true;
					}
					else {
						check = false;
					}
				}
			}
			statement1.close();
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public boolean deleteOperation(String OID,ArrayList<String> opID, ArrayList<String> opName, ArrayList<String> doc, ArrayList<String> patient, ArrayList<LocalDate> scheduleDate2, ArrayList<Integer> numofstaff, int index) {
		boolean check = false;
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
			
			sql = "select * from operation where operationid = \'" + OID +  "\'";

			System.out.println(sql);
			statement = c.createStatement();
			Statement statement1 = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if(!resultSet.next()) {
				check = false;
			}
			else {
				int test =statement1.executeUpdate("delete from operation where operationid = \'" + OID + "\'");
				if(test != 0) {
					check = true;
					index = opID.indexOf(OID);
					System.out.println(index);
					opID.remove(index);
					opName.remove(index);
					doc.remove(index);
					patient.remove(index);
					scheduleDate2.remove(index);
					numofstaff.remove(index);
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
	

	public void checkNumOperation(ArrayList<String> doctors, ArrayList<Integer> NumOfop) {
		boolean check = false;
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
					
			sql = "SELECT COUNT(OperationID), DocID FROM OPERATION GROUP BY docid;";
			System.out.println(sql);
			statement = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				doctors.add(resultSet.getString("docid"));
				NumOfop.add(resultSet.getInt("count"));
			}
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkMyOperation(String docid,ArrayList<String> patient, ArrayList<String> Operationid,
			ArrayList<String> OperationName ) {
		
		boolean check = false;
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
					
			sql = "SELECT OPERATION.OperationID, OPERATION.operationname, PATIENT.Pname FROM ( "
					+ "(OPERATION INNER JOIN DOCTOR ON OPERATION.DocID = DOCTOR.DocID) "
					+ "INNER JOIN PATIENT ON OPERATION.PatientID = PATIENT.PatientID) Where doctor.docid =\'" + docid + "\'"; 

			System.out.println(sql);
			statement = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				patient.add(resultSet.getString("pname"));
				Operationid.add(resultSet.getString("operationid"));
				OperationName.add(resultSet.getString("operationname"));
			}
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean changeNumofStaff(String operationName, int numofstaff) {
		boolean check = false;
		Statement statement = null;
		Statement statement1 = null;
		int result =0;
		Connection c = null;	
		String sql =null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");
			
			sql = "update operation set numofstaff =" + numofstaff + " where operationName = \'" + operationName + "\'";
			System.out.println(sql);
			statement = c.createStatement();
			result = statement.executeUpdate(sql);
	
			if(result == 0){
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
	public void checkNumofStaff(ArrayList<String> opID, ArrayList<String> opName , ArrayList<Integer> num,
			int NumOfStaff) {
		boolean check = false;
		Statement statement = null;
		ResultSet resultSet = null;
		Connection c = null;	
		String sql =null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","hospitaluser","thuyvi2309");
			
			System.out.println("Connected");

			sql = "SELECT operationid, operationname, NumOfStaff FROM OPERATION "
					+ "GROUP BY operationid, operationname, numOfStaff HAVING numofstaff > " + NumOfStaff; 

			System.out.println(sql);
			statement = c.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				opID.add(resultSet.getString("operationid"));
				opName.add(resultSet.getString("operationname"));
				num.add(resultSet.getInt("numofstaff"));
			}
			statement.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
