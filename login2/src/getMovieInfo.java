import java.io.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
public class getMovieInfo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("this is new");
		String input = request.getParameter("title");
		System.out.println(input);
		ArrayList <Movie>list = new ArrayList<Movie> (); 
		ArrayList <String> starList = new ArrayList<String>();
		ArrayList <String> genreList = new ArrayList<String>();
		String movieId =null;
		String movieTitle = null, director = null, year = null, banner = null, trailer = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
			Statement st = dbcon.createStatement();
            System.out.println("i got ajax call from qtip");
			if(input.length()>3){
				String query = "select * from movies where title = '"+input+"'";
				ResultSet rs = st.executeQuery(query);
				while (rs.next()){
					movieId = rs.getString(1);
					movieTitle = rs.getString(2);
					year = rs.getString(3);
					director = rs.getString(4);
					banner = rs.getString(5);
					trailer = rs.getString(6);				
				}	
				query = "select genres.name from genres inner join genres_in_movies on genres.id = genres_in_movies.genre_id where genres_in_movies.movie_id = " +movieId;
            	ResultSet resultGenre = st.executeQuery(query);
            	while (resultGenre.next())
                 {	
                 	genreList.add(resultGenre.getString(1));
                 }
            	query ="SELECT stars.first_name, stars.last_name, stars.id FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.star_id WHERE stars_in_movies.movie_id=" + movieId;
            	ResultSet resultStar = st.executeQuery(query);
            	 while(resultStar.next())
                 {	
                 	String fullName = resultStar.getString(1) + " " + resultStar.getString(2);
                 	starList.add(fullName);
                 }
            	 list.add(new Movie (movieId, movieTitle, year, director, banner, trailer, genreList, starList));
			}
			String json = new Gson().toJson(list);
			System.out.print(json);
			response.setContentType("application/json");       //application/json
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			//request.setAttribute("li", list);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
