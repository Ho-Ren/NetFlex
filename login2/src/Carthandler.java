import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class Carthandler
 */
public class Carthandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carthandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String addToCart = request.getParameter("addtocart");
		String deleteFromCart = request.getParameter("deletefromcart");
		String updateCart = request.getParameter("updatecart");
		HttpSession session = request.getSession();
		cart cart = (cart) session.getAttribute("cartsession");
		//makes sure the list has MovieForCartName
		String moviename = request.getParameter("moviename");
		System.out.println("TITLE" + moviename);
		try {
			System.out.println("IN CARTHANDLER");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false","root", "5555");
	        ResultSet set = (ResultSet) Search.BrowseTitle(moviename, dbcon);
	        set.last();
	       
				if(addToCart != null)
				{
					System.out.println("BEFORE GETTING ALL RESULTS");
			        String id = set.getString(1);
			        String title = set.getString(2);
			        String year = set.getString(3);
			        String director = set.getString(4);
			        String pic = set.getString(5);
			        String trailer = set.getString(6);        
			        System.out.println("BEFORE MovieForCart SET");
			        MovieForCart m = new MovieForCart(id, title, year, director, pic, trailer);
			        System.out.println(id + " " + title + " " + year + " " + director + " " + pic +" " + trailer);
					System.out.println(m.getDirector());
					
					cart.addToCart(m);
				}
				else if(deleteFromCart != null)
				{
					 System.out.println("BEFORE GETTING ALL RESULTS");
			        String id = set.getString(1);
			        String title = set.getString(2);
			        String year = set.getString(3);
			        String director = set.getString(4);
			        String pic = set.getString(5);
			        String trailer = set.getString(6);        
			        System.out.println("BEFORE MovieForCart SET");
			        MovieForCart m = new MovieForCart(id, title, year, director, pic, trailer);
			        System.out.println(id + " " + title + " " + year + " " + director + " " + pic +" " + trailer);
					cart.deleteFromCart(m);
				}
				else if(updateCart != null)
				{
					System.out.println("BEFORE GETTING ALL RESULTS");
			        String id = set.getString(1);
			        String title = set.getString(2);
			        String year = set.getString(3);
			        String director = set.getString(4);
			        String pic = set.getString(5);
			        String trailer = set.getString(6);        
			        System.out.println("BEFORE MovieForCart SET");
			        MovieForCart m = new MovieForCart(id, title, year, director, pic, trailer);
			        System.out.println(id + " " + title + " " + year + " " + director + " " + pic +" " + trailer);
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					cart.updateQuantity(m, quantity);
				}
			session.setAttribute("cartsession", cart);
			
			cart updatedCart = (cart) session.getAttribute("cartsession");
			ArrayList<MovieForCart> movieList = new ArrayList<MovieForCart>();
			for(Entry<String, MovieForCart> entry : updatedCart.cart.entrySet())
			{
				System.out.println("entry: " + entry.getValue().getTitle());
				movieList.add(entry.getValue());
			}
//			System.out.println(movieList.get(0));
            request.setAttribute("movieList",movieList);
            RequestDispatcher rd=request.getRequestDispatcher("/cart.jsp");  
            rd.include(request,response); 
	        
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}