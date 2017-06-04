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

        response.setContentType("text/html");    // Response mime type
        // Output stream to STDOUT
        PrintWriter out = response.getWriter();
        try
           {
              //Class.forName("org.gjt.mm.mysql.Driver");
              Class.forName("com.mysql.jdbc.Driver").newInstance();

              Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw );
              
              //check recaptcha
              String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
          	  boolean valid = VerifyUtils.verify(gRecaptchaResponse);
//      	  System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
	            System.out.println("valid = " + valid);
          	  
              // Declare our statement
              Statement statement = dbcon.createStatement();    
              String pw = request.getParameter("pw");
              String email = request.getParameter("email");             
              String query = "SELECT * from customers where password = ? and email = ?";
              PreparedStatement pstmt = dbcon.prepareStatement(query);
              pstmt.setString (1, pw);
              pstmt.setString(2, email);
              ResultSet rm = pstmt.executeQuery();
              rm.last();
              int count = rm.getRow();
              System.out.println(count);
              if (count !=1){
            	  out.print("<p style=\"color:red\">Sorry username or password error</p>");  
                  RequestDispatcher rd=request.getRequestDispatcher("../index.html");  
                  rd.include(request,response);  
              }
              else if (!valid) {
            	  out.print("<p style=\"color:red\">Invalid recaptcha, please try again</p>");
//            	  out.print("<p style=\"color:red\">RECAPTCHA </p>" + gRecaptchaResponse);
            	  request.setAttribute("rec", gRecaptchaResponse);
                  RequestDispatcher rd=request.getRequestDispatcher("../index.html");  
                  rd.include(request,response); 
              }
              // Perform the query
              else{
              rm.first();
              String name = null;
              int customerID = 961;
              while (rm.next()){
            	  name = rm.getString("first_name");
            	  customerID = rm.getInt("id");
              }
              System.out.println("customer ID:" + customerID);
              HttpSession session = request.getSession(true);
              session.setAttribute("name", name);
              session.setAttribute("username", email);
              session.setAttribute("cartsession", new cart());
              session.setAttribute("customerID", customerID);
              RequestDispatcher rd=request.getRequestDispatcher("../welcome.jsp");  
              rd.include(request,response);
              rm.close();
           }
              statement.close();
              dbcon.close();
              
            }
        catch (SQLException ex) {
              while (ex != null) {
                    System.out.println ("SQL Exception:  " + ex.getMessage ());
                    ex = ex.getNextException ();
                }  // end while
              response.sendRedirect("../index.html");
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