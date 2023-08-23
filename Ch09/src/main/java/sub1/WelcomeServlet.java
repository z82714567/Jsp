package sub1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("WelcomeServlet init()...");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("WelcomeServlet doGet()...");
		
		//HTML 출력
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter writer =  resp.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta charset='UTF-8'>");
		writer.println("<title>WelcomeServlet</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h3>WelcomeServlet</h3>");
		writer.println("<a href='./1_Servlet.jsp'>서블릿 메인</a>");
		writer.println("<a href='./hello.do'>HelloServlet</a>");
		writer.println("<a href='./welcome.do'>WelcomeServlet</a>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("WelcomeServlet doPost()...");
	}
	
	@Override
	public void destroy() {
		System.out.println("WelcomeServlet destroy()...");
	}
	
}
