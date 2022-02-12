package servlet;

import java.io.IOException;

import dao.DaoKorepetycje;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterStudent
 */
public class RegisterStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RegisterStudent() {
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
		if (dao.ifExist(request.getParameter("loginStudent")) != 0 && request.getParameter("loginStudent") != null)
			request.setAttribute("error", "Podany login jest zajêty");
		else if (request.getParameter("powtorzHasloStudent") != null && request.getParameter("hasloStudent") != null
				&& request.getParameter("hasloStudent").equals(request.getParameter("powtorzHasloStudent")) == false)
			request.setAttribute("error", "Podane has³a ró¿ni¹ siê");
		else if (request.getParameter("imieStudent") != null && request.getParameter("nazwiskoStudent") != null
				&& request.getParameter("loginStudent") != null && request.getParameter("hasloStudent") != null) {
			dao.addStudent(request.getParameter("imieStudent"), request.getParameter("nazwiskoStudent"),
					request.getParameter("loginStudent"), request.getParameter("hasloStudent"));
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		getServletContext().getRequestDispatcher("/registerStudent.jsp").forward(request, response);
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
