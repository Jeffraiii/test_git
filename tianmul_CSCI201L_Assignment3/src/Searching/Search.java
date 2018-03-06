package Searching;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volumes;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void service(HttpServletRequest req, HttpServletResponse resp)
		    throws ServletException, IOException
		{
			String ApiKey = new String("AIzaSyCf4CnB1MGdPmbJNkDX1L6uAldy0IR9HU4");
			String url = new String("https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key="+ApiKey);
			GsonFactory jsonFactory = new GsonFactory();
			final Books books = new Books.Builder(new ApacheHttpTransport(), jsonFactory, null)
					.setApplicationName("")
					.setGoogleClientRequestInitializer(new BooksRequestInitializer(ApiKey))
					.build();
			
			List volumesList = books.volumes().list("intitle:Harry Potter");
			Volumes volumes = volumesList.execute();
			
			volumes.getItems().stream().forEach(book -> {
				
				System.out.println(book.getVolumeInfo().getTitle());
			});
		
		}

}
