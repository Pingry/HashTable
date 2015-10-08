public class Runner
{
	public static void main(String[] args)
	{
		HashTable<Integer, String> table = new HashTable<Integer, String>(10);
		table.put(1, "1 Item");
		table.put(2, "2 Item");
		table.put(3, "3 Item");
		table.put(4, "4 Item");
		table.put(5, "5 Item");
		table.remove(2);
		
		System.out.println(table);
	}
}
