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
		spot=spot% table.length;
		int change=0;
		boolean placed= false;
		while( placed == false)
		{
			System.out.println("change: " + change)
			System.out.println("spot: " + spot)
			if (!(table[spot + change] instanceof Integer ))
				change+=1;
			else
			{
				table[spot + change] = o;
				placed= true;
				occupied+=1;
			}
			if (change+spot>table.length)
				change= spot * -1;
		}
		double filled= ((double) occupied)/table.length;
		if (filled>loadFactor)
			rehash();
		
		
		
	}
	 private void rehash()
	 {
	 	Object [] holder = new Object[table.length];
	 	for (int i=0; i<table.length; i++)
	 	{
	 		holder[i]=table[i];
	 	}
	 	table= new Object[table.length * 2];
	 	for ( int j=0; j<holder.length; j++)
	 	{
	 		if (holder[j] instanceof Integer)
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