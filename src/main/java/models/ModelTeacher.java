package models;

import java.io.Serializable;

public class ModelTeacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String lastname;
	private String login;
	private String password;
	private int idn;
	private ModelSubject subject;

	public ModelSubject getSubject() {
		return subject;
	}

	public void setSubject(ModelSubject subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdn() {
		return idn;
	}

	public void setIdn(int idn) {
		this.idn = idn;
	}
}
