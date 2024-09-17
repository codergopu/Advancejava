package log.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Employeeadd
 */
@WebServlet("/Employeeadd")
public class Employeeadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employeeadd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int employee_id=Integer.parseInt(request.getParameter("employee_id"));
		String employee_name=request.getParameter("employee_name");
		String employee_place=request.getParameter("employee_place");
		String employee_Address=request.getParameter("employee_address");
		String username="root";
		String password="root";
		String url="jdbc:mysql://localhost:3306/vtech";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, username, password);
			ResultSet result=con.prepareCall(sql);
			int id=result.getInt("employee_id");
			String name=result.getNString("employee_name");
			String place=result.getNString("employee_place");
			String address=result.getNString("employee_Address");
			String sql="insert into employee values(?,?,?,?)";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt(1,employee_id);
			stmt.setString(2, employee_name);
			stmt.setString(3, employee_place);
			stmt.setString(4, employee_Address);
			int data=stmt.executeUpdate();
			PrintWriter out=response.getWriter();
			if(data>0)
			{
				out.print("record added");
			}
			else {
				out.print("error");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
