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

public class MovieParser extends DefaultHandler {
	private ArrayList<PMovies> movies;
	private PMovies tempMov;
	private String tempStr;
	
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
			tempMov.setCategories(cat);
		}
		
	}
	
	public void run() {
		parseDocument();
		printData();
	}
	
	/* MAIN */
	public static void main(String[] args) {
		MovieParser mp = new MovieParser();
		mp.run();
	}
}
