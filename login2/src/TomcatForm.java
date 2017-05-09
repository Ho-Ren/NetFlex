
/* A servlet to display the contents of the MySQL movieDB database */

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TomcatForm extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getServletInfo()
    {
       return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    // Use http GET

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        String loginUser = "root";
        String loginPasswd = "5555";
        String loginUrl = "jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false";
        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();


        try
           {
              //Class.forName("org.gjt.mm.mysql.Driver");
              Class.forName("com.mysql.jdbc.Driver").newInstance();

              Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false","root", "");
//            		  loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement statement = dbcon.createStatement();

              String pw = request.getParameter("pw");
              String email = request.getParameter("email");
              String query = "SELECT * from customers where password = '" + pw + "'" + "and email ='" + email +"'";

              ResultSet rm = statement.executeQuery(query);
              rm.last();
              int count = rm.getRow();
              System.out.println(count);
              if (count !=1){
            	  out.print("<p style=\"color:red\">Sorry username or password error</p>");  
                  RequestDispatcher rd=request.getRequestDispatcher("../index.html");  
                  rd.include(request,response);  
              }
              else{
              // Perform the query
            
              ResultSet rs = statement.executeQuery(query);
              String name = null;
              while (rs.next()){
            	  name = rs.getString("first_name");
              }
           
              HttpSession session = request.getSession(true);
              session.setAttribute("name", name);
              session.setAttribute("username", email);
              session.setAttribute("cartsession", new cart());
              RequestDispatcher rd=request.getRequestDispatcher("../welcome.jsp");  
              rd.include(request,response);
             
              rs.close();
           }
              statement.close();
              dbcon.close();
              
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
         out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
	doGet(request, response);
    }
}