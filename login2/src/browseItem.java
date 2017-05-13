import java.io.IOException;
import java.util.HashMap;
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

public class browseItem extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getServletInfo() {
		return "Servlet connects to MySQL database and displays result of a SELECT";
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("param");
		String genreName = request.getParameter("genreName");
		String alpha = request.getParameter("alpha");
		int perPage;
		String order = request.getParameter("order");
		if (order == null){
			order = "asc";
		}
		if (request.getParameter("perPage") == null)
			perPage = 10;
		else
			perPage = Integer.parseInt(request.getParameter("perPage"));
		if (type.equals("title")){
			genreName = null;
		}
		String sort = request.getParameter("sort");
		if (sort == null){
			sort = "title";
		}
		

		PrintWriter out = response.getWriter();
		int pageNum;
		String value;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false","test", "1234");
			Statement statement = dbcon.createStatement();
			String query;
			System.out.println("this is working(connection)");
	       
			System.out.println("genreName: " + genreName);
			ArrayList<String> genreList = new ArrayList<String>();

			if (type.equals("genre") && genreName == null) {
				query = "Select name from genres order by name asc";
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {
					genreList.add(result.getString(1));
				}

				request.setAttribute("type", "genre");
				request.setAttribute("list", genreList);
				RequestDispatcher rd = request.getRequestDispatcher("../browseGenre.jsp");
				rd.include(request, response);

			} else {
				if (genreName != null){
					//create query for browse by genre to get number of pages for pagination
					query = "select * from genres_in_movies inner join movies on movies.id = genres_in_movies.movie_id and genres_in_movies.genre_id = (select id from genres where name = '"
							+ genreName + "') ";
					value ="genre";
					}
				else{
					//create query for browse by title to get number of pages for pagination
					query = "select * from movies where movies.title LIKE '"+alpha +"%'";
					value ="title";
					}
				// get number of rows in the list
				ResultSet result = statement.executeQuery(query);
				result.last();
				int total = result.getRow();
				System.out.println("total page: " + total);
				pageNum = (total <= perPage ? 0 : (int)(Math.ceil((double)result.getRow() / perPage)));
				System.out.println("pageNum: " + pageNum);

				// get current page and set limit and offset query is based on the parameter again
				int offset;
				System.out.println(request.getParameter("page"));
				if (request.getParameter("page") == null) {
					offset = 0;
				} else {
					offset = (Integer.parseInt(request.getParameter("page"))-1) * perPage;
				}

				System.out.println("page: " + offset);
				if (genreName != null){   //create query for browse by genre  //select id from genres where genres.name = '" + genreName +"'
					query = "select * from genres_in_movies inner join movies on movies.id = genres_in_movies.movie_id and genres_in_movies.genre_id = (select id from genres where name = '"
							+ genreName + "') order by " + sort + " " + order+" limit " + offset + ", " + perPage;;
					
				}          
				else                     //create query for browse by title
					query = "select *  from movies  where movies.title like '" +alpha+"%' order by " + sort + " " + order+" limit " + offset + ", " + perPage;
				Statement statement2 = dbcon.createStatement();
				System.out.println("created second statement");
				ResultSet result2 = statement2.executeQuery(query);
				System.out.print("I got the result");
				ArrayList <Movie> movieList = new ArrayList<Movie>();
				
				while (result2.next()) {
					
					int id = result2.getInt("id");
					String title = result2.getString("title");
					int year = result2.getInt("year");
					String director = result2.getString("director");
					String pic = result2.getString("banner_url");
					String url = result2.getString("trailer_url");
					ArrayList<Star> starList = new ArrayList<Star>();
					ArrayList<String> genreList2 = new ArrayList<String>();
					
					String query3 = "select genres.name from genres inner join genres_in_movies on genres.id = genres_in_movies.genre_id where genres_in_movies.movie_id = " +id;
					Statement stmt2 = dbcon.createStatement();
					ResultSet resultGenre = stmt2.executeQuery(query3);
					
					while (resultGenre.next())
	                 {	
	                 	genreList2.add(resultGenre.getString(1));
	                 }
					// For adding stars, genres to a new Movie() object:
	            	query3 ="SELECT stars.first_name, stars.last_name, stars.id FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.star_id WHERE stars_in_movies.movie_id=" + id;
	            	ResultSet resultStar = statement.executeQuery(query3);
	            	 while(resultStar.next())
	                 {	
	            		String starid = resultStar.getString(3);
	                 	String fullName = resultStar.getString(1) + " " + resultStar.getString(2);	                 	
	                 	starList.add(new Star(starid, fullName));
	                 }

					movieList.add(new Movie(id,title,year, director, pic, url, genreList2, starList));
			
				}
				
				request.setAttribute("genreName", genreName);
				request.setAttribute("value", value);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("perPage", perPage);
				request.setAttribute("type", "title");
				request.setAttribute("list2", movieList);
				request.setAttribute("sort", sort);
				request.setAttribute("order", order);
				request.setAttribute("alpha", alpha);
				RequestDispatcher rd = request.getRequestDispatcher("../browse.jsp");
				rd.include(request, response);
			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL Exception:  " + ex.getMessage());
				ex = ex.getNextException();
			} // end while
		} // end catch SQLException

		catch (java.lang.Exception ex) {
			out.println("<HTML>" + "<HEAD><TITLE>" + "MovieDB: Error" + "</TITLE></HEAD>\n<BODY>"
					+ "<P>SQL error in doGet: " + ex.getMessage() + "</P></BODY></HTML>");
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

