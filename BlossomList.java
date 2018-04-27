
public class BlossomList 
{
	private BlossomNode head;
	private BlossomNode tail;
	private int moveAmount = 0;
	
	BlossomList ()
	{
		head = null;
		tail = null;
	}
	
	
	public void createPathList(int x, int y, double weight)
	{
		BlossomNode new_node = new BlossomNode(x, y, weight);
		
		if(moveAmount == 0)
		{
			new_node = head;
			tail = head;
		}
		new_node.setLink(tail);
		tail = new_node;	
		moveAmount++;
	}
	
	public void deleteFromHead()
	{
		head.getLink();
	}
	
	public void deleteFromTail()
	{
		BlossomNode prev;
		
		prev = head;
		while (prev.getLink() != tail) 
		{
			prev.getLink();
		}
		tail = prev;
	}
	
}
