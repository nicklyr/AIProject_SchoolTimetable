package model;

import java.util.HashMap;
import java.util.Map;

public class Lesson {
	
	private int lessonid;
	private String lessonname;
	private Map<String, Integer> hoursperweek;
	
	public Lesson(){
		System.out.println("Creating new Lesson.....");
		hoursperweek = new HashMap<String, Integer>();
	}

	public int getLessonid() {
		return lessonid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public String getLessonname() {
		return lessonname;
	}

	public void setLessonname(String lessonname) {
		this.lessonname = lessonname;
	}

	public Map<String, Integer> getHourspeweek() {
		return hoursperweek;
	}

	public void setHourspeweek(HashMap<String, Integer> hoursperweek) {
		this.hoursperweek = hoursperweek;
	}
	
	

}
