package Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class SearchUser
 */
@WebServlet("/SearchUser")
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String term = request.getParameter("term");
		String users_str = (String) request.getSession().getAttribute("users");
		String user_str = (String) request.getSession().getAttribute("user");
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new StringReader(users_str));
		Users users = gson.fromJson(br, Users.class);
		br = new BufferedReader(new StringReader(user_str));
		User user = gson.fromJson(br, User.class);
		if (term == "") {
			request.getSession().setAttribute("userResult", gson.toJson(users).toString());
			response.getWriter().println(gson.toJson(users).toString());
		}
		else {
			Users result = new Users();
			for (int i=0;i<users.users.length;i++) {
				if (users.users[i].username.startsWith(term)) {
					result.addUser(users.users[i]);
				}
			}
			request.getSession().setAttribute("userResult", gson.toJson(result).toString());
			response.getWriter().println(gson.toJson(result).toString());
		}
	}

}
