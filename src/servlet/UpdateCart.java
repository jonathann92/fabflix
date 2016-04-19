package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Movie;
import objects.MutableInt;
import services.Service;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/UpdateCart")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String context = request.getContextPath();

        String clear = request.getParameter("clear");        	
        String removeParam = request.getParameter("remove");
        String error = "";
                
        Map<Movie, MutableInt> cart = (Map<Movie, MutableInt>) session.getAttribute("cart");
        if(cart == null){
        	cart = new HashMap<Movie, MutableInt>();
        }
        
        if(clear != null && clear.equals("true"))
        	cart.clear();
        else if(request.getParameter("remove") != null && request.getParameter("remove").length() > 0)
        	error += removeMovie(request, cart);
        else if(request.getParameter("update") != null && 
        		request.getParameter("update").length() > 0)
        {
	        error += updateCart(cart, request);
			removeZeros(cart);
        }
        
        request.setAttribute("cart", cart);
        session.setAttribute("cart", cart);
        session.setAttribute("error", error);
        
        response.sendRedirect(context + "/cart.jsp");
        
        //Service.forward(request, response, "cart.jsp");

	}

	private String removeMovie(HttpServletRequest request, Map<Movie, MutableInt> cart) {
		String movieId = request.getParameter("remove");
		try{
			int id = Integer.parseInt(movieId);
			Movie toRemove = new Movie(id, "remove");
			cart.remove(toRemove);
		} catch (Exception e){
			return "Parameter to remove is not a number";
		}
		return "";
	}

	protected void removeZeros(Map<Movie, MutableInt> cart) {
		for(Iterator<Map.Entry<Movie, MutableInt>> it = cart.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Movie, MutableInt> entry = it.next();
			if(entry.getValue().get() < 1)
				it.remove();
		}
	}
	
	protected String updateCart(Map<Movie, MutableInt> cart, HttpServletRequest request) {
		String updateParam = request.getParameter("update");
		String quantityParam = request.getParameter("quantity");
		int id = -1;
		int quantity = 0;
		
		try{
			id = Integer.parseInt(updateParam);
		} catch (Exception e){
			return "Invalid movie id: " + updateParam;
		}
		
		try{
			quantity = Integer.parseInt(quantityParam);
		} catch (Exception e) {
			return "Invalid quantity : " + quantityParam;
		}

		Movie m = new Movie(id, "update");
		MutableInt quantityInCart = cart.get(m);

		quantityInCart.set(quantity);
		
		return "";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
