import java.io.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

public class AjaxTest extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
                               throws ServletException, IOException {
	
//	Calendar c = Calendar.getInstance ();
	String input = request.getParameter("Title");
	System.out.println(input);
	String[] token = input.split("\\s+");
//	response.setContentType("text/html");
//    PrintWriter out = response.getWriter();
    ArrayList <String>list = new ArrayList<String> (); 
    
//    out.println(c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND));
    try {
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection dbcon = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", Globals.un, Globals.pw);
		Statement st = dbcon.createStatement();
//		System.out.println(input);
		String query = "select title from movies where title ";
		if(input.length()>3){
			for(int i = 0; i <token.length; i++){
				if (i == 0){
					query+= "regexp '(^| )" + token[i]+"'";
				}
				else
				{
					query += " and title regexp '(^| )" + token[i] +"'";
				}
				
			}
			
		 System.out.println(query);
		PreparedStatement stmt = dbcon.prepareStatement(query);
		ResultSet rs =stmt.executeQuery();
		while (rs.next()){
			list.add(rs.getString(1));		
//			System.out.println(rs.getString(1));
		}	
		}
		for(int j = 0; j< list.size();j++){
			System.out.println(list.get(j));
		}
		String json = new Gson().toJson(list);
		 response.setContentType("application/json");       //application/json
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().write(json);
//		request.setAttribute("li", list);
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    //out.println("Hello World" + Calendar.getInstance().get(Calendar.SECOND));
  }
}
