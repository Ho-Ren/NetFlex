

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

/**
 * Servlet implementation class Results
 */
public class Results extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Results() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String title, year, director, firstname, lastname;
        title = "";
        year = "";
        director = "";
        firstname = "";
        lastname = "";
        
        PrintWriter out = response.getWriter();

//        out.println("<HTML><HEAD><TITLE>MovieDB</TITLE></HEAD>");
//        out.println("<BODY><H1>Welcome IDJIT</H1>");
        
        if(request.getParameter("Title") != null && request.getParameter("Director") != null)
        {
    		title = request.getParameter("Title");
    		year = request.getParameter("Year");
    		director = request.getParameter("Director");
    		lastname = request.getParameter("Lastname");
    		firstname = request.getParameter("Firstname");
        }
		
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false","root", "");
            Statement statement = dbcon.createStatement();
            System.out.println("this is not working");
            String query = "SELECT DISTINCT movies.title from movies, stars_in_movies, stars WHERE ";
            
            if(title.compareTo("")!=0)
            {
                query+= "movies.title = '" + title + "' AND ";
            }
            if(year.compareTo("")!=0)
            {
                query+= "movies.year = '" + year + "' AND ";
            }
            if(director.compareTo("")!=0)
            {
                query+= "movies.director = '" + director + "' AND ";
            }
            
            if(firstname.compareTo("")!=0)
            {
                query+= "stars.first_name= '" + firstname + "' AND ";	
            }
            if(firstname.compareTo("")!=0)
            {
            	query+= "stars.last_name= '" + lastname + "' AND ";
            }
            
            query+= "stars.id = stars_in_movies.star_id AND stars_in_movies.movie_id = movies.id;";
            if(!dbcon.isClosed() || dbcon!=null){
            	System.out.println("this is not close");
            }
            System.out.println(query);
            ResultSet re = statement.executeQuery(query);
            re.last();
            int count = re.getRow();
            System.out.println("cuurent count: " + count);
            ResultSet result = statement.executeQuery(query);
            ArrayList<String> movieList = new ArrayList<String>();
            while(result.next())
            {	
            	System.out.println(result.getString(1));
            	movieList.add(result.getString(1));
            }
            request.setAttribute("movieList",movieList);
            RequestDispatcher rd=request.getRequestDispatcher("../movieList.jsp");  
            rd.include(request,response); 
        }
        catch (SQLException ex) {
            while (ex != null) {
                  System.out.println ("SQL Exception:  " + ex.getMessage ());
                  ex = ex.getNextException ();
              }  // end while
          }  // end catch SQLException

      catch(java.lang.Exception ex)
          {	
              out.println("<HTML>" +
                          "<HEAD><TITLE>" +
                          "MovieDB: Error" +
                          "</TITLE></HEAD>\n<BODY>" +
                          "<P>SQL error in doGet: " +
                          ex.getMessage() + "</P></BODY></HTML>");
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
