//Maddie Temares
//September 28

public class HashTable
{
	private Object[] arr;
	private double loadfactor = 0.6;
	private double percentFilled = 0.0;
	private int numFilled = 0;
	
	//Default constructor. Initializes to capacity 100.
	public HashTable()
	{
		arr = new Object[100];
	}
	
	public HashTable(int capacity)
	{
		arr = new Object[capacity];
	}
	
	//Puts the object in the hashtable. Deals with collisions by placing the object in the
	// next open spot.
	public void put(Object obj)
	{
		int location = obj.hashCode()%arr.length;
		if (location < 0)
		{
			location = location +arr.length;
		}
		while (arr[location] != null)
		{
			location++;
		}
		if (location < 0)
		{
			location = location +100;
		}
		arr[location] = obj;
		numFilled++;
		percentFilled = (numFilled)/(arr.length);
		if (percentFilled >= loadfactor)
		{
			rehash();
		}
	}
	
	//String representation of the HashTable.
	public String toString()
	{
		String s = "";
		for (Object o: arr)
			s += o + " ";
		return s;
	}
	
	//Doubles the size of the HashTable and rehashes each item contained within. Should be
	// called anytime calling the put function makes the current fill of the HashTable 
	//exceed the load factor.
	private void rehash()
	{
		Object[] old = new Object[arr.length];
		for (int x =0; x<arr.length; x++)
		{
			old[x] = arr[x];
		}
		int newSize = arr.length * 2;
		arr = new Object[newSize];
		for (Object o: old)
		{
			if (o!= null)
			{
				put(o);
			}
		}
	}
	
	public static void main(String[] args)
	{
		HashTable names = new HashTable();
		names.put("A");
		names.put("Daria");
		names.put("Dariasd");
		names.put("B");
		names.put("C");
		System.out.println(names);
	}
}
