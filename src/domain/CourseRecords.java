/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/

package domain;

public class CourseRecords extends Records{
	private String description;
	private int credits;
	private String preRequisite;
	
	public CourseRecords(){
		this.description=" ";
		this.credits=0;
		this.preRequisite=" ";
	}
	public CourseRecords(String code, String name, String description, int credits,String preRequisite){
		super(code,name);
		this.description=description;
		this.credits=credits;
		this.preRequisite=preRequisite;
	}
	
	public String getDescription() {
		return description;
	}
	public int getCredits() {
		return credits;
	}
	public String getPreRequisite() {
		return preRequisite;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}
	public void display(){
		String[] pre =preRequisite.split("\\s");
		String[] name =super.getName().split("\\s");
		String[] code =super.getCode().split("\\s");
		
		System.out.println("Course Description: "+description);
		System.out.println("Credtis: "+credits);
		
		System.out.println("Course Codes:");
		for(String c:code){
			System.out.println(c);
		}
		
		System.out.println("Course Names:");
		for(String n:name){
			System.out.println(n);
		}
		
		System.out.println("Pre-Requisites:");
		for(String p:pre){
			System.out.println(p);
		}
	}
}
