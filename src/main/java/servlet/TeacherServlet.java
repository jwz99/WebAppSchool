 package servlet;

import java.io.IOException;
import java.util.ArrayList;

import dao.DaoKorepetycje;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelSubject;
import models.ModelTeacher;

/**
 * Servlet implementation class EditTeacherServlet
 */
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TeacherServlet() {
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
		String nextURL = "/editTeacher.jsp";
		ArrayList<ModelSubject> ls = dao.getSubjects();
		request.setAttribute("ls", ls);
		ModelTeacher teacher = new ModelTeacher();
		if (LoginServlet.nextURL.equals("/teacherPage.jsp")) {
			if (request.getParameter("imieTeacher") != "")
				teacher.setName(request.getParameter("imieTeacher"));
			if (request.getParameter("nazwiskoTeacher") != "")
				teacher.setLastname(request.getParameter("nazwiskoTeacher"));
			if (request.getParameter("loginTeacher") != "")
				teacher.setLogin(request.getParameter("loginTeacher"));
			if (request.getParameter("przedmiot") != null) {
				ModelSubject subject = dao.getSubjectByName(request.getParameter("przedmiot"));
				teacher.setSubject(subject);
			}
			dao.editTeacher(teacher, LoginServlet.idn);
		}
		if (LoginServlet.nextURL.equals("/adminPage.jsp")) {
			nextURL = "/teachers.jsp";
			ArrayList<ModelTeacher> lt = dao.getTeachers();
			request.setAttribute("l_t", lt);
			if (request.getParameter("nauczyciel") != null) {
				String[] teacherTab = request.getParameter("nauczyciel").split(" ");
				teacher = dao.getTeacherByName(teacherTab[0], teacherTab[1]);
				if (request.getParameter("imieTeacher") != "")
					teacher.setName(request.getParameter("imieTeacher"));
				if (request.getParameter("nazwiskoTeacher") != "")
					teacher.setLastname(request.getParameter("nazwiskoTeacher"));
				if (request.getParameter("loginTeacher") != "")
					teacher.setLogin(request.getParameter("loginTeacher"));
				dao.editTeacher(teacher, teacher.getIdn());
			}
			request.setAttribute("l_t", lt);
			if (request.getParameter("nauczyciel") != null) {
				String[] teacher_tab = request.getParameter("nauczyciel").split(" ");
				ModelTeacher teacherD = dao.getTeacherByName(teacher_tab[0], teacher_tab[1]);
				dao.deleteLessonByTeacher(teacherD);
				dao.deleteTeacher(teacherD);
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
		doGet(request, response);
	}

}
