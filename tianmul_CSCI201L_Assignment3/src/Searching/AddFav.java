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
 * Servlet implementation class AddFav
 */
@WebServlet("/AddFav")
public class AddFav extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFav() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String users_str = (String) request.getSession().getAttribute("users");
		String user_str = (String) request.getSession().getAttribute("user");
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new StringReader(users_str));
		Users users = gson.fromJson(br, Users.class);
		br = new BufferedReader(new StringReader(user_str));
		User user = gson.fromJson(br, User.class);
		user.addfav(title);
		users.updateUser(user);
		FileWriter fw;
		PrintWriter pw;
		try {
			String filename = getServletContext().getRealPath("Sample.json");
			fw = new FileWriter(filename);
			pw = new PrintWriter(fw);
			String result = gson.toJson(users);
			pw.print(result);
			pw.flush();
			request.getSession().setAttribute("user", gson.toJson(user).toString());
			request.getSession().setAttribute("users", gson.toJson(users).toString());
		}
		catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

}
