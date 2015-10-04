public class Runner
{
	public static void main(String[] args)
	{
		HashTable<Integer, String> list = new HashTable<Integer, String>(10);
		list.put(1, "potato");
		list.put(2, "tomato");
		list.put(4, "fruit");
		list.put(39, "hummus");
		list.put(6, "corn");
		list.put(15, "apple");
		list.put(21, "berry");
		list.put(21, "orange");
		list.put(52, "artichoke");
		list.put(-6, "pizza");
		list.put(-58, "mozzarella");
		list.put(46, "fruit punch");
		list.put(86, "lemon");
		list.remove(52);
		System.out.println(list.containsValue("tomat"));
		System.out.println(list.containsValue("tomato"));
		System.out.println(list.containsKey(4));
		System.out.println(list.containsKey(5));
		System.out.println(list.get(21));
		System.out.println(list.get(20));
		System.out.println(list.remove(15));
		System.out.println(list);
	}
}