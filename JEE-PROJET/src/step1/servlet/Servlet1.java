package step1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import step1.db.DB;
import step1.model.UserModel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
		
	private	static final long serialVersionUID = 1L;
	private DB	db;
	
	/**
	 * @see	HttpServlet#HttpServlet()
	 */
	public Servlet1() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		db = new DB();
		ArrayList<UserModel> userList = db.getData();
		
		PrintWriter wr = response.getWriter();
        wr.println("<!DOCTYPE html>");
        wr.println("<html>");
        wr.println("<head>");
        wr.println("<title>Liste des utilisateurs</title>");
        wr.println("</head>");
        wr.println("<body>");
        
		for (UserModel user : userList) {
            wr.println("<div>" +user.toString() + "</div>");
        }
		wr.println("</body></html>");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}