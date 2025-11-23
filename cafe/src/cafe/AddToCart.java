package cafe;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));

        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

        if (cart == null) { 
        	
        cart = new ArrayList<>();
        }
       cart.add(name + "  Rs." + price);

        session.setAttribute("cart", cart);
        System.out.println(cart);
        
        resp.sendRedirect("menu.html"); 
    }
}
