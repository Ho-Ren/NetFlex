package movieParse;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

import xmlParse.Globals;

public class MovieParser extends DefaultHandler {
	private ArrayList<PMovie> movies;
	private PMovie tempMov;
	private String tempStr;
	
	/* CONSTRUCTOR */
	MovieParser() {
		this.movies = new ArrayList<PMovie>();
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
		Iterator<PMovie> iter = movies.iterator();
		while(iter.hasNext())
			System.out.println(iter.next().toString());
	}
	
	/* PUBLIC METHODS */
	public void startElement(String uri, String localName, String qName, Attributes attr) {
		tempStr = "";
		
		if(qName.equalsIgnoreCase("film")) {
			tempMov = new PMovie();
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
		
		else if(qName.equalsIgnoreCase("year")){
			try {
        		
        		int year = Integer.parseInt(tempStr); 
        		tempMov.setYear( Integer.toString(year));
        	}
        	catch (NumberFormatException e ){
        		tempMov.setYear( "1900");
        		System.out.println("Invalid year, set year as null");
        	}
		}
			
		
		else if(qName.equalsIgnoreCase("cat")) {
			ArrayList<String> cat = tempMov.getCategories();
			cat.add(tempStr);
			tempMov.setCategories(cat);
		}
		
	}
	public void connectDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
			String query = "INSERT INTO movies (id,title,year,director) VALUES(?,?,?,?)";
			for (int i=0; i< movies.size();i++){
			 int movieid = getMovie(movies.get(i).getTitle(),movies.get(i).getYear(),dbcon);
			 	if(movieid==-1){
					movieid = getMaxId(dbcon,"movies") +1; 
					PreparedStatement pstmt2 = dbcon.prepareStatement(query);
					pstmt2.setInt(1, movieid);
					pstmt2.setString(2, movies.get(i).getTitle());
					pstmt2.setString(3,  movies.get(i).getYear());
					pstmt2.setString(4, movies.get(i).getDirector());
					pstmt2.executeUpdate();
				}
//				System.out.println(movies.get(i).toString());
				for(int j=0;j<movies.get(i).getCategories().size();j++){
					int genreId = getGenreID(movies.get(i).getCategories().get(j),dbcon);
					linkGenreMovie(movieid,genreId, dbcon);
				}
		   }
		        }
			
    	catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL Exception:  " + ex.getMessage());
				ex = ex.getNextException();
			} // end while
		} // end catch SQLException

		catch (java.lang.Exception ex) {
			System.out.println("Sql error: " + ex.getMessage());
			return;
		}
    }
	private static int getMaxId (Connection conn, String table) throws Exception{
		 int id = 0;
		 String query = "Select MAX(id) from " + table;
		 ResultSet result = conn.createStatement().executeQuery(query);

       while (result.next()){
      	 id = result.getInt(1);
       }
      return id;
	 }
	private static int getGenreID(String g, Connection con){
		try{
		String query = " call checkGenre (?, ?)";
        CallableStatement cstmt = con.prepareCall(query);
        cstmt.setString(1, g);
		cstmt.registerOutParameter(2,java.sql.Types.INTEGER);
		cstmt.executeUpdate();
		int result = cstmt.getInt(2);
		return result;
		}
		catch (java.lang.Exception ex) {
			System.out.println("Sql error in getGenreID: " + ex.getMessage());
			return -10;
		}
		
	}
	private static int getMovie(String title, String year, Connection con){
		try{
		String query = " call checkMovie (?, ?, ?)";
        CallableStatement cstmt = con.prepareCall(query);
        cstmt.setString(1, title);
        cstmt.setString(2, year);
		cstmt.registerOutParameter(3,java.sql.Types.INTEGER);
		cstmt.executeUpdate();
		int result = cstmt.getInt(3);
		return result;
		}
		catch (java.lang.Exception ex) {
			System.out.println("Sql error in getMovie: " + ex.getMessage());
			return -1;
		}
		
	}
	private static void linkGenreMovie(int movieId,int genreId, Connection con){
		try{
		String query = " call checkGenreInMovie (?, ?, ?)";
        CallableStatement cstmt = con.prepareCall(query);
        cstmt.setInt(1, genreId);
        cstmt.setInt(2, movieId);
		cstmt.registerOutParameter(3,java.sql.Types.INTEGER);
		cstmt.executeUpdate();
//		int result = cstmt.getInt(3);
//		if(result == 0)
//			System.out.println("link existed");
//		else
//			System.out.println("created link for: " + genreId);
		}
		catch (java.lang.Exception ex) {
		
			System.out.println("Sql error in CheckGenreInMove: " + ex.getMessage());
			return;
		}
		
	}
	public void run() {
		parseDocument();
//		printData();
		connectDB();
	}
	
	/* MAIN */
	public static void main(String[] args) {
		MovieParser mp = new MovieParser();
		mp.run();
	}
}







//for (int i=0; i< movies.size();i++){
//	 int movieid = getMovie(movies.get(i).getTitle(),movies.get(i).getYear(),dbcon);
//	 	if(movieid==-1){
//			movieid = getMaxId(dbcon,"movies") +1; 
//       	PreparedStatement pstmt2 = dbcon.prepareStatement(query);
//			pstmt2.setInt(1, movieid);
//			pstmt2.setString(2, movies.get(i).getTitle());
//			pstmt2.setString(3,  movies.get(i).getYear());
//			pstmt2.setString(4, movies.get(i).getDirector());
//			pstmt2.executeUpdate();
//		}
//		System.out.println(movies.get(i).toString());
//		for(int j=0;j<movies.get(i).getCategories().size();j++){
//			int genreId = getGenreID(movies.get(i).getCategories().get(j),dbcon);
//			linkGenreMovie(movieid,genreId, dbcon);
//		}
//   }


//for (int i=0; i< movies.size();i++){
//	 
//	PreparedStatement pstmt2 = dbcon.prepareStatement(query);
//	pstmt2.setInt(1, movieid);
//	pstmt2.setString(2, movies.get(i).getTitle());
//	pstmt2.setString(3,  movies.get(i).getYear());
//	pstmt2.setString(4, movies.get(i).getDirector());
//	pstmt2.executeUpdate();
//	System.out.println(movies.get(i).toString());
//	for(int j=0;j<movies.get(i).getCategories().size();j++){
//		int genreId = getGenreID(movies.get(i).getCategories().get(j),dbcon);
//		linkGenreMovie(movieid,genreId, dbcon);
//	}
//	movieid++;
//}