package servlet;

import java.io.IOException;
import java.util.ArrayList;

import dao.DaoKorepetycje;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelStudent;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public StudentServlet() {
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
		int idn = dao.getTeacher(LoginServlet.login).getIdn();
		//boolean loginChange = false;
		ArrayList<ModelStudent> ls = dao.getStudentsById(idn);
		request.setAttribute("ls", ls);
		String nextURL = "/myStudents.jsp";
		if (LoginServlet.nextURL.equals("/studentPage.jsp")) {
			nextURL = "/editStudent.jsp";
			ModelStudent student = new ModelStudent();
			if (request.getParameter("imieStudent") != "")
				student.setName(request.getParameter("imieStudent"));
			if (request.getParameter("nazwiskoStudent") != "")
				student.setLastname(request.getParameter("nazwiskoStudent"));
			if (request.getParameter("loginStudent") != "")
				student.setLogin(request.getParameter("loginStudent"));
				dao.editStudent(student, LoginServlet.idu);
		}
		if (LoginServlet.nextURL.equals("/adminPage.jsp")) {
			nextURL = "/students.jsp";
			ArrayList<ModelStudent> ls_a = dao.getStudents();
			request.setAttribute("ls", ls_a);
			if (request.getParameter("uczen") != null) {
				ModelStudent student = new ModelStudent();
				String[] studentTab = request.getParameter("uczen").split(" ");
				int idu = dao.findStudent(studentTab[0], studentTab[1]);
				if (request.getParameter("imieStudent") != "")
					student.setName(request.getParameter("imieStudent"));
				if (request.getParameter("nazwiskoStudent") != "")
					student.setLastname(request.getParameter("nazwiskoStudent"));
				if (request.getParameter("loginStudent") != "")
					student.setLogin(request.getParameter("loginStudent"));
				dao.editStudent(student, idu);
			}
			request.setAttribute("ls", ls_a);
			if (request.getParameter("uczen") != null) {
				String[] student_tab = request.getParameter("uczen").split(" ");
				ModelStudent student = dao.getStudentByName(student_tab[0], student_tab[1]);
				dao.deleteLessonByStudent(student);
				dao.deleteStudent(student);
			}
			// dao.editStudent(null, idn)
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
