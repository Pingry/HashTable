import java.lang.Math;

public class HashTable
{
	public static final double loadFactor = .6;
	private Object[] table;
	private int occupied;
	//occupied is the number of spaces in the table that have been taken

//Default constructor. Initializes to capacity 100.	
	public HashTable()
	{
		table = new Object[100];
		occupied=0;
	}
	
	public HashTable(int c)
	{
		table = new Object[c];
		occupied=0;
	}

//Puts the object in the hashtable. 
//Deals with collisions by placing the object using quadratic probing

	public void put(Object o)
	{
		int spot = (int)  o.hashCode();
		System.out.println("Intial code is: " + spot);
		spot=spot% table.length;
		spot = Math.abs(spot);
		System.out.println("Code after mod is: " + spot);
		int change=0;
		boolean placed= false;
		while(placed == false)
		{
			int place=(spot + change)%table.length;
			System.out.println("change: " + change);
			System.out.println("spot: " + spot);
			if (table[place] instanceof Object )
			{
				if (change==0)
					change+=1;
				else
					change=change*2;
			}
			else
			{
				table[place] = o;
				placed= true;
				occupied+=1;
			}
		}
		/*
		System.out.println("");
		System.out.println(this.toString());
		System.out.println("");
		System.out.println("Occupied: " + occupied);
		System.out.println("");
		*/
		double filled= ((double) occupied)/table.length;
		if (filled>loadFactor)
		{
			occupied=0;
			rehash();
		}
		
		
		
	}
	
//Makes the size of the HashTable the next prime number after double the size of the previous one
// and rehashes each item contained within. 
	 private void rehash()
	 {
	 	System.out.println("Rehashing");
	 	Object [] holder = new Object[table.length];
	 	for (int i=0; i<table.length; i++)
	 	{
	 		holder[i]=table[i];
	 	}
	 	int biggerPrime= (table.length * 2) +1;
	 	while (primeChecker(biggerPrime)==false)
	 		biggerPrime+=2;
	 	table= new Object[biggerPrime];
	 	for ( int j=0; j<holder.length; j++)
	 	{
	 		if (holder[j] instanceof Object)
	 			this.put(holder[j]);
	 	}
	 }
	
//checks for the next prime, called in rehash 
//not so efficient
	 private boolean primeChecker(int a)
	 {
	 	for (int x=2; x<((int) Math.sqrt(a))+1; x++)
	 	{
	 		if (a%x==0)
	 			return false;
	 	}
	 	return true;
	 }

//String representation of the HashTable.	 
	 public String toString()
	 {
	 	String s= "";
	 	for (int i=0; i<table.length; i++)
	 	{
	 		s= s+ table[i] + "|";
	 	}
	 	return s;
	 }
	




}