
public class Genre {

	private String ID;
	private String name;
	
	Genre(String id, String n) {
		this.ID = id;
		this.name = n;
	}
	
	// To compare two Genres:
	@Override
	public boolean equals(Object g) {
		if ((g == null) || !(g instanceof Genre))
			return false;
		if (g == this)
			return true;
		if (g instanceof Genre) {
			if (((Genre) g).ID == this.ID)
				return true;
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
