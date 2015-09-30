/** This is my hashTable class. It creates and modifies the actual table
	that objects can be added to. It uses the code to place the object in the table
	
	@author Zachary Keller
	@version 9/29/15
*/


import java.lang.Math;

public class HashTable
{
/** Any percentage of occupancy above this is too much and will rehash */
	public static final double loadFactor = .6;
/** the actual table that the objects are being appended to*/
	private Object[] table;
/** the number of objects in the table, is used to determine whether the loadFactor is being exceeded*/
	private int occupied;

/** Default constructor. Initializes the table to capacity 100.	*/
	public HashTable()
	{
		table = new Object[100];
		occupied=0;
	}
	
/** Constructor with table length parameter
	@param c the length of the hashTable to be created
*/
	public HashTable(int c)
	{
		table = new Object[c];
		occupied=0;
	}


/** Puts the object in the hashtable. 
	Deals with collisions by placing the object using quadratic probing
	@param o  an object to be placed in the table
*/
	public void put(Object o)
	{
		int spot = (int)  o.hashCode();
		// System.out.println("Intial code is: " + spot);
		spot = spot% table.length;
		spot = Math.abs(spot);
		// System.out.println("Code after mod is: " + spot);
		// At this point, spot represents where the object will be placed
		// and change is how far the spot will be moved if there is an
		// object already in its spot
		int change = 0;
		boolean placed = false;
		while(placed == false)
		{
			int place= (spot + change) % table.length;
			if (table[place] instanceof Object )
			{
				if (change == 0)
					change += 1;
				else
					change = change * 2;
			}
			else
			{
				table[place] = o;
				placed = true;
				occupied += 1;
			}
		}
		/* USED FOR TESTING PURPOSES ONLY
		System.out.println("");
		System.out.println(this.toString());
		System.out.println("");
		System.out.println("Occupied: " + occupied);
		System.out.println("");
		*/
		double filled = ((double) occupied) / table.length;
		if (filled > loadFactor)
		{
			occupied = 0;
			rehash();
		}
		
		
		
	}
	
/** Makes the size of the HashTable the next prime number after double the size of the previous one
	and calls put for each item contained within the old hashTable
*/ 
	 private void rehash()
	 {
	 	Object [] holder = new Object[table.length];
	 	for (int i=0; i<table.length; i++)
	 	{
	 		holder[i] =table[i];
	 	}
	 	int biggerPrime = (table.length * 2) + 1;
	 	while (primeChecker(biggerPrime) == false)
	 		biggerPrime += 2;
	 	table = new Object[biggerPrime];
	 	for ( int j=0; j<holder.length; j++)
	 	{
	 		if (holder[j] instanceof Object)
	 			this.put(holder[j]);
	 	}
	 }
	
/** This determines whether a given number is a prime number.
	Admittedly, this algorithm is incredibly inefficient,
	but I did not really know any better ways of doing this.
	@param a the number to be checked fo whether it is prime
	@return whether or not the parameter is prime
*/
	 private boolean primeChecker(int a)
	 {
	 	for (int x=2; x<((int) Math.sqrt(a)) + 1; x++)
	 	{
	 		if (a%x == 0)
	 			return false;
	 	}
	 	return true;
	 }

/** Returns a string representation of the hash table, 
	complete with aesthetically pleasing dividers.

*/	 
	 public String toString()
	 {
	 	String s= "|";
	 	for (int i=0; i<table.length; i++)
	 	{
	 		// so that we can see the actual object, as opposed to its address
	 		if (table[i] instanceof Object)
	 			s= s+ table[i].toString();
	 		else
	 			s= s+ table[i];
	 		s=s+ "|";
	 	}
	 	return s;
	 }
	




}