/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package domain;

public class ProgrammeRecords extends Records{
	private int maxCourse;
	private String award;
	private String accreditation;
	private CourseRecords course;
	
	public ProgrammeRecords(){
		this.award=" ";
		this.accreditation=" ";
		this.course = new CourseRecords();
	}
	
	public ProgrammeRecords(String code, String name, int maxCourse, String award, String accreditation,CourseRecords course){
		super(code,name);
		this.course=course;
		this.maxCourse=maxCourse;
		this.award=award;
		this.accreditation=accreditation;
	}
	public void setcourse(CourseRecords course){
		this.course=course;
	}
	public CourseRecords getcourse(){
		return course;
	}
	public int getMaxCourse() {
		return maxCourse;
	}
	public String getAward() {
		return award;
	}
	public String getAccreditation() {
		return accreditation;
	}
	public void setMaxCourse(int maxCourse) {
		this.maxCourse = maxCourse;
	}
	public void setAward(String award) {
		this.award = award;
	}
	public void setAccreditation(String accreditation) {
		this.accreditation = accreditation;
	}
	
	public void display(){
		System.out.println("Programme Code: "+super.getCode()); 
		System.out.println("Programme Name: "+super.getName());
		System.out.println("Max Number of Courses: "+maxCourse);
		System.out.println("Award: "+award);
		System.out.println("Accreditation: "+accreditation);	
		course.display();
	}
}
