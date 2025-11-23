package cafe;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/removeFromCart")
public class Removeitems extends HttpServlet {
	
	

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        HttpSession session = req.getSession();
	        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

	        int index = Integer.parseInt(req.getParameter("index"));

	        // Remove the item
	        cart.remove(index);

	        session.setAttribute("cart", cart);

	        // Redirect back to cart page
	        resp.sendRedirect("viewcart");
	    }
	}

