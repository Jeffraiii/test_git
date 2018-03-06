package Searching;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		FileReader fr;
//		BufferedReader br;
		boolean valid = false;
//		try {
			String users = (String) request.getSession().getAttribute("users");
			BufferedReader br = new BufferedReader(new StringReader(users));
			Gson gson = new Gson();
			Users obj = gson.fromJson(br, Users.class);
			User user = new User();
			for (User u : obj.users) {
				if (username.equals(u.username) && password.equals(u.password)) {
					valid = true;
					user = u;
					break;
				}
			}
			if (valid) {
				request.getSession().setAttribute("user", gson.toJson(user).toString());
//				String pageToForward = "/Results.jsp";
//		        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(pageToForward);
//				dispatch.forward(request, response);
				
			}
			else {
				response.getWriter().println("Your username or password was incorrect! <br/>");
			}
			
//		}
//		catch (FileNotFoundException fnfe){
//			System.out.println("That file could not be found.");
//			System.out.println(fnfe.getMessage());
//		}
//		catch (JsonParseException jpe) {
//			System.out.println("That file is not a well-formed JSON file.");
//			System.out.println(jpe.getMessage());
//		}
	}

}
