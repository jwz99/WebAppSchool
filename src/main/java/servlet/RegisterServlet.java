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
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RegisterServlet() {
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
		ArrayList<ModelSubject> ls = dao.getSubjects();
		request.setAttribute("ls", ls);
		if (dao.ifExist(request.getParameter("loginTeacher")) != 0 && request.getParameter("loginTeacher") != null)
			request.setAttribute("error", "Podany login jest zajêty");
		else if (request.getParameter("hasloTeacher") != null && request.getParameter("powtorzHasloTeacher") != null
				&& request.getParameter("hasloTeacher").equals(request.getParameter("powtorzHasloTeacher")) == false)
			request.setAttribute("error", "Podane has³a ró¿ni¹ siê");
		else if(request.getParameter("hasloTeacher") != null && request.getParameter("powtorzHasloTeacher") != null && request.getParameter("loginTeacher") != null){
			dao.addTeacher(request.getParameter("imieTeacher"), request.getParameter("nazwiskoTeacher"),
					request.getParameter("loginTeacher"), request.getParameter("hasloTeacher"),
					request.getParameter("przedmiot"));
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		getServletContext().getRequestDispatcher("/registerTeacher.jsp").forward(request, response);
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
