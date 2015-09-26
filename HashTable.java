public class HashTable
{
	public static final double loadFactor = .6;
	private Object[] table;
	private int occupied;
	
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
	
	public void put(Object o)
	{
		int spot = (int)  o.hashCode();
		System.out.println("Intial code is: " + spot);
		spot=spot% table.length;
		System.out.println("Code after mod is: " + spot);
		int change=0;
		boolean placed= false;
		while( placed == false)
		{
			System.out.println("change: " + change);
			System.out.println("spot: " + spot);
			if (table[spot + change] instanceof Object )
				change+=1;
			else
			{
				table[spot + change] = o;
				placed= true;
				occupied+=1;
			}
			if (change+spot==table.length)
				change= spot * -1;
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
	 private void rehash()
	 {
	 	System.out.println("Rehashing");
	 	Object [] holder = new Object[table.length];
	 	for (int i=0; i<table.length; i++)
	 	{
	 		holder[i]=table[i];
	 	}
	 	table= new Object[table.length * 2];
	 	for ( int j=0; j<holder.length; j++)
	 	{
	 		if (holder[j] instanceof Object)
	 			this.put(holder[j]);
	 	}
	 }
	 
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