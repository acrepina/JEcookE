package processing;

import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.LoginBean;
import model.UserModelBean;
import model.UserSubmissionModelBean;
import dao.fabric.DaoFabric;
import dao.instance.UserDao;
import java.util.regex.*;
@ManagedBean
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entrée unique à l'ensemble des clients
public class UserControlerBean {
	
	private UserDao userDao;
	
	public UserControlerBean() {
		this.userDao=DaoFabric.getInstance().createUserDao();
	}
	
	public String checkUser(LoginBean loginBean){
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(),
				loginBean.getPwd());
		if( user!=null){
			//récupère l'espace de mémoire de JSF
			ExternalContext externalContext =
					FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			//place l'utilisateur dans l'espace de mémoire de JSF
			sessionMap.put("loggedUser", user);
			//redirect the current page
			return "userdisplay.xhtml";
		}else{
			//redirect the current page
			return "userLogin.xhtml";
		}
	}
	
	public String checkAndAddUser(UserSubmissionModelBean userSubmitted){
		//Vérifier les propriétés de l'utilisateur
//		 First Name / Last Name : autorisé [a-zA-Z0-9]
//		 Age : uniquement des entiers <100
//		 Email Address: autorisé [a-zA-Z0-9-._]+@[a-zA-Z0-9-._].[a-z]+
//		 Login: autorisé [a-zA-Z0-9-._]
//		 Password: identique à Re-enter Password
//		 En cas d’erreur la valeur du champ en erreur devra être en rouge
		
		
		
		//ajout de l'utilisateur à la base de données
		this.userDao.addUser(userSubmitted);
		return "/pages/activitySelection.xhtml";
	}
}
