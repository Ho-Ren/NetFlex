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
			String title, year, banner, trailer, director;
			String category = request.getParameter("category");
	        String sucess;
	        PrintWriter out = response.getWriter();
	    	first = request.getParameter("first");
			last = request.getParameter("last");
			dob = request.getParameter("dob");
			photo = request.getParameter("photo");
			
			title = request.getParameter("title");
			year = request.getParameter("year");
			banner = request.getParameter("banner");
			trailer = request.getParameter("trailer");
			director = request.getParameter("director");
			
			if (first == null)
				first = "";
			if (banner == null)
				banner ="";
			if (trailer == null)
				trailer = "";
			if(category == null)
				category = "";
			if (category.equals("movie")){
				if (title ==null || title.equals("")|| year==null||year.equals("")||director == null || director.equals("")){
					sucess = "fail";
					 request.setAttribute("sucess", sucess);
					 RequestDispatcher rd=request.getRequestDispatcher("../newMovie.jsp");  
		             rd.include(request,response);
		             return;
				}
			}
			else{
				if (dob ==null || dob.equals("")){
					sucess = "fail";
					 request.setAttribute("sucess", sucess);
					 RequestDispatcher rd=request.getRequestDispatcher("../newStar.jsp");  
		             rd.include(request,response);
		             return;
				}
				
			}
			
			
			

			System.out.println("first: " + first +" last: " + last + " dob:" + dob + " photo:" + photo);
		
		
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
            int rowUpdate;
            String path;
            System.out.print("category: " + category);
            if (category.equals("movie")){
            	rowUpdate = doMovie(dbcon,title,year, director, banner, trailer);
            	path = "../newMovie.jsp";
            }
            else {
            	System.out.println("I am adding a new Star");
            	rowUpdate = doStar(dbcon,first,last,dob,photo);
            	path = "../newStar.jsp";
            }
            System.out.println("rowUpdate : " + rowUpdate);
			 if (rowUpdate >0){
				 sucess = "sucess";
			 }
			 else {
				 sucess  =  "fail";
			 }
			 
			System.out.println("success: " + sucess);
            request.setAttribute("sucess", sucess);
 
            RequestDispatcher rd=request.getRequestDispatcher(path);  
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
	private static int doStar(Connection dbcon, String first, String last,String dob, String photo) throws Exception{
		 String query = " insert into stars (id, first_name, last_name,dob, photo_url)"
 		        + " values (?, ?, ?, ?,?)";
          int id  = getMaxId(dbcon, "stars") +1;
          PreparedStatement pstmt2 = dbcon.prepareStatement(query);
			 pstmt2.setInt(1,id);
			 pstmt2.setString(2, first);
			 pstmt2.setString(3, last);
			 pstmt2.setString(4,dob);
			 pstmt2.setString(5, photo);
			 return pstmt2.executeUpdate();
		
	}
	private static int doMovie(Connection dbcon, String title, String year,String director, String banner, String trailer) throws Exception{
		 String query = " insert into movies values (?, ?, ?, ?, ?,?)";
		       
         int id  = getMaxId(dbcon, "movies") +1;
         PreparedStatement pstmt2 = dbcon.prepareStatement(query);
			 pstmt2.setInt(1,id);
			 pstmt2.setString(2, title);
			 pstmt2.setString(3, year);
			 pstmt2.setString(4,director);
			 pstmt2.setString(5, banner);
			 pstmt2.setString(6, trailer);
			 return pstmt2.executeUpdate();
		
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
