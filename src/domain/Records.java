/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package domain;

public abstract class Records {
	private String code;
	private String name;
	
	public Records(){
		this.code=" ";
		this.name=" ";
	}
	public Records(String code, String name){
		this.code=code;
		this.name=name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public abstract void display();
}
