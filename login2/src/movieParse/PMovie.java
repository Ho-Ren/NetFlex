package movieParse;
import java.util.ArrayList;

public class PMovie {
	private String director;
	private String title;
	private String year;
	private ArrayList<String> categories;
	private String type;
	
	PMovie() {
		this.categories = new ArrayList<String>();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Movie details: ");
		sb.append("Director: " + director + ", ");
		sb.append("Title: " + title + ", ");
		sb.append("Year: " + year + ", ");
		sb.append("Categories: ");
		
		for(int i = 0; i < categories.size(); i++) {
			sb.append(categories.get(i));
			if(i != categories.size()-1)
				sb.append(", ");
		}
		
		return sb.toString();
	}

	public String getDirector() {
		if (director == null){
			this.director ="";
		}
		return director;
	}

	public void setDirector(String director) {
		if (director == null){
			this.director ="";
		}
		else{
		this.director = director;
	}}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		if(year.length()>4){
			return year.substring(0, 4);
		}
		else if(year.length()<4 || year==null || year.equals(""))
			return "1900";
		else
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}