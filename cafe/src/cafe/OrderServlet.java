package cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

        // If cart empty
        if (cart == null || cart.isEmpty()) {
            out.println("<h3>Your cart is empty!</h3>");
            return;
        }

        int total = 0;
        String itemsList = "";   // Using String instead of StringBuilder

        // Build items and total price
        for (String item : cart) {
            itemsList = itemsList + item + ", ";

            // item format →  "Burger - Rs.100"
            String priceStr = item.split("Rs.")[1];
            total += Integer.parseInt(priceStr);
        }

        String items = itemsList;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cafe","root","root");

            // Insert into database
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO orderitems (items, total) VALUES (?, ?)"
            );

            ps.setString(1, items);
            ps.setInt(2, total);

            ps.executeUpdate();

            // Clear cart after order
            session.removeAttribute("cart");

            resp.sendRedirect("order-success.html?total=" + total);


        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error while placing order!</h3>");
        }
    }
}
