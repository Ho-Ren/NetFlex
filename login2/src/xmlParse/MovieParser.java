package xmlParse;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;


public class MovieParser extends DefaultHandler {
	private ArrayList<PMovies> movies;
	private PMovies tempMov;
	private String tempStr;
	private HashSet<String> categories;
	
	/* CONSTRUCTOR */
	MovieParser() {
		this.movies = new ArrayList<PMovies>();
	}
	
	/* PRIVATE METHODS*/
	private void parseDocument() {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		
		try {
			SAXParser sp = spf.newSAXParser();
			sp.parse("mains243.xml", this);
			
		} catch(SAXException se) {
			se.printStackTrace();
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
	private void printData() {
		System.out.println("Number of Movies: " + movies.size());
		Iterator<PMovies> iter = movies.iterator();
		while(iter.hasNext())
			System.out.println(iter.next().toString());
	}
	
	/* PUBLIC METHODS */
	public void startElement(String uri, String localName, String qName, Attributes attr) {
		tempStr = "";
		
		if(qName.equalsIgnoreCase("film")) {
			tempMov = new PMovies();
			tempMov.setType(attr.getValue("type"));
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempStr = new String(ch, start, length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("film"))
			movies.add(tempMov);
		
		else if(qName.equalsIgnoreCase("dirn"))
			tempMov.setDirector(tempStr);
		
		else if(qName.equalsIgnoreCase("t"))
			tempMov.setTitle(tempStr);
		
		else if(qName.equalsIgnoreCase("year"))
			tempMov.setYear(tempStr);
		
		else if(qName.equalsIgnoreCase("cat")) {
			ArrayList<String> cat = tempMov.getCategories();
			cat.add(tempStr);
			
			// Checking for duplicates (so categories can be added into main category list):
			if(!categories.contains(tempStr))
				categories.add(tempStr);
			
			tempMov.setCategories(cat);
		}
		
	}
	
	public void run() {
		parseDocument();
		printData();
	}
	
	// Update the Database:
	public void updateDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
			Statement statement = dbcon.createStatement();
			String insertMovie = "INSERT INTO Movies (id, title, year, director) VALUES (?, ?, ?, ?)";
			String insertGenMov = "INSERT INTO genres_in_movies(genre_id, movie_id) VALUES (?,?)";
			String getMov = "SELECT title FROM movies";
			ResultSet mov = (ResultSet) statement.executeQuery(getMov);
			String callable = "CALL checkMovie(?, ?, ?)";
			
			// Checking for duplicate Movies:
			for(int i = 0; i < movies.size(); i++) {
				CallableStatement cs = dbcon.prepareCall(callable);
				cs.setString(1, movies.get(i).getTitle());
				cs.setString(2, movies.get(i).getYear());
				cs.registerOutParameter(3, java.sql.Types.INTEGER);
				cs.executeUpdate();
				
				int result = cs.getInt(7);
				if(result != -1) { // Duplicate is found
					continue;
				} else {
				
					// Confirmed the movie isn't a duplicate
					PreparedStatement pstate = dbcon.prepareStatement(insertMovie);
					pstate.setString(2, movies.get(i).getTitle());
					pstate.setString(3, movies.get(i).getYear());
					pstate.setString(4, movies.get(i).getDirector());
					
					// Trying to decipher abbreviated categories (genres)
					for(String c : movies.get(i).getCategories()) {
						System.out.println(c);
						
						// Query could be questionable
						String getGenre = "SELECT * FROM genres g, genres_in_movies gm, movies m WHERE m.id = gm.movie_id AND m.title = " 
										+ movies.get(i).getTitle() 
										+ " AND g.name LIKE "
										+ c 
										+ "%";
						
						// Adding to genres_in_movies table
						PreparedStatement cState = dbcon.prepareStatement(insertGenMov);
						ResultSet gm = (ResultSet) statement.executeQuery(getGenre);
						cState.setInt(1, gm.getInt("genre_id"));
						cState.setInt(2, gm.getInt("movie_id"));
						cState.executeQuery();	
					}
					
					pstate.executeQuery();
				}
			}
			
			
			
		} catch(SQLException ex) {
			while (ex != null) {
				System.out.println("SQL Exception:  " + ex.getMessage());
				ex = ex.getNextException();
			}
			
		} catch(java.lang.Exception ex) {
			System.out.println("Sql error: " + ex.getMessage());
			return;
		}
	}
	
	/* MAIN */
	public static void main(String[] args) {
		MovieParser mp = new MovieParser();
		mp.run();
	}
	
}

