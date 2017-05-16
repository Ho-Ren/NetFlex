package xmlParse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

import com.mysql.jdbc.ResultSet;

public class Castsparse extends DefaultHandler {

    List<StarsInMovies> myStars;

    private String tempVal;

    //to maintain context
    private StarsInMovies tempStar;

    public Castsparse() {
        myStars = new ArrayList<StarsInMovies>();
    }

    public void runExample() {
        parseDocument();
        //printData();
        connectDB();
    }

    private void parseDocument() {

        //get a factory
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {

            //get a new instance of parser
            SAXParser sp = spf.newSAXParser();

            //parse the file and also register this class for call backs
            sp.parse("casts124.xml", this);

        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Iterate through the list and print
     * the contents
     */
    private void printData() {

        System.out.println("No of StarsInMovies '" + myStars.size() + "'.");

        Iterator<StarsInMovies> it = myStars.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    //Event Handlers
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //reset
        tempVal = "";
        if (qName.equalsIgnoreCase("m")) {
            //create a new instance of employee
        	tempStar = new StarsInMovies();
        }
        
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("m")) {
            //add it to the list
            myStars.add(tempStar);
        } else if (qName.equalsIgnoreCase("t")) {
        	//System.out.println(tempVal);
            tempStar.setMovieTitle(tempVal);
        } else if (qName.equalsIgnoreCase("a")) {
        	//System.out.println(tempVal);
        	if(tempVal.contains(" "))
        	{
            	String str = tempVal;
            	String[] splited = str.split("\\s+", 2);
            	tempStar.setFirstName(splited[0]);
            	tempStar.setLastName(splited[1]);
        	}
        	else
        	{
        		tempStar.setLastName(tempVal);
        	}
        }
    }
    
    public void connectDB()
    {
    	try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
			Statement statement = dbcon.createStatement();
			String query = "INSERT INTO stars_in_movies (star_id, movie_id) VALUES(?,?)";
			 for (int i=0; i< myStars.size();i++)
			 {
		    		int mID = -1;
		    		int sID = -1;
		    		String title = myStars.get(i).getMovieTitle();
		    		String firstName = myStars.get(i).getFirstName();
		    		String lastName = myStars.get(i).getLastName();
		    		
		        	System.out.println("INITIAL movie: " + title);
		    		if(title != null && (title.contains("'") || title.contains("\\")))
		    		{
		    			System.out.println("IN IF");
		    			title = title.replace("\'", "''");
		    			title = title.replace("\\", " ");
		    		}
		    		
		    		if(firstName != null && firstName.contains("'") || firstName!= null && firstName.contains("\\"))
		    		{
		    			System.out.println("IN IFirst");
		    			firstName = firstName.replace("'", "''");
		    			firstName = firstName.replace("\\", " ");

		    		}
		    		if(lastName != null && lastName.contains("'") || lastName!= null && lastName.contains("\\"))
		    		{
		    			System.out.println("IN IFlast");
		    			lastName = lastName.replace("'", "''");
		    			lastName = lastName.replace("\\", " ");

		    		}
		    		
					String getMovieIDQuery = "SELECT * from movies where movies.title = '" + title + "'";
		        	System.out.println("movie: " + title);
		        	ResultSet movieSet = (ResultSet) statement.executeQuery(getMovieIDQuery);
		        	if(movieSet.first())
		        	{
		        		mID = movieSet.getInt("id");
		        		System.out.println("mID: " + mID);
		        	}
		        	
		        	System.out.println("star first : " + firstName + "star last: " + lastName);
					String getStarIDQuery = "SELECT * from stars where stars.first_name = '" + firstName +
							"' and stars.last_name = '" + lastName + "'";
		        	ResultSet starSet = (ResultSet) statement.executeQuery(getStarIDQuery);
		        	if(starSet.isFirst())
		        	{
		        		sID = starSet.getInt("id");
		        		System.out.println("sID: " + sID);
		        	}
		        	
		        	if(sID == -1 || mID == -1)
		        	{
		        		continue;
		        	}
		        	
		        	PreparedStatement pstmt2 = dbcon.prepareStatement(query);
		        	System.out.println(myStars.get(i).toString());
					pstmt2.setInt(1, mID);
					pstmt2.setInt(2, sID);
					pstmt2.executeUpdate();
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

    public static void main(String[] args) {
        Castsparse spe = new Castsparse();
        spe.runExample();
    }

}
