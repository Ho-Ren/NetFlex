import java.io.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

public class AjaxTest extends HttpServlet {

  @SuppressWarnings("unused")
public void doGet(HttpServletRequest request, HttpServletResponse response)
                               throws ServletException, IOException {
	long startTimeTX = System.nanoTime();
    PrintWriter out = response.getWriter();
	String input = request.getParameter("Title");
	System.out.println(input);
	String[] token = input.split("\\s+");
//	response.setContentType("text/html");
//    PrintWriter out = response.getWriter();
    ArrayList <String>list = new ArrayList<String> (); 
    
    try {
    	 Context initCtx = new InitialContext();
         if (initCtx == null)
             out.println("initCtx is NULL");

         Context envCtx = (Context) initCtx.lookup("java:comp/env");
         if (envCtx == null)
             out.println("envCtx is NULL");

         // Look up our data source
         DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
         if (ds == null)
             out.println("ds is null.");

         Connection dbcon = ds.getConnection();
         if (dbcon == null)
             out.println("dbcon is null.");
/*
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
*/		
        long startTimeTJ = System.nanoTime();
		String query = "select title from movies where title ";
		if(input.length()>3){
			for(int i = 0; i <token.length; i++){
				if (i == 0){
					query  += "regexp ?";
				}
				else
				{
					query += " and title regexp ?";
				}
				
			}
			
		 System.out.println("query: " + query);
		 
		PreparedStatement stmt = dbcon.prepareStatement(query);
		for (int i = 0; i< token.length;i++){
			stmt.setString(i+1,  "(^| )" + token[i]); 
		}
		 System.out.println(stmt.toString());
		 
		 ResultSet rs = stmt.executeQuery();
		while (rs.next()){
			list.add(rs.getString(1));		
//			System.out.println(rs.getString(1));
		}	
		}
		long endTimeTJ = System.nanoTime();
	    long elapsedTimeTJ = endTimeTJ - startTimeTJ;
		for(int j = 0; j< list.size();j++){
			System.out.println(list.get(j));
		}
		writeFile("tj.txt", elapsedTimeTJ);
		String json = new Gson().toJson(list);
		 response.setContentType("application/json");       //application/json
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().write(json);
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    long endTimeTX = System.nanoTime();
    long elaspedTX = endTimeTX - startTimeTX;

    writeFile("TX.txt", elaspedTX);
    //out.println("Hello World" + Calendar.getInstance().get(Calendar.SECOND));
  }
  private void writeFile (String fileName, long time){

		 try{
		  File file = new File(fileName);
	      file.createNewFile();
		  FileWriter fw = new FileWriter(file, true);
		  fw.write(new String(Long.toString(time) +" "));
		  fw.close();
				} catch (IOException e) {
			}
  }
}
