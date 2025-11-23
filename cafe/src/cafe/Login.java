package cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet  {
       @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String email=req.getParameter("e1");
    	String password=req.getParameter("e2");
    	  try {
     			Class.forName("com.mysql.cj.jdbc.Driver");
     			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe?user=root&&password=root");
     			
     			PreparedStatement ps =con.prepareStatement("select * from cafeusers where email=? and password=?");
     			ps.setString(1,email);
     			ps.setString(2, password);
     			ResultSet rs=ps.executeQuery();
     			System.out.println(email);
     			System.out.println(password);
     			
     			if(rs.next()) {
     				if(rs.getString(2).equals(email) && rs.getString(3).equals(password)) {
     				System.out.println("loggined successfully");
     				RequestDispatcher rd =req.getRequestDispatcher("menu.html");
     				rd.forward(req, resp);
     			}
     			}
     			else {
     			    PrintWriter out = resp.getWriter();
     			    out.println("<script>");
     			    out.println("alert('Invalid details');");
     			    out.println("location='index.html';");
     			    out.println("</script>");
     			}
    	  }
    	catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
    	
    	
    	
    	
    	
    	
    	
    	
    }
}
