public class Runner
{
	public static void main(String[] args)
	{
		HashTable list = new HashTable(10);
		list.put("potato");
		list.put("tomato");
		list.put("fruit");
		list.put("corn");
		list.put("apple");
		list.put("cherry");
		list.put(new String());
		list.put(new String());
		System.out.println(list);
	}
}