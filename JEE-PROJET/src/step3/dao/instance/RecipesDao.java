package step3.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step3.model.RecipeModelBean;
import step3.model.UserModelBean;

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
	
	public void addRecipe(RecipeModelBean recipe) {
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
	
	public ArrayList<RecipeModelBean> getAllRecipes() {
		//return value
		ArrayList<RecipeModelBean> recipeList = new ArrayList<RecipeModelBean>();
		
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			java.sql.Statement query = connection.createStatement();
			RecipeModelBean r;
			java.sql.ResultSet rs = query.executeQuery( "SELECT * FROM recipes");
			while (	rs.next() )
			{
				r = new RecipeModelBean(rs.getString("title"), rs.getString("description"), rs.getInt("expertise"), rs.getInt("duration"), rs.getInt("nbpeople"), rs.getString("type"));
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
}