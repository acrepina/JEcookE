package dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.RecipeModel;

public class RecipesDao {
	
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;
	private static int id = 0;
	
	public RecipesDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}
	
	public void addRecipe(RecipeModel recipe) {
		// Création de la requête
		java.sql.Statement query;
		
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			//Creation de l'élément de requète
			query =	connection.createStatement();
			int rs = query.executeUpdate( "INSERT INTO recipes (title, description, expertise, duration, nbpeople, type) VALUES ('" + recipe.getTitle() + "', '" + recipe.getDescription() + "', '" + recipe.getExpertise() + "', '" + recipe.getDuration() + "', '" + recipe.getNbpeople() + "', '" + recipe.getType() + "')");
			query.close	();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<RecipeModel> getAllRecipes() {
		//return value
		ArrayList<RecipeModel> recipeList = new ArrayList<RecipeModel>();
		
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			java.sql.Statement query = connection.createStatement();
			RecipeModel r;
			java.sql.ResultSet rs = query.executeQuery( "SELECT * FROM recipes");
			while (	rs.next() )
			{
				r = new RecipeModel(rs.getString("title"), rs.getString("description"), rs.getInt("expertise"), rs.getInt("duration"), rs.getInt("nbpeople"), rs.getString("type"));
				recipeList.add(r);
			}
			rs.close();
			query.close	();
			
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeList;
	}
	
	public ArrayList<RecipeModel> getRecipes(int temps, int expert, int nmb_perso, String type ) {
		//return value
		ArrayList<RecipeModel> recipeList = new ArrayList<RecipeModel>();
		
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			String selectSQL = "SELECT * FROM recipes WHERE expertise=? AND duration=? AND nbpeople=? AND type=?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1,expert);
			preparedStatement.setInt(2,temps);
			preparedStatement.setInt(3,nmb_perso);
			preparedStatement.setString(4,type);
			ResultSet rs = preparedStatement.executeQuery(selectSQL );
			
			RecipeModel r;
			 
            //System.out.println(listItems);   
			//java.sql.ResultSet rs = query.executeQuery( "SELECT * FROM recipes WHERE expertise={0},duration={1},nbpeople={2}, type={3}",expert,temps,nmb_perso,type );
			while (	rs.next() )
			{
				r = new RecipeModel(rs.getString("title"), rs.getString("description"), rs.getInt("expertise"), rs.getInt("duration"), rs.getInt("nbpeople"), rs.getString("type"));
				recipeList.add(r);
			}
			rs.close();
			preparedStatement.close	();
			
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeList;
	}
}