

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

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
        String order = request.getParameter("order");
		if (order == null || order.equals("")){
			order = "asc";
		}
		int perPage;
		if (request.getParameter("perPage") == null || request.getParameter("perPage").equals(""))
			 perPage = 10;
		else
			perPage = Integer.parseInt(request.getParameter("perPage"));
		
		String sort = request.getParameter("sort");
		if (sort == null || sort.equals("")){
			sort = "title";
		}
		
        
        if(request.getParameter("Title") != null || request.getParameter("Director") != null)
        {
    		title = request.getParameter("Title");
    		title+="%";
    		year = request.getParameter("Year");
    		if (year == null)
    			year ="";
    		director = request.getParameter("Director");
    		if (director == null){
    			director ="";
    		}
    		lastname = request.getParameter("Lastname");
    		if (lastname == null){
    			lastname ="";
    		}
    		firstname = request.getParameter("Firstname");
    		if(firstname == null){
    			firstname ="";
    		}
        }
       
		System.out.println("title: " + title +" First: " + firstname + " Last:" + lastname + " Director:" + director);
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
            Statement statement = dbcon.createStatement();
            System.out.println("this is not working");
            String query = "SELECT * from movies, stars_in_movies, stars WHERE ";
            
            if(!title.equals(""))
            {
                query+= "movies.title like '" + title + "' AND ";
            }
            if(!year.equals(""))
            {
                query+= "movies.year = '" + year + "' AND ";
            }
            if(!director.equals(""))
            {
                query+= "movies.director = '" + director + "' AND ";
            }
            
            if(!firstname.equals(""))
            {
                query+= "stars.first_name= '" + firstname + "' AND ";	
            }
            if(!lastname.equals(""))
            {
            	query+= "stars.last_name= '" + lastname + "' AND ";
            }
            
            
            query+= "stars.id = stars_in_movies.star_id AND stars_in_movies.movie_id = movies.id group by movies.title order by " + sort + " " + order;  //+" limit 1, " +perPage
         
            System.out.println(query);
            System.out.println("perPage" + perPage);
            ResultSet result = statement.executeQuery(query);
			result.last();
			int total = result.getRow();
			System.out.println("totalRow: " + total);
			int pageNum = total <= perPage ? 0 :(int)(Math.ceil((double)result.getRow() / perPage));
			System.out.println("pageNum: " + pageNum);

			// get current page and set limit and offset query is based on the parameter again
			int offset;
			System.out.println(request.getParameter("page"));
			if (request.getParameter("page") == null) {
				offset = 0;
			} else {
				offset = (Integer.parseInt(request.getParameter("page"))-1) * perPage ;
			}
			System.out.println("offset: " + offset);

			query +=  " limit " + offset + ", " + perPage;
            result = statement.executeQuery(query);
            ArrayList<Movie> movieList = new ArrayList<Movie>();
            while(result.next())
            {	
            	int id = result.getInt("id");
				String title2 = result.getString("title");
				int year2 = result.getInt("year");
				String director2 = result.getString("director");
				String pic = result.getString("banner_url");
				String url = result.getString("trailer_url");
				ArrayList<Star> starList = new ArrayList<Star>();
				ArrayList<String> genreList2 = new ArrayList<String>();
				
				String query3 = "select genres.name from genres inner join genres_in_movies on genres.id = genres_in_movies.genre_id where genres_in_movies.movie_id = " +id ;
				java.sql.PreparedStatement stmt2 = dbcon.prepareStatement(query3);
				ResultSet resultGenre = stmt2.executeQuery();
				
				while (resultGenre.next())
                 {	
                 	genreList2.add(resultGenre.getString(1));
                 }
				// For adding stars, genres to a new Movie() object:
            	query3 ="SELECT stars.first_name, stars.last_name, stars.id FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.star_id WHERE stars_in_movies.movie_id= " +id;
            	java.sql.PreparedStatement stmt3 = dbcon.prepareStatement(query3);
			/*	stmt3.setInt(1, id);*/	
            	ResultSet resultStar = stmt3.executeQuery(query3);
            	 while(resultStar.next())
                 {	
            		String starid = resultStar.getString(3);
                 	String fullName = resultStar.getString(1) + " " + resultStar.getString(2);	                 	
                 	starList.add(new Star(starid, fullName));
                 }


				movieList.add(new Movie(id,title2,year2, director2, pic, url, genreList2, starList));
            }
            request.setAttribute("movieList",movieList);
            request.setAttribute("Title",title);
            request.setAttribute("Year",year);
            request.setAttribute("Director",director);
            request.setAttribute("Lastname",firstname);
            request.setAttribute("Firstname",lastname);
            request.setAttribute("pageNum", pageNum);
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
