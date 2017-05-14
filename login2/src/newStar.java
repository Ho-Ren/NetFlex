import java.io.IOException;
import java.io.PrintWriter;
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

public class newStar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String getServletInfo() {
		return "Servlet connects to MySQL database and displays result of a SELECT";
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String first, last, dob, photo;
        String sucess;
        PrintWriter out = response.getWriter();
    	first = request.getParameter("first");
		last = request.getParameter("last");
		dob = request.getParameter("dob");
		photo = request.getParameter("photo");
		if (first == null)
			first = "";
		if (dob ==null || dob.equals("")){
			sucess = "fail";
			 request.setAttribute("sucess", sucess);
			 RequestDispatcher rd=request.getRequestDispatcher("../newStar.jsp");  
             rd.include(request,response);
		}

		System.out.println("first: " + first +" last: " + last + " dob:" + dob + " photo:" + photo);
		
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
            System.out.println("this is  working");
            String query = " insert into stars (id, first_name, last_name,dob, photo_url)"
    		        + " values (?, ?, ?, ?,?)";
             int id  = getMaxId(dbcon, "stars") +1;
             PreparedStatement pstmt2 = dbcon.prepareStatement(query);
			 pstmt2.setInt(1,id);
			 pstmt2.setString(2, first);
			 pstmt2.setString(3, last);
			 pstmt2.setString(4,dob);
			 pstmt2.setString(5, photo);
			 int rowUpdate = pstmt2.executeUpdate();
			 
			 if (rowUpdate >0){
				 sucess = "sucess";
			 }
			 else {
				 sucess  =  "fail";
			 }
			 
			System.out.println("success: " + sucess);
            request.setAttribute("sucess", sucess);
 
            RequestDispatcher rd=request.getRequestDispatcher("../newStar.jsp");  
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
