
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
	@Override
	public boolean equals(Object s) {
		if ((s == null) || !(s instanceof Star))
			return false;
		if (s == this)
			return true;
		if (s instanceof Star) {
			if (((Star) s).id == this.id)
				return true;
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
