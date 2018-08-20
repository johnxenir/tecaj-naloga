package naloga1;

public class ItemPair 
{
	private int key;
	private String value;
	
	public ItemPair(int k, String v)
	{
		key = k;
		value = v;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
	public int getKey()
	{
		return key;
	}
	
	public String getValue()
	{
		return value;
	}
	
}
