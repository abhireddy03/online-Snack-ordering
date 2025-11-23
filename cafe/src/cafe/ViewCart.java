package cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/viewcart")
public class ViewCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession();
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

        pw.println("<!DOCTYPE html>");
        pw.println("<html><head><title>Your Cart</title>");

        pw.println("<style>");
        pw.println("body { background: #f2f2f2; font-family: Arial, sans-serif; margin: 0; padding: 0; }");
        pw.println(".container { max-width: 600px; margin: 40px auto; background: white; padding: 20px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }");
        pw.println("h2 { text-align: center; margin-bottom: 20px; color: #333; }");
        pw.println(".item-box { display: flex; justify-content: space-between; align-items: center; padding: 12px 0; border-bottom: 1px solid #ddd; }");
        pw.println(".item-name { font-size: 18px; color: #444; }");
        pw.println(".remove-btn { background: #ff4d4d; color: white; padding: 6px 12px; border-radius: 5px; text-decoration: none; font-size: 14px; }");
        pw.println(".remove-btn:hover { background: #e60000; }");
        pw.println(".total-box { text-align: right; font-size: 22px; margin-top: 20px; font-weight: bold; color: #333; }");
        pw.println(".back-btn { display: block; width: 180px; margin: 30px auto 0; text-align: center; padding: 12px; background: #0066ff; color: white; text-decoration: none; border-radius: 6px; font-size: 16px; }");
        pw.println(".back-btn:hover { background: #0047b3; }");
        pw.println(".orderbutton{height:40px; width:200px; background:blue;color:white; border:none; border-radius:5px;margin-left:200px; font-size:18px; }");
        pw.println("</style>");

        pw.println("</head><body>");
        pw.println("<div class='container'>");
        pw.println("<h2>Your Cart</h2>");

        if (cart == null || cart.size() == 0) {
            pw.println("<p>Your cart is empty!</p>");
        } else {

            int total = 0;

            for (int i = 0; i < cart.size(); i++) {

                String item = cart.get(i);    // Example: "Burger - ₹100"
                String name = item.split(" - ")[0];
                String priceStr = item.split("Rs.")[1];
                int price = Integer.parseInt(priceStr);
                total += price;

                pw.println("<div class='item-box'>");
                pw.println("<span class='item-name'>" + name + "</span>");
                pw.println("<span class='item-price'>Rs." + price + "</span>");
                pw.println("<a class='remove-btn' href='removeFromCart?index=" + i + "'>Remove</a>");
                pw.println("</div>");
            }

            pw.println("<div class='total-box'>Total: Rs." + total + "</div>");
        }

        pw.println("<a class='back-btn' href='menu.html'>Back to Menu</a><br>");
        pw.print("<form action='order'><button class='orderbutton'>Order Now</button></form>");
        pw.println("</div></body></html>");
    }
}

