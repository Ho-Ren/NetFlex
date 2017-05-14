import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Metadata extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public String getServletInfo()
    {
       return "Servlet connects to MySQL database and displays result of a SELECT";
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
	
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
            Statement stmt = dbcon.createStatement();
            ResultSet result = stmt.executeQuery("SHOW TABLES");
            System.out.println("got the result set");
            Map <String, ArrayList<Pair>> meta = new HashMap <String, ArrayList<Pair>>();
            while (result.next()){
           	 String tname = result.getString(1);
   			 Statement stmt2 = dbcon.createStatement();
   			 ResultSet result2 = stmt2.executeQuery("Select * from " + tname);
	    	 ResultSetMetaData metadata = result2.getMetaData();
	    	 int i = 1;
	    	 ArrayList <Pair> pairList = new ArrayList <Pair> ();
	    	 while (i<=metadata.getColumnCount()){
		    	 pairList.add(new Pair(metadata.getColumnName(i),metadata.getColumnTypeName(i)));
		    	 i++;
	    	 }
	    	 meta.put(tname, pairList);
            }
            request.setAttribute("meta", meta);
        	RequestDispatcher rd=request.getRequestDispatcher("../showMeta.jsp");  
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


