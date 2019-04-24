/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package domain;

public abstract class Person {
	private String idNumber;
	private String firstName;
	private String lastName;
	
	public Person(){
		this.idNumber="";
		this.firstName=" ";
		this.lastName=" ";
	}
	public Person(String idNumber, String firstName, String LastName){
		this.idNumber=idNumber;
		this.firstName=firstName;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public abstract void display();
}
