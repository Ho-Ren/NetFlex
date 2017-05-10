public class MovieForCart {
    private String ID;
    private String title;
    private String year;
    private String director;
    private String genre;
    private String stars;
    private String trailer;
    private String pic;
    private int y;
    private int id;
    private int quantity;

    public MovieForCart (String id, String t, String y, String d, String g, String s) {
        this.ID = id;
        this.title = t;
        this.year = y;
        this.director = d;
        this.genre = g;
        this.stars = s;
        this.quantity = 1;
    }
    //I created a new constructor that will store the result from mysql with the format I need
     public MovieForCart(int id, String title,int year,  String director, String pic, String trailer){
             this.title = title;
            this.id = id;
            this.pic = pic;
            this.y = year;
            this.director = director;
            this.trailer = trailer;
            this.quantity = 1;
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
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getStars() {
        return stars;
    }
    public void setStars(String stars) {
        this.stars = stars;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    
}