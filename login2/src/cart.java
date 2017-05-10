import java.util.HashMap;

public class cart {
	
	public static HashMap<String, MovieForCart> cart;
	
	public cart()
	{
		cart = new HashMap<String, MovieForCart>();
	}
	
	public static HashMap<String, MovieForCart> getMovieForCarts()
	{
		return cart;
	}
	
	public MovieForCart addToCart(MovieForCart m)
	{
		if(cart.containsKey(m.getTitle()))
		{
			System.out.println("addTocart containsKey (Title)");
			return m;
		}
		else
		{
			cart.put(m.getTitle(), m);
			System.out.println("addTocart not containsKey (title)");

			return m;
		}
	}
	
	public void updateQuantity(MovieForCart m, int quantity)
	{
		cart.get(m.getTitle()).setQuantity(quantity);
		System.out.println("addTocart setQuantity");

	}
	
	public void emptyCart()
	{
		cart.clear();
	}
	
	public void deleteFromCart(MovieForCart m)
	{
		cart.remove(m.getTitle());
	}
}
