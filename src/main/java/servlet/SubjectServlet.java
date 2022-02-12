package servlet;

import java.io.IOException;
import java.util.ArrayList;

import dao.DaoKorepetycje;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelSubject;

/**
 * Servlet implementation class SubjectServlet
 */
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SubjectServlet() {
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
		if(request.getParameter("przedmiot")!=null) {
			dao.addSubject(request.getParameter("przedmiot"));
		}
		ArrayList<ModelSubject> ls = dao.getSubjects();
		request.setAttribute("ls", ls);
		if(request.getParameter("przedmiotD")!=null) {
			ModelSubject subject = dao.getSubjectByName(request.getParameter("przedmiotD"));
			//dao.deleteLessonBySubject(subject);
			dao.deleteSubject(subject);
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		getServletContext().getRequestDispatcher("/subjects.jsp").forward(request, response);
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
