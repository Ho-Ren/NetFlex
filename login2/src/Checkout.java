import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class Checkout
 */
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try
         {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
            
            //Get all information and check if cc info is correct.
            Statement statement = dbcon.createStatement();
            String firstname = request.getParameter("Firstname");
            String lastname = request.getParameter("Lastname");
            String cc = request.getParameter("Creditcard");
            String expiration = request.getParameter("Expiration");
            
            String query = "SELECT * FROM creditcards where id = '" + cc + "' and first_name = '" + firstname
            + "' and last_name = '" + lastname + "' and expiration = '" + expiration + "';";
            ResultSet rm = statement.executeQuery(query);
            rm.last();
            int count = rm.getRow();
            //If no rows at all, then cc info incorrect
            
            if (count !=1)
            {
                RequestDispatcher rd=request.getRequestDispatcher("/checkouterror.jsp");  
                rd.include(request,response);  
            }
            else
            {	
	            // Perform the query and insert record into sales
	            String insertQuery = "insert into sales (customer_id, movie_id, sale_date)" + " values (?, ?, ?)";
	            HttpSession session = request.getSession(false);
	            cart cart = (cart) session.getAttribute("cartsession");
	            int customerID = (int) session.getAttribute("customerID");
	              System.out.println("customer ID:" + customerID);

	            PreparedStatement sales = dbcon.prepareStatement(insertQuery);
	            Calendar calendar = Calendar.getInstance();
	            java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
            
	            //Adds all MovieForCarts to sales record
	            System.out.println("BEFORE INSERTING SALES");
	            for(Map.Entry<String, MovieForCart> entry : cart.getMovieForCarts().entrySet())
	            {
	            	System.out.println("MovieForCart_ID: " + entry.getValue().getId());
	            	int numSales = entry.getValue().getQuantity();
	            	for(; numSales > 0; numSales--)
	            	{
	            		sales.setInt(1, customerID);
	            		sales.setInt(2, Integer.parseInt(entry.getValue().getID()));
	            		sales.setDate(3, currentDate);
	            		sales.execute();
	            	}
	            }
	            cart.emptyCart();
	            RequestDispatcher rd = request.getRequestDispatcher("/checkoutsuccess.jsp");  
	            rd.include(request,response);
            }
            statement.close();
            dbcon.close();     
          }
      catch (SQLException ex) {
            while (ex != null) {
                  System.out.println ("SQL Exception:  " + ex.getMessage ());
                  ex = ex.getNextException ();
              }  // end while
          }  // end catch SQLException
      catch(java.lang.Exception ex)
          {	
              return;
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