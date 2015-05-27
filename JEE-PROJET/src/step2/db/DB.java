package step2.db;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step1.model.UserModel;
import step2.model.UserModelBean;



public class DB {

	private static final String DB_HOST="db-tp.cpe.fr";
	private static final String DB_PORT="3306";
	private static final String DB_NAME="binome02";
	private static final String DB_USER="binome02";
	private static final String DB_PWD="binome02";
	private Connection connection;

	public DB() {
		try {
			//Chargement du Driver, puis établissement de la connexion
			Class.forName("com.mysql.jdbc.Driver");
			//create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME,DB_USER,DB_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<UserModel> getData(){

		//return value
		ArrayList<UserModel> userList = new ArrayList<UserModel>();

		// Création	de la requête
		java.sql.Statement query;
		try
		{
			query = connection.createStatement();
			UserModel u;
			java.sql.ResultSet rs = query.executeQuery( "SELECT * FROM users");
			while (	rs.next() )
			{
				u = new UserModel(rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age"), rs.getString("login"), rs.getString("pwd"));
				userList.add(u);
			}
			rs.close();
			query.close	();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	public void addUser(UserModelBean user) {

		//Création de la requête
		java.sql.Statement query;

		try
		{
			//Creation de l'élément de requète
			query =	connection.createStatement();
			int rs = query.executeUpdate( "INSERT INTO users (lastname, firstname, age, login, pwd, email) VALUES ('" + user.getSurname() + "', '" + user.getLastname() + "', '" + user.getAge() + "', '" + user.getLogin() + "', '" + user.getPwd() + "', 'toto@cpe.fr')");
			query.close	();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}