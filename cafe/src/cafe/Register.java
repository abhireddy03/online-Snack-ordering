package cafe;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register  extends HttpServlet{
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String name=req.getParameter("name");
    	String email=req.getParameter("email");
    	String password=req.getParameter("password");
    	long phoneno=Long.parseLong(req.getParameter("phno"));
    	   try {
   			Class.forName("com.mysql.cj.jdbc.Driver");
   			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe?user=root&&password=root");
   			String s="insert into cafeusers values(?,?,?,?)";
   			PreparedStatement ps=con.prepareStatement(s);
   			ps.setString(1,name);
   			ps.setString(2,email);
   			ps.setString(3,password );
   			ps.setLong(4,phoneno);
   			ps.executeUpdate();
   			RequestDispatcher rd=req.getRequestDispatcher("index.html");
    	     rd.forward(req, resp);
    	   }
    	   catch (Exception e) {
			// TODO: handle exception
		}
    	   }
    	
    
     
}
