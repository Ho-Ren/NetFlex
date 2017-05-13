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

public class MoviePage extends HttpServlet{
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
        Movie item = null;
        PrintWriter out = response.getWriter();
	
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false","test", "1234");
            Statement statement = dbcon.createStatement();
            System.out.println("this is working");
            
            //get all the info of the movie (store in item-->item)
            String query = "select * from movies where id = " + id;
        	ResultSet result = statement.executeQuery(query);
                while(result.next())
                {	
                	String title = result.getString(2);
                	int year = result.getInt(3);
                	String director = result.getString(4);
                	String pic = result.getString(5);
                	String url = result.getString(6);
                
                	item = new Movie(id,title,year,director,pic,url);
                }
                request.setAttribute("item", item);
                
//            	System.out.print("at genre");
            	//get the list of genres that the movie is in (store in  genre --> genre)
                query = "select genres.name from genres inner join genres_in_movies on genres.id = genres_in_movies.genre_id where genres_in_movies.movie_id = " +id;
            	ResultSet resultGenre = statement.executeQuery(query);
            	ArrayList<String> genre = new ArrayList<String>();
            	while (resultGenre.next())
                 {	
                 	genre.add(resultGenre.getString(1));
                 }
            	 request.setAttribute("genre", genre);
            	 //get all the stars that are in the movie (store in: name --> star) 
            	query ="SELECT stars.first_name, stars.last_name, stars.id FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.star_id WHERE stars_in_movies.movie_id=" + id;
            	ResultSet resultStar = statement.executeQuery(query);
            	ArrayList<Star> name = new ArrayList<Star>();
            	 while(resultStar.next())
                 {	
                 	String fullName = resultStar.getString(1) + " " + resultStar.getString(2);
                 	String starId = resultStar.getString(3);
                 	
                 	name.add(new Star(starId, fullName));
                 }
            	request.setAttribute("star", name);
            	RequestDispatcher rd=request.getRequestDispatcher("../movieInfo.jsp");  
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


