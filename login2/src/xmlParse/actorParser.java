package xmlParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class actorParser extends DefaultHandler{
	List<Actor> actorList;
	private String tempVal;
	Actor actTemp;
	public actorParser(){
		actorList = new ArrayList<Actor>();
	}
	
	 public void runExample() {
	        parseDocument();
//	        printData();
	        connectDB();
	    }

	    private void parseDocument() {

	        //get a factory
	        SAXParserFactory spf = SAXParserFactory.newInstance();
	        try {

	            //get a new instance of parser
	            SAXParser sp = spf.newSAXParser();

	            //parse the file and also register this class for call backs
	            sp.parse("actors63.xml", this);

	        } catch (SAXException se) {
	            se.printStackTrace();
	        } catch (ParserConfigurationException pce) {
	            pce.printStackTrace();
	        } catch (IOException ie) {
	            ie.printStackTrace();
	        }
	    }
	    private void printData() {

	        System.out.println("No of actor '" + actorList.size() + "'.");

	        Iterator<Actor> it = actorList.iterator();
	        while (it.hasNext()) {
	            System.out.println(it.next().toString());
	        }
	    }
	    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	        //reset
	        tempVal = "";
	        if (qName.equalsIgnoreCase("actor")) {
	            //create a new instance of employee	            
	        	actTemp = new Actor();
	        }
	    }
	    public void characters(char[] ch, int start, int length) throws SAXException {
	        tempVal = new String(ch, start, length);
	    }
	    public void endElement(String uri, String localName, String qName) throws SAXException {

	        if (qName.equalsIgnoreCase("actor")) {
	            //add it to the list
	            actorList.add(actTemp);

	        } else if (qName.equalsIgnoreCase("stagename")) {
	        	String[] tokens = tempVal.split("[ ]+",2);
	        	if(tokens.length>=2){
	   
	        		actTemp.setFirst(tokens[0]);
	        		
	        		actTemp.setLast(tokens[1]);
	        	}
	        	else if(tokens.length==1){
	        		actTemp.setFirst("");
	        		actTemp.setLast(tokens[0]);	
	        	}
	        	else {
	        		actTemp.setFirst("");
	        		actTemp.setLast("");
	        	}
	        	
	        } else if (qName.equalsIgnoreCase("dob")) {
	        	try {
	        		
	        		Integer.parseInt(tempVal); 
	        		tempVal= tempVal + "/00/00";
	        	}
	        	catch (NumberFormatException e ){
	        		tempVal= null;
	        	}
	        	actTemp.setDob(tempVal);
	        } 

	    }
	    public void connectDB(){
	    	try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
				Statement statement = dbcon.createStatement();
				String query = "INSERT INTO stars (first_name,last_name,dob) VALUES(?,?,?)";
				 for (int i=0; i< actorList.size();i++){
			        	PreparedStatement pstmt2 = dbcon.prepareStatement(query);
			        	System.out.println(actorList.get(i).toString());
						pstmt2.setString(1, actorList.get(i).getFirst());
						pstmt2.setString(2, actorList.get(i).getLast());
						pstmt2.setString(3, actorList.get(i).getDob());
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
	        actorParser spe = new actorParser();
	        spe.runExample();
	    }

}
