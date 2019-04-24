/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package domain;



public class StaffRecords extends Person{
	private String faculty;
	private String department;
	private String employmentDate;
	
	public StaffRecords(){
		this.faculty=" ";
		this.department=" ";
		this.employmentDate = "";
	}
	public StaffRecords(String id, String firstName, String lastName,String faculty, String department, String employmentDate){
		super(id,firstName,lastName);
		this.faculty=faculty;
		this.department=department;
		this.employmentDate=employmentDate;
		
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setEmploymentDate(String employmentDate) {
		this.employmentDate = employmentDate;
	}

	public String getFaculty() {
		return faculty;
	}
	public String getDepartment() {
		return department;
	}
	public String getEmploymentDate() {
		return employmentDate;
	}
	public void display() {
		// TODO Auto-generated method stub
		
	}
}
