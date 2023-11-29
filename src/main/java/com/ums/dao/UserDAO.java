package com.ums.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ums.entity.User;


public class UserDAO {
	private String USERNAME="root";
	private String PASSWORD="Shabitha2904@";
	private String URL= "jdbc:mysql://localhost:3306/ums";
	private String DRIVER="com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users (name,password,emailid,phonenumber,address) VALUES  (?,?,?,?,?);\r\n"
			+ "";

	private static final String SELECT_USER_BY_ID = "select * from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,password= ?,emailid=?,phonenumber=?,address=? where id = ?;";

	protected Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			}
		return conn;
		}
	
		
		

	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String emailid = rs.getString("emailid");
				String phonenumber = rs.getString("phonenumber");
				String address = rs.getString("address");
				users.add(new User(id,name,password,emailid,phonenumber,address));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	
	}

	public void insertUser(com.ums.entity.User user) {
		System.out.println("UserDAO_insertUser called");
		try 
			(Connection con=getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS_SQL)){
				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getEmailid());
				preparedStatement.setString(4, user.getPhoneNumber());
				preparedStatement.setString(5, user.getAddress());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			}catch (SQLException e) 
			{
				printSQLException(e); 
			}
		}
			
		
	

		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
		
	




	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
		
	}




	public boolean updateUser(User user) throws SQLException {
		System.out.println("updateDao called");
		boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmailid());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getAddress());
            statement.setInt(6, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
		
	}




	public User selectUser(int id) {User user = null;
	System.out.println("selectUser---called");
	// Step 1: Establishing a Connection
	try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
		preparedStatement.setInt(1, id);
		System.out.println(preparedStatement);
		// Step 3: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();

		// Step 4: Process the ResultSet object.
		while (rs.next()) {
			String name = rs.getString("name");
			String password = rs.getString("password");
			String emailid = rs.getString("emailid");
			String phoneNumber = rs.getString("phoneNumber");
			String address = rs.getString("address");
			user = new User(id, name,password,emailid,phoneNumber,address);
		}
	} catch (SQLException e) {
		printSQLException(e);
	}
	return user;
}
		// TODO Auto-generated method stub
}