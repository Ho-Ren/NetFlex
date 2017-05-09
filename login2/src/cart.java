import java.util.HashMap;

public class cart {
	
	private HashMap<String, Movie> cart;
	
	public cart()
	{
		cart = new HashMap<String, Movie>();
	}
	
	public HashMap<String, Movie> getMovies()
	{
		return cart;
	}
	
	public Movie addToCart(Movie m)
	{
		if(cart.containsKey(m.getTitle()))
		{
			Movie item = cart.get(m.getTitle());
			item.setQuantity(item.getQuantity() + 1);
			return item;
		}
		else
		{
			cart.put(m.getTitle(), m);
			return m;
		}
	}
	
	public void updateQuantity(Movie m, int quantity)
	{
		cart.get(m.getTitle()).setQuantity(quantity);
	}
	
	public void emptyCart()
	{
		cart.clear();
	}
	
	public void deleteFromCart(Movie m)
	{
		cart.remove(m.getTitle());
	}
}
