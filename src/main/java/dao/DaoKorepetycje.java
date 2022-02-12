package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import models.ModelLesson;
import models.ModelStudent;
import models.ModelSubject;
import models.ModelTeacher;

public class DaoKorepetycje {

	private Connection dbcon = null;
	private Statement dbstat = null;

	private void openCon() {
		String login = "s44677";
		String haslo = "sem4KauER";
		String url = "jdbc:postgresql://localhost:5432/s44677?currentSchema=\"paw\"";
		try {
			Class.forName("org.postgresql.Driver");
			dbcon = DriverManager.getConnection(url, login, haslo);
			dbstat = dbcon.createStatement();
		} catch (ClassNotFoundException ex) {
			System.err.println("ClassNotFoundException z init: " + ex.getMessage());
		} catch (SQLException ex) {
			System.err.println("SQLException z init: " + ex.getMessage());
		}
	}

	public void addTeacher(String imie, String nazwisko, String login, String haslo, String przedmiot) {
		String query_S = "SELECT idp FROM przedmioty WHERE nazwa = '" + przedmiot + "'";
		int idp = 0;
		ResultSet result;
		try {
			openCon();
			result = dbstat.executeQuery(query_S);
			if (result.next()) {
				idp = result.getInt("idp");
				closeCon();
			}
			openCon();
			String query = "INSERT INTO nauczyciele (imie, nazwisko, login, haslo, idp) VALUES (" + "'" + imie + "'"
					+ "," + "'" + nazwisko + "'" + "," + "'" + login + "'" + "," + "'" + haslo + "'" + "," + idp + ")";
			dbstat.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public ArrayList<ModelLesson> getLessons(int id, String user) {
		ArrayList<ModelLesson> ll = new ArrayList<>();
		ModelTeacher teacher;
		ModelSubject subject;
		String query;
		ResultSet result = null;
		if (user == "nauczyciel") {
			query = "SELECT * FROM lekcje WHERE idn = " + id;
			try {
				openCon();
				result = dbstat.executeQuery(query);
				while (result.next()) {
					ModelLesson lesson = new ModelLesson();
					ModelStudent student = getStudentById(result.getInt("idu"));
					lesson.setIdl(result.getInt("idl"));
					lesson.setHour(result.getInt("godzina"));
					lesson.setDate(LocalDate.parse(result.getString("data")));
					lesson.setStudent(student);
					ll.add(lesson);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				closeCon();
			}
		}
		if (user == "uczeñ") {
			query = "SELECT * FROM lekcje WHERE idu = " + id;
			try {
				openCon();
				result = dbstat.executeQuery(query);
				while (result.next()) {
					ModelLesson lesson = new ModelLesson();
					lesson.setIdl(result.getInt("idl"));
					lesson.setHour(result.getInt("godzina"));
					lesson.setDate(LocalDate.parse(result.getString("data")));
					teacher = getTeacher(result.getInt("idn"));
					subject = teacher.getSubject();
					lesson.setTeacher(teacher);
					ll.add(lesson);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				closeCon();
			}
		}
		return ll;
	}

	public ArrayList<ModelLesson> getLessons(ModelSubject subject) {
		ArrayList<ModelLesson> ls = new ArrayList<ModelLesson>();
		ArrayList<ModelTeacher> lt = getTeachersBySubject(subject.getName());
		LocalDate ld = LocalDate.now();
		for (int i = 0; i < 3; i++) {
			ld = ld.plusDays(1);
			for (int j = 9; j <= 18; j++) {
				for (int k = 0; k < lt.size(); k++) {
					int hour = j;
					String query = "SELECT COUNT(*) AS number FROM lekcje WHERE idn = " + lt.get(k).getIdn()
							+ "AND godzina = " + hour + "AND data = '" + ld + "'";
					ResultSet result;
					int results = 0;

					try {
						openCon();
						result = dbstat.executeQuery(query);
						while (result.next()) {
							results = result.getInt("number");
						}
						if (results == 0) {
							ModelLesson lesson = new ModelLesson();
							lesson.setDate(ld);
							lesson.setHour(hour);
							lesson.setTeacher(lt.get(k));
							ls.add(lesson);
						}
					} catch (Exception e) {
						System.out.println(e);
					} finally {
						closeCon();
					}
				}

			}
		}

		return ls;
	}

	public ModelLesson getLesson(int idl) {
		ModelLesson lesson = new ModelLesson();
		String query = "SELECT * FROM lekcje WHERE idl = " + idl;
		ResultSet result;
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				lesson.setDate(LocalDate.parse(result.getString("data")));
				lesson.setHour(result.getInt("godzina"));
				lesson.setIdl(result.getInt("idl"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}

		return lesson;
	}

	public void addLesson(int idu, int idn, LocalDate date, int hour) {
		String query = "INSERT INTO lekcje (idu, idn, data, godzina) VALUES ( " + idu + ", " + idn + ", '" + date
				+ "', " + hour + ")";
		try {
			openCon();
			dbstat.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public void addStudent(String imie, String nazwisko, String login, String haslo) {
		String query = "INSERT INTO uczniowie (imie, nazwisko, login, haslo) VALUES (" + "'" + imie + "'" + "," + "'"
				+ nazwisko + "'" + "," + "'" + login + "'" + "," + "'" + haslo + "'" + ")";
		try {
			openCon();
			dbstat.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public void editStudent(ModelStudent student, int idu) {
		String query;
		try {
			openCon();
			if (student.getName() != null) {
				query = "UPDATE uczniowie SET imie = '" + student.getName() + "' WHERE idu = " + idu;
				dbstat.executeQuery(query);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			openCon();
			if (student.getLastname() != null) {
				query = "UPDATE uczniowie SET nazwisko = '" + student.getLastname() + "' WHERE idu = " + idu;
				dbstat.executeQuery(query);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			openCon();
			if (student.getLogin() != null) {
				openCon();
				query = "UPDATE uczniowie SET login = '" + student.getLogin() + "' WHERE idu = " + idu;
				dbstat.executeQuery(query);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public ModelSubject getSubjectByName(String przedmiot) {
		String query_S = "SELECT idp FROM przedmioty WHERE nazwa = '" + przedmiot + "'";
		ModelSubject subject = new ModelSubject();
		ResultSet result;
		try {
			openCon();
			result = dbstat.executeQuery(query_S);
			if (result.next()) {
				subject.setIdp(result.getInt("idp"));
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return subject;
	}

	public String getSubjectName(int idp) {
		String query_S = "SELECT nazwa FROM przedmioty WHERE idp = " + idp;
		String name = "";
		ResultSet result;
		try {
			openCon();
			result = dbstat.executeQuery(query_S);
			if (result.next()) {
				name = result.getString("nazwa");
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return name;
	}

	public void editTeacher(ModelTeacher teacher, int idn) {
		String query;
		try {
			openCon();
			if (teacher.getName() != null) {
				query = "UPDATE nauczyciele SET imie = '" + teacher.getName() + "' WHERE idn = " + idn;
				dbstat.executeQuery(query);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			openCon();
			if (teacher.getLastname() != null) {
				query = "UPDATE nauczyciele SET nazwisko = '" + teacher.getLastname() + "' WHERE idn = " + idn;
				dbstat.executeQuery(query);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			openCon();
			if (teacher.getLogin() != null) {
				openCon();
				query = "UPDATE nauczyciele SET login = '" + teacher.getLogin() + "' WHERE idn = " + idn;
				dbstat.executeQuery(query);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			openCon();
			if (teacher.getSubject() != null) {
				openCon();
				query = "UPDATE nauczyciele SET idp = '" + teacher.getSubject().getIdp() + "' WHERE idn = " + idn;
				dbstat.executeQuery(query);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public int ifExist(String user) {
		String queryT = "SELECT COUNT(login) AS number FROM nauczyciele WHERE login = " + "'" + user + "'";
		String queryS = "SELECT COUNT(login) AS number FROM uczniowie WHERE login = " + "'" + user + "'";
		ResultSet resultT, resultS;
		int count;
		try {
			openCon();
			resultT = dbstat.executeQuery(queryT);
			if (resultT.next()) {
				count = resultT.getInt("number");
				if (count == 1)
					return 1;
				closeCon();
			}
			openCon();
			resultS = dbstat.executeQuery(queryS);
			if (resultS.next()) {
				count = resultS.getInt("number");
				if (count == 1)
					return 2;
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return 0;
	}

	public String checkPasswd(String login, String user) {
		String query = "SELECT haslo FROM " + user + " WHERE login = " + "'" + login + "'";
		ResultSet result;
		String haslo = "";
		try {
			openCon();
			result = dbstat.executeQuery(query);
			if (result.next()) {
				haslo = result.getString("haslo");
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return haslo;
	}

	public int findStudent(String name, String lastname) {
		String query = "SELECT idu FROM uczniowie WHERE imie = " + "'" + name + "' AND nazwisko = '" + lastname + "'";
		ResultSet result;
		int idu = 0;
		try {
			openCon();
			result = dbstat.executeQuery(query);
			if (result.next()) {
				idu = result.getInt("idu");
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return idu;
	}

	public ModelTeacher getTeacher(String login) {
		String query = "SELECT * FROM nauczyciele WHERE login = '" + login + "'";
		String query_s = "SELECT * FROM przedmioty JOIN nauczyciele on przedmioty.idp = nauczyciele.idp WHERE nauczyciele.login = '"
				+ login + "'";
		ModelTeacher teacher = new ModelTeacher();
		ResultSet result;
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				teacher.setIdn(result.getInt("idn"));
				teacher.setName(result.getString("imie"));
				teacher.setLastname(result.getString("nazwisko"));
				teacher.setLogin(result.getString("login"));
				teacher.setPassword(result.getString("haslo"));
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			openCon();
			result = dbstat.executeQuery(query_s);
			while (result.next()) {
				ModelSubject subject = new ModelSubject();
				subject.setName(result.getString("nazwa"));
				subject.setIdp(result.getInt("idp"));
				teacher.setSubject(subject);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return teacher;
	}

	public ModelTeacher getTeacherByName(String name, String lastname) {
		String query = "SELECT idn FROM nauczyciele WHERE imie = '" + name + "' AND nazwisko = '" + lastname + "'";
		ResultSet result;
		ModelTeacher teacher = new ModelTeacher();
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				teacher.setIdn(result.getInt("idn"));
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return teacher;
	}

	public ModelTeacher getTeacher(int idn) {
		String query = "SELECT * FROM nauczyciele WHERE idn = " + idn;
		ResultSet result;
		ModelTeacher teacher = new ModelTeacher();
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				teacher.setIdn(result.getInt("idn"));
				teacher.setName(result.getString("imie"));
				teacher.setLastname(result.getString("nazwisko"));
				ModelSubject subject = new ModelSubject();
				subject.setIdp(result.getInt("idp"));
				subject.setName(getSubjectName(result.getInt("idp")));
				teacher.setSubject(subject);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return teacher;
	}

	public ArrayList<ModelTeacher> getTeachersBySubject(String subject) {
		String query = "SELECT * FROM nauczyciele JOIN przedmioty ON nauczyciele.idp = przedmioty.idp WHERE przedmioty.nazwa = '"
				+ subject + "'";
		ResultSet result;
		ArrayList<ModelTeacher> lt = new ArrayList<>();
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				ModelTeacher teacher = new ModelTeacher();
				teacher.setIdn(result.getInt("idn"));
				teacher.setLastname(result.getString("nazwisko"));
				teacher.setName(result.getString("imie"));
				teacher.setLogin(result.getString("login"));
				lt.add(teacher);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return lt;
	}

	public ModelStudent getStudent(String login) {
		String query = "SELECT * FROM uczniowie WHERE login = '" + login + "'";
		ModelStudent student = new ModelStudent();
		ResultSet result;
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				student.setIdu(result.getInt("idu"));
				student.setName(result.getString("imie"));
				student.setLastname(result.getString("nazwisko"));
				student.setLogin(result.getString("login"));
				student.setPassword(result.getString("haslo"));
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return student;
	}

	public ArrayList<ModelStudent> getStudentsById(int idn) {
		String query = "SELECT * FROM uczniowie join lekcje on uczniowie.idu = lekcje.idu WHERE lekcje.idn = " + idn;
		ResultSet result;
		ArrayList<ModelStudent> ls = new ArrayList<ModelStudent>();
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				ModelStudent student = new ModelStudent();
				student.setIdu(result.getInt("idu"));
				student.setLastname(result.getString("nazwisko"));
				student.setName(result.getString("imie"));
				student.setLogin(result.getString("login"));
				student.setPassword(result.getString("haslo"));
				ls.add(student);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return ls;
	}

	public ModelStudent getStudentById(int idu) {
		String query = "SELECT * FROM uczniowie WHERE idu = " + idu;
		ResultSet result;
		ModelStudent student = new ModelStudent();
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				student = new ModelStudent();
				student.setIdu(result.getInt("idu"));
				student.setLastname(result.getString("nazwisko"));
				student.setName(result.getString("imie"));
				student.setLogin(result.getString("login"));
				student.setPassword(result.getString("haslo"));
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return student;
	}

	public ArrayList<ModelStudent> getStudents() {
		String query = "SELECT * FROM uczniowie";
		ResultSet result;
		ArrayList<ModelStudent> ls = new ArrayList<ModelStudent>();
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				ModelStudent student = new ModelStudent();
				student.setIdu(result.getInt("idu"));
				student.setLastname(result.getString("nazwisko"));
				student.setName(result.getString("imie"));
				student.setLogin(result.getString("login"));
				student.setPassword(result.getString("haslo"));
				ls.add(student);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return ls;
	}

	public ArrayList<ModelSubject> getSubjects() {
		String query = "SELECT * FROM przedmioty";
		ResultSet result;
		ArrayList<ModelSubject> ls = new ArrayList<ModelSubject>();
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				ModelSubject subject = new ModelSubject();
				subject.setName(result.getString("nazwa"));
				ls.add(subject);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return ls;
	}

	public ArrayList<ModelTeacher> getTeachers() {
		String query = "SELECT * FROM nauczyciele";
		ResultSet result;
		ArrayList<ModelTeacher> lt = new ArrayList<ModelTeacher>();
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				ModelTeacher teacher = new ModelTeacher();
				;
				teacher.setIdn(result.getInt("idn"));
				teacher.setName(result.getString("imie"));
				teacher.setLastname(result.getString("nazwisko"));
				teacher.setLogin(result.getString("login"));
				teacher.setPassword(result.getString("haslo"));
				ModelSubject subject = new ModelSubject();
				subject.setIdp(result.getInt("idp"));
				subject.setName(getSubjectName(result.getInt("idp")));
				teacher.setSubject(subject);
				lt.add(teacher);
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return lt;
	}

	private void closeCon() {
		if (dbcon == null)
			return;
		try {
			dbcon.close();
		} catch (SQLException ex) {
			System.out.println("Problem z zamkniêciem bazy");
		}
	}

	public void addSubject(String nazwa) {
		String query = "INSERT INTO przedmioty (nazwa) VALUES ('" + nazwa + "')";
		try {
			openCon();
			dbstat.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public void deleteStudent(ModelStudent student) {
		String query = "DELETE FROM uczniowie WHERE idu = " + student.getIdu();
		try {
			openCon();
			dbstat.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public ModelStudent getStudentByName(String name, String lastname) {
		String query = "SELECT idu FROM uczniowie WHERE imie = '" + name + "' AND nazwisko = '" + lastname + "'";
		ResultSet result;
		ModelStudent student = new ModelStudent();
		try {
			openCon();
			result = dbstat.executeQuery(query);
			while (result.next()) {
				student.setIdu(result.getInt("idu"));
				closeCon();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
		return student;
	}

	public void deleteTeacher(ModelTeacher teacher) {
		String query = "DELETE FROM nauczyciele WHERE idn = " + teacher.getIdn();
		try {
			openCon();
			dbstat.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public void deleteLesson(ModelLesson lesson) {
		String query = "DELETE FROM lekcje WHERE idl = " + lesson.getIdl();
		try {
			openCon();
			dbstat.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public void deleteSubject(ModelSubject subject) {
		String query = "DELETE FROM przedmioty WHERE idp = " + subject.getIdp();
		try {
			openCon();
			dbstat.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public void deleteLessonByStudent(ModelStudent student) {
		String query_D = "DELETE FROM lekcje WHERE idu = " + student.getIdu();
		String query_C = "SELECT COUNT(idu) AS number FROM lekcje WHERE idu = " + student.getIdu();
		int count = 0;
		ResultSet result;
		try {
			openCon();
			result = dbstat.executeQuery(query_C);
			if (result.next()) {
				count = result.getInt("number");
				closeCon();
			}
			if (count == 1) {
				openCon();
				result = dbstat.executeQuery(query_D);
				if (result.next()) {
					dbstat.executeQuery(query_D);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public void deleteLessonBySubject(ModelSubject subject) {
		String query_D = "DELETE FROM lekcje WHERE idp = " + subject.getIdp();
		String query_C = "SELECT COUNT(idp) AS number FROM lekcje WHERE idp = " + subject.getIdp();
		int count = 0;
		ResultSet result;
		try {
			openCon();
			result = dbstat.executeQuery(query_C);
			if (result.next()) {
				count = result.getInt("number");
				closeCon();
			}
			if (count == 1) {
				openCon();
				result = dbstat.executeQuery(query_D);
				if (result.next()) {
					dbstat.executeQuery(query_D);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public void deleteLessonByTeacher(ModelTeacher teacher) {
		String query_D = "DELETE FROM lekcje WHERE idn = " + teacher.getIdn();
		String query_C = "SELECT COUNT(idn) AS number FROM lekcje WHERE idn = " + teacher.getIdn();
		int count = 0;
		ResultSet result;
		try {
			openCon();
			result = dbstat.executeQuery(query_C);
			if (result.next()) {
				count = result.getInt("number");
				closeCon();
			}
			if (count == 1) {
				openCon();
				result = dbstat.executeQuery(query_D);
				if (result.next()) {
					dbstat.executeQuery(query_D);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeCon();
		}
	}

	public static void main(String[] args) {
		// DaoKorepetycje dao = new DaoKorepetycje();
		// ArrayList<ModelTeacher> teachers = dao.getTeachers();
		/*
		 * for (int i = 0; i < teachers.size(); i++)
		 * System.out.println(teachers.get(i).getName() +
		 * teachers.get(i).getLastname());
		 */
	}

}
