package step3.main;

import java.util.List;

import step3.dao.fabric.DaoFabric;
import step3.dao.instance.RecipesDao;
import step3.dao.instance.UserDao;
import step3.model.RecipeModelBean;
import step3.model.UserModelBean;

public class Launcher {
	public static void main(String[] args) {
		
		UserDao userDao=DaoFabric.getInstance().createUserDao();
		RecipesDao recipesDao=DaoFabric.getInstance().createRecipesDao();
		
		UserModelBean user1=new UserModelBean("Doe", "John",55, "jdoe", "pwd");
		RecipeModelBean recipe1=new RecipeModelBean("Fish Salad", "bla bla bal bla",5 , 180, 10, "salad");
		RecipeModelBean recipe2=new RecipeModelBean("Fresh Meat", "bla bla bal bla",1, 20, 1, "meat");
		
		userDao.addUser(user1);
		
		recipesDao.addRecipe(recipe1);
		recipesDao.addRecipe(recipe2);
		
		List<UserModelBean> userList=userDao.getAllUser();
		List<RecipeModelBean> recipeList=recipesDao.getAllRecipes();
		
		for(UserModelBean userTmp:userList){
			System.out.println("User added:"+userTmp);
		}
		
		for(RecipeModelBean recipeTmp:recipeList){
			System.out.println("User added:"+recipeTmp);
		}
	}
}
