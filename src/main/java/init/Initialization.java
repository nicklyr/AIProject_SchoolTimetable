package init;

import java.awt.List;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Lesson;

public class Initialization {

	public Initialization(String lessons,String teachers){
				
			parseXML(lessons);
			parseXML(teachers);
		
	}
	
	public void parseXML(String path){
		try {

			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			if (doc.getDocumentElement().getNodeName().equals("Lessons")){
				
				ArrayList<Lesson> lessonlist = new ArrayList<Lesson>();
				
				NodeList nList = doc.getElementsByTagName("Lesson");
				
				System.out.println("----------------------------");
	
				for (int temp = 0; temp < nList.getLength(); temp++) {
					
					Lesson lesson = new Lesson();
	
					Node nNode = nList.item(temp);
	
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
	
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
						Element eElement = (Element) nNode;
	
						System.out.println("Lesson Id : " + eElement.getAttribute("Id"));
						System.out.println("Lesson Name : " + eElement.getAttribute("Name"));
						
						lesson.setLessonid(Integer.parseInt(eElement.getAttribute("Id")));
						lesson.setLessonname(eElement.getAttribute("Name"));
						
						NodeList hoursList = eElement.getElementsByTagName("HoursPerClass");
						
						HashMap<String, Integer> hoursperweek = new HashMap<String, Integer>();
						
						for (int i = 0; i < hoursList.getLength(); i++){
							
							Node hoursNode = hoursList.item(i);
							System.out.println("Current Element :" + hoursNode.getNodeName());
							
							if (hoursNode.getNodeType() == Node.ELEMENT_NODE) {
								
								Element hoursElement = (Element) hoursNode;
								
								System.out.println("Class : " + hoursElement.getAttribute("Class"));
								System.out.println("Hours : " + hoursElement.getAttribute("Hours"));
								
								hoursperweek.put(hoursElement.getAttribute("Class"), Integer.parseInt(hoursElement.getAttribute("Hours")));
							}
						}
						
						lesson.setHourspeweek(hoursperweek);
					}
					lessonlist.add(lesson);
				}
			}else if (doc.getDocumentElement().getNodeName().equals("Teachers")){
				NodeList nList = doc.getElementsByTagName("Teacher");
	
				System.out.println("----------------------------");
	
				for (int temp = 0; temp < nList.getLength(); temp++) {
	
					Node nNode = nList.item(temp);
	
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
	
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
						Element eElement = (Element) nNode;
	
						System.out.println("Teacher Id : " + eElement.getAttribute("Id"));
						System.out.println("Teacher Name : " + eElement.getAttribute("Name"));
						System.out.println("Max Hours Per Week : " + eElement.getAttribute("MaxHoursPerWeek"));
						
						Node lesscanteach = eElement.getElementsByTagName("LessonsCanTeach").item(0);
						if(lesscanteach.getNodeType() == Node.ELEMENT_NODE){
							Element lesscanteachel = (Element) lesscanteach;
						
						
							NodeList lessonsList = lesscanteachel.getElementsByTagName("Lesson");
							
							for (int i = 0; i < lessonsList.getLength(); i++){
								
								Node lessonsNode = lessonsList.item(i);
																
								if (lessonsNode.getNodeType() == Node.ELEMENT_NODE) {
									
									Element lessonsElement = (Element) lessonsNode;
									
									System.out.println("Lesson Ids can teach : " + lessonsElement.getAttribute("Id"));
								}
							}
						}
						
						Node maxhoursday = eElement.getElementsByTagName("MaxHoursPerDay").item(0);
						if(maxhoursday.getNodeType() == Node.ELEMENT_NODE){
							Element maxhoursdayel = (Element) maxhoursday;
							
							NodeList dayList = maxhoursdayel.getElementsByTagName("Day");
							
							for (int j = 0; j < dayList.getLength(); j++){
								
								Node dayNode = dayList.item(j);
															
								if (dayNode.getNodeType() == Node.ELEMENT_NODE) {
									
									Element dayElement = (Element) dayNode;
									
									System.out.println("Day : " + dayElement.getAttribute("Name") + " ==> MaxHours : " + dayElement.getAttribute("Hours"));
								}
							}
						}
																		
//						NodeList hoursList = eElement.getElementsByTagName("MaxHoursPerDay");
//						
//						for (int i = 0; i < hoursList.getLength(); i++){
//							
//							Node hoursNode = hoursList.item(i);
//							System.out.println("Current Element :" + hoursNode.getNodeName());
//							
//							if (hoursNode.getNodeType() == Node.ELEMENT_NODE) {
//								
//								Element hoursElement = (Element) hoursNode;
//								
//								System.out.println("Lesson Id : " + hoursElement.getAttribute("Day"));
//								System.out.println("Lesson Name : " + hoursElement.getAttribute("Hours"));
//							}
//						}
						
//						System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//						System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//						System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
	
					}
				}
			}
			else{
				throw new Exception("Illegal XML Format");
			} 
		    } catch (Exception e) {
			e.printStackTrace();
		    }
	}
	
	
}
