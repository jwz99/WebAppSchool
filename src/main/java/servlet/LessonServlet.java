package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.DaoKorepetycje;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ModelLesson;
import models.ModelSubject;

/**
 * Servlet implementation class LessonServlet
 */
public class LessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LessonServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextURL = "/addLesson.jsp";
		DaoKorepetycje dao = new DaoKorepetycje();
		ArrayList<ModelSubject> ls = dao.getSubjects();
		HttpSession sesja = request.getSession();
		sesja.setAttribute("ls", ls);
		if (request.getParameter("przedmiot") != null) {
			ModelSubject subject = new ModelSubject();
			subject.setName(request.getParameter("przedmiot"));
			ArrayList<ModelLesson> ll = dao.getLessons(subject);
			request.setAttribute("ll", ll);
		}
		if (request.getParameter("lekcje") != null) {
			String lesson_tab[] = request.getParameter("lekcje").split(" ");
			ModelLesson lesson = new ModelLesson();
			lesson.setTeacher(dao.getTeacherByName(lesson_tab[0], lesson_tab[1]));
			dao.addLesson(dao.getStudent(LoginServlet.login).getIdu(), lesson.getTeacher().getIdn(),
					LocalDate.parse(lesson_tab[3]), Integer.parseInt(lesson_tab[2]));
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
