package Searching;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class LoadData
 */
@WebServlet("/LoadData")
public class LoadData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FileReader fr;
		BufferedReader br;
		String filename = getServletContext().getRealPath("Sample.json");
		fr = new FileReader(filename);
		br = new BufferedReader(fr);
		request.getSession().setAttribute("users", br.lines().collect(Collectors.joining()));
//		response.getWriter().write(br.lines().collect(Collectors.joining()));
	}
}
