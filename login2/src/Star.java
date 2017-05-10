
public class Star {
	String id, firstName, lastName, DOB, photo, fullName;
	public Star(String id, String fullName){
		this.id = id;
		this.fullName = fullName;
	}
	public Star(String id, String firstName, String lastName, String DOB, String photo){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.DOB = DOB;
		this.photo = photo;
	}
	
	// To compare two Stars:
	public boolean equals(Star s) {
		if((s != null) && s instanceof Star) {
			if (this.id == s.id)
				return true;
			return false;
		}
		
		return false;
	}
	public String getId(){
		return id;
	}
	public String getfirstName(){
		return firstName;
	}
	public String getlastName(){
		return lastName;
	}
	public String getDOB(){
		return DOB;
	}
	public String getphoto(){
		return photo;
	}
	public String getfullName(){
		return fullName;
	}
}
