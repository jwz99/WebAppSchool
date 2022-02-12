package models;

import java.io.Serializable;
import java.time.LocalDate;

public class ModelLesson implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate date;
	private ModelStudent student;
	private ModelTeacher teacher;
	private int hour;
	private int idl;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ModelStudent getStudent() {
		return student;
	}

	public void setStudent(ModelStudent student) {
		this.student = student;
	}

	public ModelTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(ModelTeacher teacher) {
		this.teacher = teacher;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getIdl() {
		return idl;
	}

	public void setIdl(int idl) {
		this.idl = idl;
	}

}
