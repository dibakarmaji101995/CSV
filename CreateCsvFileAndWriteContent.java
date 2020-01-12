package com.nit.corejava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import sun.util.logging.resources.logging;

public class CreateCsvFileAndWriteContent {
	public static void main(String[] args) throws IOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String[] headers = { "SYS_DATE" };
		try {
			// regisgter jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			// establish the connnection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nit", "root", "root");
			// create STatement obj
			if (con != null)
				st = con.createStatement();
			// send and execute SQL Query
			if (st != null) {
				rs = st.executeQuery("SELECT sysdate FROM DATE_TABLE");
			}
			createCSVFile(headers, rs);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void createCSVFile(String[] headers, ResultSet rs) throws IOException, SQLException {
		File file = new File("D:\\DIBAKAR_MAJI\\book.csv");
		FileWriter out = null;
		if (!file.exists()) {
			out = new FileWriter(file);
			try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
				//create SimpleDateFormat object
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
				while(rs.next()){
					Date date = rs.getDate("sysdate");
					String dateString = sdf.format(date);
					printer.printRecord(" "+dateString);
				}
			}
		} else {
			System.out.println("File Already exist");
		}
	}
}
