package servlet;

import java.io.IOException;

import dao.DaoKorepetycje;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelStudent;
import models.ModelTeacher;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String nextURL;
	public static String login;
	public static int idu;
	public static int idn;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DaoKorepetycje dao = new DaoKorepetycje();
		if (request.getParameterMap().containsKey("login")) {
			login = request.getParameter("login");
			int loginResult = dao.ifExist(login);
			String passwordResult_T = dao.checkPasswd(login, "nauczyciele");
			String passwordResult_S = dao.checkPasswd(login, "uczniowie");
			if (loginResult == 0) {
				request.setAttribute("error", "B³êdny login");
				nextURL = "/index.jsp";
			} else if (login.equals("admin") && request.getParameter("haslo").equals("admin")) {
				nextURL = "/adminPage.jsp";
			} else if (loginResult == 1 && passwordResult_T.equals(request.getParameter("haslo"))) {
				nextURL = "/teacherPage.jsp";
			} else if (loginResult == 2 && passwordResult_S.equals(request.getParameter("haslo"))) {
				nextURL = "/studentPage.jsp";
			} else {
				request.setAttribute("error", "B³êdne has³o");
				nextURL = "/index.jsp";
			}
				
		}
		if(nextURL.equals("/teacherPage.jsp")) {
			ModelTeacher teacher = dao.getTeacher(login);
			request.setAttribute("imie", teacher.getName());
			request.setAttribute("nazwisko", teacher.getLastname());
			request.setAttribute("login", teacher.getLogin());
			request.setAttribute("przedmiot", teacher.getSubject().getName());
			request.setAttribute("teacher", teacher.getName());
			idn = teacher.getIdn();
		}
		if(nextURL.equals("/studentPage.jsp")) {
			ModelStudent student = dao.getStudent(login);
			request.setAttribute("imie", student.getName());
			request.setAttribute("nazwisko", student.getLastname());
			request.setAttribute("login", student.getLogin());
			request.setAttribute("student", student.getName());
			idu = student.getIdu();
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
