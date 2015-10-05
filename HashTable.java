/**
 * @author Billy Fallon
 * @version 10/6/15
 */

public class HashTable <K, V>
{
	/**
	 * class field array table
	 * Stores objects that have been hashed
	 */
	private Entry<K,V>[] table;
	
	private static final double LOAD_FACTOR = .6;
	
	private static double numFilled;
	
	/**
	 * Default constructor. Initializes to capacity 100
	 */
	
	@SuppressWarnings("unchecked")
	public HashTable()
	{
		table = new Entry[100];
		numFilled = 0;
		
	}
	
	/**
	 * Constructor. Initializes to capacity param int capacity
	 * 
	 * @param int that is desired capacity of HashTable
	 */
	
	@SuppressWarnings("unchecked")
	public HashTable(int capacity)
	{
		table = new Entry[capacity];
		numFilled = 0;
	}
	
	/**
	 * Puts a key-value entry in the hashtable. Deals with collisions 
	 * by placing the object in the next open spot 
	 * 
	 * @param Object obj	
	 * @return void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void put (K key, V value)
	{
		Entry E = new Entry (key, value);
		int num = key.hashCode();
		num=num%table.length;
		if(num<0)
		{
			num+=table.length;
		}
		if(table[num]==null)
		{
			numFilled+=1;
			table[num] = E;
		}
		else
		{
			for(int i = num;i<table.length;i++)
			{	
				if(table[i]==null)
				{
					table[i]=E;
					numFilled+=1;
					break;
				}
			}
				
		}
		if((numFilled/(double) table.length) >= LOAD_FACTOR)
			reHash();
		
	}
	
	/**
	 * String representation of the HashTable.
	 * 
	 * @param null
	 * @return String str which is a string representation of table array
	 */
	public String toString()
	{
		String str = "| ";
		for(int i = 0; i<table.length; i++)
		{
			if(table[i]!=null)
				str = str + table[i].value + " | ";
			else 
				str = str + "null" + " | ";
		}
		return str;
	}
	
	/**
	 * Doubles the size of the HashTable and rehashes each item contained within. 
	 * Should be called anytime calling the put function makes the current fill 
	 * of the HashTable exceed the load factor.
	 * 
	 * @return void 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void reHash()
	{
		Entry[] copy = new Entry[(table.length*2)];
		for(int i =0; i <table.length;i++)
		{
			copy[i] = table[i];
		}
		table = copy;
	}
	
	/**
	 * Removes the Entry with the corresponding key and returns its value. 
	 * Returns null if the key does not exist in the table.
	 * 
	 * @param key
	 * @return V
	 */
	
	public V remove(K key)
	{
		for(int i = 0; i<table.length; i++)
		{
			if(table[i]!=null&&table[i].key==key)
			{
				V v = table[i].value;
				table[i]=null;
				numFilled--;
				return v;
			}
		}
		return null;
	}
	/**
	 * Returns the value that corresponds to key. Returns null if the 
	 * key does not exist in the table.
	 * 
	 * @param key
	 * @return V
	 */
	public V get (K key)
	{
		for(int i = 0; i<table.length; i++)
		{
			if(table[i]!=null&&table[i].key==key)
			{
				return table[i].value;
			}
		}
		return null;
	}
	
	/**
	 * Returns whether or not key exists in the table.
	 * 
	 * @param k1
	 * @return boolean
	 */
	
	public boolean containsKey (K k1)
	{
		for(int i = 0; i<table.length; i++)
		{
			if(table[i]!=null&&table[i].key==k1)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns whether or not value exists in the table.
	 * 
	 * @param v1
	 * @return boolean
	 */
	
	public boolean containsValue (V v1)
	{
		for(int i = 0; i<table.length; i++)
		{
			if(table[i]!=null&&table[i].value==v1)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Nested class used to hold key-value pairings. Should have appropriate constructors and accessors as necessary.
	 */
	@SuppressWarnings("hiding")
	private class Entry <K,V>
	{
		public K key;
		public V value;
		/**
		 * Initializes Entry Object
		 * 
		 * @param k
		 * @param v
		 */
		public Entry (K k, V v)
		{
			key = k;
			value = v;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		@SuppressWarnings("rawtypes")
		HashTable table = new HashTable(5);
		String str1 = "str1";
		String str2 = "str2";
		String str3 = "str3";
		String str4 = "str4";
		String str5 = "str5";
		String str6 = "str6";
		String str7 = "str7";
		String str8 = "str8";
		String str9 = "str9";
		String str10 = "str10";
		table.put(str1, str2);
		table.put(str3, str4);
		table.put(str5, str6);
		table.put(str7, str8);
		table.put(str9, str10);
		System.out.println(table.containsValue(str2));
		System.out.println(table);
		System.out.println(table.get(str1));
		System.out.println(table.containsKey(str1));
		@SuppressWarnings("unused")
		String str11 = (String) table.remove(str5);
		System.out.println(table);	
	}
	
	
}
