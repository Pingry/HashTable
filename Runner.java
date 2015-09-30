public class Runner
{
	public static void main(String[] args)
	{
		HashTable table = new HashTable(70);
		table.put("English");
		table.put("Math");
		table.put("Science");
		table.put("Computer Science");
		table.put("Spanish");
		System.out.println(table);
	}
}