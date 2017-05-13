import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StarPage extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public String getServletInfo()
    {
       return "Servlet connects to MySQL database and displays result of a SELECT";
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
        int id = Integer.parseInt(request.getParameter("id"));
        Star star = null;
        PrintWriter out = response.getWriter();
	
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
            Statement statement = dbcon.createStatement();
            System.out.println("this is working");
            
            //get all the info of the star (store in star-->star)
            String query = "select * from stars where id = " + id;
        	ResultSet result = statement.executeQuery(query);
                while(result.next())
                {	
                	String id2 = result.getString(1);
                	String first = result.getString(2);
                	String last = result.getString(3);
                	String DOB = result.getString(4);
                	String pic = result.getString(5);
                
                	star = new Star(id2,first,last,DOB,pic);
                }
                request.setAttribute("star", star);
                
//            	System.out.print("at genre");
            	//get the list of movie that the star is in (store in  movieList --> movieList)
                query = "select * from stars_in_movies inner join movies on stars_in_movies.movie_id = movies.id and stars_in_movies.star_id= " +id;
            	ResultSet resultMovie = statement.executeQuery(query);
            	ArrayList<Movie> movieList = new ArrayList<Movie>();
            	while (resultMovie.next())
                 {	
            		int id3 = resultMovie.getInt("id");
            		String title = resultMovie.getString("title");
            		int year = resultMovie.getInt("year");
            		String dir = resultMovie.getString("director");
            		String pic = resultMovie.getString(7);
            		String trail = resultMovie.getString(8);
                 	movieList.add(new Movie (id3, title,year,dir,pic, trail));
                 }
            	 request.setAttribute("movieList", movieList);
            	RequestDispatcher rd=request.getRequestDispatcher("../starInfo.jsp");  
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


