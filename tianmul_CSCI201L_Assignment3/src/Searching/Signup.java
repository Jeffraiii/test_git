package Searching;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
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
		String imageURL = request.getParameter("imageURL");
		String users = (String) request.getSession().getAttribute("users");
		boolean valid = true;
		
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new StringReader(users));
		Users userList = gson.fromJson(br, Users.class);
		
		if (imageURL == "") {
			response.getWriter().println("4");
			valid = false;
		}
		if (username == "") {
			response.getWriter().println("1");
			valid = false;
		}
		else {
			for (User u : userList.users) {
				if (u.username.equals(username)) {
					response.getWriter().println("3");
					valid = false;
					break;
				}
			}
		}
		if (password == "") {
			response.getWriter().println("2");
			valid = false;
		}
		response.getWriter().flush();
		
		if (valid) {
			User u = new User();
			u.username = username;
			u.password = password;
			u.imageURL = imageURL;
			userList.addUser(u);
			FileWriter fw;
			PrintWriter pw;
			try {
				String filename = getServletContext().getRealPath("Sample.json");
				fw = new FileWriter(filename);
				pw = new PrintWriter(fw);
				String result = gson.toJson(userList);
				pw.print(result);
				pw.flush();
				request.getSession().setAttribute("user", gson.toJson(u).toString());
				request.getSession().setAttribute("users", gson.toJson(userList).toString());
			}
			catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
	}

}
