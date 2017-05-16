package xmlParse;

public class Actor {
	private String first, last,  dob;
	Actor(){
		
	}
	Actor(String first, String last, String dob){
		this.first = first;
		this.last = last;
		this.dob = dob;

	}
	public String getFirst(){
		return first;
	}
	public String getLast(){
		return last;
	}
	public String getDob(){
		return dob;
	}
//	public String getPic(){
//		return pic;
//	}
	public void setFirst(String f){
		first = f;
	}
	public void setLast(String l){
		last = l;
	}
	public void setDob(String d){
		dob = d;
	}
	
	public String toString() {
		String tempDob;
		StringBuffer sb = new StringBuffer();
		sb.append("Actor Details - ");
		sb.append("First:" + getFirst());
		sb.append(", ");
		sb.append("Last:" + getLast());
		sb.append(", ");
		if(getDob() == null)
			tempDob ="null";
		else tempDob = getDob();
		sb.append("Dob:" + tempDob);

		
		return sb.toString();
	}
	

}
