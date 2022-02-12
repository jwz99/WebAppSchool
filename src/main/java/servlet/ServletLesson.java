package servlet;

import java.io.IOException;
import java.util.ArrayList;

import dao.DaoKorepetycje;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelLesson;

/**
 * Servlet implementation class LessonServlet
 */
public class ServletLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletLesson() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextURL = "";
		if (LoginServlet.nextURL == "/studentPage.jsp") {
			nextURL = "/myLessons.jsp";
			DaoKorepetycje dao = new DaoKorepetycje();
			ArrayList<ModelLesson> ll = dao.getLessons(dao.getStudent(LoginServlet.login).getIdu(), "uczeñ");
			request.setAttribute("ll", ll);
			if (request.getParameter("lekcjaD") != null) {
				ModelLesson lesson = dao.getLesson(Integer.parseInt(request.getParameter("lekcjaD")));
				dao.deleteLesson(lesson);
			}
		}
		if (LoginServlet.nextURL == "/teacherPage.jsp") {
			nextURL = "/myLessons_T.jsp";
			DaoKorepetycje dao = new DaoKorepetycje();
			ArrayList<ModelLesson> ll = dao.getLessons(dao.getTeacher(LoginServlet.login).getIdn(), "nauczyciel");
			request.setAttribute("ll", ll);
			if (request.getParameter("lekcjaD") != null) {
				ModelLesson lesson = dao.getLesson(Integer.parseInt(request.getParameter("lekcjaD")));
				dao.deleteLesson(lesson);
			}
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
	}

}
