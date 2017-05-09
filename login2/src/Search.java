import java.io.IOException;

import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class Search{
	
	public static ResultSet advancedSearch(String title, String year, String director, String
			first, String last, Connection connection)
	{
        String query = "SELECT DISTINCT movies.title from movies, stars_in_movies, stars WHERE ";
        
        if(title != null)
        {
            query+= "movies.title = '" + title + "' AND ";
        }
        if(year != null)
        {
            query+= "movies.year = '" + year + "' AND ";
        }
        if(director != null)
        {
            query+= "movies.director = '" + director + "' AND ";
        }
        if(first != null)
        {
            query+= "stars.first_name= '" + first + "' AND ";	
        }
        if(first != null)
        {
        	query+= "stars.last_name= '" + last + "' AND ";
        }
        query+= "stars.id = stars_in_movies.star_id AND stars_in_movies.movie_id = movies.id;";
        
		ResultSet result = null;
		try
		{
			Statement statement = connection.createStatement();
		//	String query = "SELECT * FROM movies WHERE movies.title= '" + title + "'";
		//	System.out.println("query: " + query);
			result = statement.executeQuery(query);
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION ");
		}	
		return result;
	}
	
	public static ResultSet BrowseGenre(String genre, Connection connection)
	{
		ResultSet result = null;
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM movies, genres_in_movies, genres WHERE genres.name = '" + genre + 
					"'AND genres.id = genres_in_movies.genre_id AND genres_in_movies.movie_id = movies.id";
			System.out.println("query: " + query);
			result = statement.executeQuery(query);
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION ");
		}
		return result;
	}
	public static ResultSet BrowseTitle(String title, Connection connection)
	{
		ResultSet result = null;
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM movies WHERE movies.title= '" + title + "';";
			System.out.println("query: " + query);
			result = statement.executeQuery(query);
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION ");
		}
		return result;
	}
	
	
	public static String BrowseTitleString(String title)
	{
		String query = ""; 
		query = "SELECT * FROM movies WHERE movies.title= '" + title + "%';";
		System.out.println("query: " + query);
		return query;
	}

	public static String BrowseTitleGenre(String genre)
	{
		String query = ""; 
		query = "SELECT * FROM movies, genres_in_movies, genres WHERE genres.name = '" + genre + 
				"'AND genres.id = genres_in_movies.genre_id AND genres_in_movies.movie_id = movies.id;";
		return query;
	}
}
