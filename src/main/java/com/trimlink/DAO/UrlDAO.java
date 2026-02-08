package com.trimlink.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.trimlink.models.Url;


public class UrlDAO {
	
	private static Properties properties = new Properties();
	
	static {
		try {
			InputStream is = UrlDAO.class.getClassLoader().getResourceAsStream("db.properties");
			properties.load(is);
			
			Class.forName(properties.getProperty("DB_DRIVER"));
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	
	public void generateShortUrl(String longUrl, String slug, int userId) throws Exception{
		
		String slug2 = "";
		
		if(slug.equals("")) {
			slug2 = randomSlug();
		}
		else {
			slug2 = slug;
		}
		
		String query = "insert into urls(user_id, long_url, short_url) values (?,?,?)";
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1,userId);
		ps.setString(2, longUrl);
		ps.setString(3, slug);
		
		System.out.println("Inserted URLS Rows: "+ ps.executeUpdate());
		
		ps.close();
		con.close();
			
		
	}
	
	public String randomSlug() {
		StringBuilder slug = new StringBuilder();
		
		Random  random = new Random();
		
		while(slug.length() != 5) {
			int ran = random.nextInt(3);
			
			if(ran == 0) slug.append( (char)(random.nextInt(26) + 65));
			else if(ran == 1) slug.append( (char)(random.nextInt(26) + 97));
			else slug.append( (char)(random.nextInt(10) + 48));
		}
		
		return slug.toString();
	}
	
	public List<Url> getAllUrls(int userId) throws Exception{
		
		String query = "select * from urls where user_id = ?";
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, userId);
		
		ResultSet rs = ps.executeQuery();
		
		List<Url> list = new ArrayList<Url>();
		
		while(rs.next()) {
			Url url = new Url();
			url.setUrl_id(rs.getInt(1));
			url.setUser_id(rs.getInt(2));
			url.setLong_url(rs.getString(3));
			url.setShort_url(rs.getString(4));
			
			list.add(url);
		}
		
		return list;
	}
	
	
	public String getLongUrl(String slug) {
			String query = "select long_url from urls where short_url= ?";
			try {
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, slug);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					return rs.getString("long_url");
				}
			}catch (Exception e) {
			 System.out.println(e.getMessage());
			}
			
			return null;
	}
	
	
	public boolean deleteUrl(int urlId) throws Exception {

	    String query = "DELETE FROM urls WHERE url_id = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setInt(1, urlId);

	        return ps.executeUpdate() > 0;
	    }
	}

}
