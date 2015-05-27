package step2.model;

import java.io.Serializable;

//contrainte BEAN implements Serializable
public class UserModelBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lastname	;
	private String surname;
	private int	age	;
	private	String login;
	private	String pwd;
	
	//Contrainte BEAN constructeur sans paramètre
	public 	UserModelBean() {
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void	setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login= login;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void	setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "[SURNAME]:"+this.getSurname()+",[LASTNAME]:"+this.getLastname()+",[AGE]:"+this.getAge()+",[LOGIN]:"+this.getLogin()+",[PWD]:"+this.getPwd();
	}
}