package dao.instance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserModelBean;


public class UserDao {
	
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;
	public UserDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}
	public void addUser(UserModelBean user) {
		
		// Création de la requête
		java.sql.Statement query;
		
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			//Creation de l'élément de requète
			query =	connection.createStatement();
			
			int rs = query.executeUpdate( "INSERT INTO users (lastname, firstname, age, login, pwd, email) VALUES ('" + user.getSurname() + "', '" + user.getLastname() + "', '" + user.getAge() + "', '" + user.getLogin() + "', '" + user.getPwd() + "', 'toto@cpe.fr')");
			query.close	();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<UserModelBean> getAllUser(){
		
		//return value
		ArrayList<UserModelBean> userList=new ArrayList<UserModelBean>();
		
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			java.sql.Statement query = connection.createStatement();
			UserModelBean u;
			java.sql.ResultSet rs = query.executeQuery( "SELECT * FROM users");
			while (	rs.next() )
			{
				u = new UserModelBean(rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age"), rs.getString("login"), rs.getString("pwd"));
				userList.add(u);
			}
			rs.close();
			query.close	();
			
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	public UserModelBean checkUser(String login, String pwd) {
		// Création de la requête
		java.sql.Statement query;
		UserModelBean u = null;
		
		try {
			// récupération des infos de l'user login
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query =	connection.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT * FROM users WHERE login='" + login + "'");
			rs.next();
			u = new UserModelBean(rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age"), rs.getString("login"), rs.getString("pwd"));
			
			query.close	();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
}
