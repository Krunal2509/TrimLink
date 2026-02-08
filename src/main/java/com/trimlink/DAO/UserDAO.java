package com.trimlink.DAO;

import java.util.Properties;

import com.trimlink.models.User;

import java.io.InputStream;
import java.sql.*;


public class UserDAO {
	
//	UserDAO(ServletContext servletContext){
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection(servletContext.getInitParameter("DB_URL"), 
//														 servletContext.getInitParameter("DB_USER"),
//														 servletContext.getInitParameter("DB_PASSWORD"));
//			
//			
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//	}
	
	private static Properties properties = new Properties();
	
	
	static{
		try {
			InputStream input = UserDAO.class.getClassLoader()
	                    .getResourceAsStream("db.properties");

	        properties.load(input);

			
			Class.forName(properties.getProperty("DB_DRIVER"));
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private Connection getConnection() throws Exception {
//		System.out.println("URL: " + properties.getProperty("DB_URL"));
//		System.out.println("USER: " + properties.getProperty("DB_USER"));
//		System.out.println("PASS: " + properties.getProperty("DB_PASSWORD"));

			return DriverManager.getConnection(properties.getProperty("DB_URL"),
			 								   properties.getProperty("DB_USER"),
			 								   properties.getProperty("DB_PASSWORD"));
	}
	
	
	public boolean registerUser(User user) {
		try {
			Connection con = getConnection();
			
			String query = "insert into users(user_name, password) values(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getPassword());
			
			
			return ps.executeUpdate() > 0;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	public User loginUser(User user) {
		try {
			String query = "select * from users where user_name=? and password=?";
			Connection con = getConnection();
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, user.getUser_name());
			preparedStatement.setString(2, user.getPassword());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				int userId = rs.getInt(1);
				user.setUser_id(userId);
				user.setPassword("");
				return user;
			}
			else {
				System.out.println("Error In DAO Login USER");
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	
}
