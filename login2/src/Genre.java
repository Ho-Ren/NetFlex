
public class Genre {

	private String ID;
	private String name;
	
	Genre(String id, String n) {
		this.ID = id;
		this.name = n;
	}
	
	// To compare two Genres:
	public boolean equals(Genre g) {
		if ((g != null) && g instanceof Genre) {
			if (g.ID == this.ID)
				return true;
			return false;
		}
		
		return false;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
