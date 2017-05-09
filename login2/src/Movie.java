import java.util.ArrayList;

public class Movie {
	private String ID;
	private String title;
	private String year;
	private String director;
	private ArrayList<Genre> genre; // Making these arraylists makes things easier for hyperlinking
	private ArrayList<Star> stars;
	private ArrayList<String> genreList;
	private ArrayList<Star> starList;
	private String trailer;
	private String pic;
	private int y;
	private int id;
	private int quantity;

	public Movie (String id, String t, String y, String d) {
		this.ID = id;
		this.title = t;
		this.year = y;
		this.director = d;
		this.genre = new ArrayList<Genre>();
		this.stars = new ArrayList<Star>();
		this.quantity = 1;
	}
	
	//I created a new constructor that will store the result from mysql with the format I need
	 public Movie(int id, String title,int year,  String director, String pic, String trailer){this.title = title;
			this.id = id;
			this.pic = pic;
			this.y = year;
			this.director = director;
			this.trailer = trailer;
			this.stars = new ArrayList<Star>();
			this.genre = new ArrayList<Genre>();
			this.quantity = 1;
	}
	 
	 public Movie(int id, String title,int year,  String director, String pic, String trailer, ArrayList<String> genreList, ArrayList <Star> starList){
		 	this.title = title;
			this.id = id;
			this.pic = pic;
			this.y = year;
			this.director = director;
			this.trailer = trailer;
			this.starList = starList;
			this.genreList =genreList;
			this.quantity = 1;
	}
	
	public ArrayList<Star> getStarList(){
		return starList;
	}
	
	public ArrayList<String> getGenreList(){
		return genreList;
	}
	public String getPic(){
		return pic;
	}
	public String getTrailer(){
		return trailer;
	}
	public int getY(){
		return y;
	}
	public int getId(){
		return id;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		this.ID = iD;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public ArrayList<Genre> getGenre() {
		return genre;
	}
	public void setGenre(ArrayList<Genre> genre) {
		this.genre = genre;
	}
	
	public void addGenre(Genre g) {
		this.genre.add(g);
	}
	public ArrayList<Star> getStars() {
		return stars;
	}
	public void setStars(ArrayList<Star> stars) {
		this.stars = stars;
	}
	
	public void addStars(Star s) {
		this.stars.add(s);
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	
	@Override 
	public boolean equals(Object o) {
		if((o == null) || !(o instanceof Movie))
			return false;
		else {
			if(o instanceof Movie) {
				if (this == o)
						return true;
				else if(((Movie) o).id == this.id)
					return true;
			}
		}
		
		return false;
	}
	
}