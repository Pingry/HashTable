/**

*/
// Yanni Angelides
// Creating a basic hash table object

import java.lang.Math;

public class HashTable<K,V>
{
	private double capacity = .6;
	private int size;
	private Entry<K,V>[] arr;
	private int numOfOccupiedSpaces = 0;
	private K k;
	private V v;
	
	public HashTable()
	{
		arr = new Entry<K,V>[100];
		size = 100;
	}
	
	/**
	Description 
	:) @ param obj Object that represents the Object the user wants to put in the hash table
	:) @ return 
	*/
	public void put(K k, V v)
	{
		Entry<K,V> entry = new Entry<K,V>(k,v);
		int spot = Math.abs(k.hashCode());
		while(arr[spot] != null)
		{
			spot++;
		}
		spot = spot % size;
		arr[spot] = entry;
		numOfOccupiedSpaces++;
		if ((numOfOccupiedSpaces/size) >= .6)
			rehash(); 
	}
	
	private void rehash()
	{
		size = size*2;
		Entry<K,V> arrCopy = new Entry<K,V>[size];
		int i = 0;
		while (i < arr.length)
		{
			arrCopy.put(arr[i].getKey(), arr[i].getValue());
		}
		arr = arrCopy;
	}
	
	public String toString()
	{
		String strOfArr = "";
		for (int i = 0; i < arr.length; i++)
		{
			strOfArr += arr[i].getValue().toString() + ", " + arr[i].getKey().toString();	
		}
		return strOfArr;
	}
	
	public V remove(K key)
	{
		int spot = Math.abs(key.hashCode());
		spot = spot % size;
		if (!(arr[spot].getKey().equals(key)))
		{
			if (search(spot) != null)
				return arr[search(spot)].getValue();
			else
				return null;
		}
	}
	
	public int search(K k, int position)
	{
		while (position < size)
		{
			if (arr[position].getKey().equals(k));
				return position;
		}
		return null;	 
	}
	
	public static void main(String [] args)
	{
		HashTable table = new HashTable();
		arr.put("Asbds");
		arr.put("hdjfsk");
		arr.put("jkrkjs");
		arr.put("dfsadsa");
		arr.toString();
	}
	
	private class Entry<K,V>
	{
		private K k;
		private V v;
		
		public Entry(K k, V v)
		{
			K = k;
			V = v;
		}
		
		public K getKey()
		{
			return k;
		}
		
		public V getValue()
		{
			return V;
		}
	}	
}






