import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class updateMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String getServletInfo() {
		return "Servlet connects to MySQL database and displays result of a SELECT";
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String first, last, dob, title,year,genre;
        String missing;
        String sucess = null;
        PrintWriter out = response.getWriter();
    	first = request.getParameter("first");
		last = request.getParameter("last");
		dob = request.getParameter("dob");
		year = request.getParameter("year");
		title = request.getParameter("title");
		genre = request.getParameter("genre");
		
		if (first == null)
			first = "";
		if (last == null || last.equals("")||dob ==null || dob.equals("")|| year  == null || year.equals("")||genre == null || genre.equals("")){
			sucess = "fail";
			 request.setAttribute("sucess", sucess);
			 RequestDispatcher rd=request.getRequestDispatcher("../addMovie.jsp");  
             rd.include(request,response);
		}

		System.out.println("first: " + first +" last: " + last + " dob:" + dob );
		
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
            System.out.println("this is  working");
//            call add_movie("jackie", "chan", "1954/04/07", "Rush  2", "2001", "Crime", @result);
            String query = " call add_movie (?, ?,  ?, ?, ?, ?, ?)";
            CallableStatement cstmt = dbcon.prepareCall(query);
            cstmt.setString(1, first);
            cstmt.setString(2, last);
            cstmt.setString(3, dob);
            cstmt.setString(4, title);
            cstmt.setString(5, year);
            cstmt.setString(6, genre);
			cstmt.registerOutParameter(7,java.sql.Types.INTEGER);
			cstmt.executeUpdate();
			int result = cstmt.getInt(7);
			
			 
			 if (result == -2){
				 missing = "movie";
				 request.setAttribute("titleName", title);		 
			 }
			 else if (result == -1){
				 missing = "star";
				 request.setAttribute("starName", first + " " + last);		
				 }
			 
				 
			 else {
				 sucess  =  "sucess";
				 missing = null;
			 }
			 

            request.setAttribute("sucess", sucess);
            request.setAttribute("missing", missing );
 
            RequestDispatcher rd=request.getRequestDispatcher("../addMovie.jsp");  
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
	private static int getMaxId (Connection conn, String table) throws Exception{
		 int id = 0;
		 String query = "Select MAX(id) from " + table;
		 ResultSet result = conn.createStatement().executeQuery(query);

        while (result.next()){
       	 id = result.getInt(1);
        }
       return id;
	 }
}
