/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StudentRecords extends Person {
	private String address;
	private String dateEnrolled;
	private String programmeCode;
	private String enrolmentStatus;
	private String contactNumber;
	private CourseRecords course;
	
	
	public StudentRecords(){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = Calendar.getInstance().getTime();
		String dateEnrolled = df.format(today);
		this.address=" ";
		this.dateEnrolled =dateEnrolled;
		this.enrolmentStatus="0";
		this.contactNumber="";
		this.course = new CourseRecords();
	}
	public StudentRecords(String id,String firstName, String lastName,String address, String enrolmentStatus,String programmeCode, String contactNumber,CourseRecords course){
		super(id,firstName,lastName);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = Calendar.getInstance().getTime();
		String dateEnrolled = df.format(today);
		this.course=course;
		this.address=address;
		this.programmeCode=programmeCode;
		this.contactNumber=contactNumber;
		this.enrolmentStatus=enrolmentStatus;
		this.dateEnrolled=dateEnrolled;
	}
	
	public void setcourse(CourseRecords course){
		this.course=course;
	}
	public CourseRecords getcourse(){
		return course;
	}
 	public void setAddress(String address) {
		this.address = address;
	}
	public void setDateEnrolled(String dateEnrolled) {
		this.dateEnrolled = dateEnrolled;
	}
	public void setProgrammeCode(String programmeCode){
		this.programmeCode=programmeCode;
	}
	public void setEnrolmentStatus(String enrolmentStatus) {
		this.enrolmentStatus = enrolmentStatus;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getProgramCode(){
		return programmeCode;
	}
	public String getAddress() {
		return address;
	}
	public String getDateEnrolled() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = Calendar.getInstance().getTime();
		String dateEnrolled = df.format(today);
		return dateEnrolled;
	}
	public String getEnrolmentStatus() {
		return enrolmentStatus;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void display(){
		System.out.println("ID: "+super.getIdNumber());
		System.out.println("Name: "+super.getFirstName() +" "+super.getLastName());
		System.out.println("Address: "+address);
		System.out.println("Enrollment Status: "+enrolmentStatus);
		System.out.println("Programme Code: "+programmeCode);
		System.out.println("Date Enrolled: "+dateEnrolled);
		course.display();
		
	}
}
