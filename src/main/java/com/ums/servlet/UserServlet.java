package com.ums.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ums.dao.UserDAO;
import com.ums.entity.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private UserDAO userDAO;
	public void init() {
		userDAO=new UserDAO();
	}

	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		doGet(req,res);

}

	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		String url=req.getServletPath();
		try {
			switch(url) {
			case"/new":
				insertUser(req,res);
				break;
			case"/add":
				insertUserToDb(req,res);
				break;
			case"/delete":
				deleteUser(req,res);
				break;
			case"/update":
			updateUser(req,res);
			break;
			case"/edit":
				GetUser(req,res);
				break;
				default:
					listUser(req,res);
					break;
					}
		
	}catch(SQLException ex) {
		throw new ServletException(ex);
	}
	}
	private void insertUserToDb(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		System.out.println("insert called");
		String UserName = req.getParameter("userName");
		String Password = req.getParameter("password");
		String Emailid = req.getParameter("emailid");
		String PhoneNumber = req.getParameter("phoneNumber");
		String Address = req.getParameter("address");
		User user=new User(UserName,Password,Emailid,PhoneNumber,Address);
		userDAO.insertUser(user);
		res.sendRedirect("list");
		
	}

	private void GetUser(HttpServletRequest req, HttpServletResponse res)throws SQLException, ServletException, IOException  {
		System.out.println("GetUser----Userservlet");
		int id = Integer.parseInt(req.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
		req.setAttribute("user", existingUser);
		dispatcher.forward(req,res);

	}
	
	
     private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list =userDAO.selectAllUsers();
		request.setAttribute("listUser", list);//session man
		System.out.println(list.toString());
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
		
	}
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
	RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
	dispatcher.forward(request, response);
	}
	
		
		
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("update called");
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("update called");
		String UserName = request.getParameter("userName");
		String Password = request.getParameter("password");
		String Emailid = request.getParameter("emailid");
		String PhoneNumber = request.getParameter("phoneNumber");
		String Address = request.getParameter("address");

		User user = new User(id,UserName,Password,Emailid,PhoneNumber,Address);
		userDAO.updateUser(user);
		response.sendRedirect("list");
	}
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");

	}

}
