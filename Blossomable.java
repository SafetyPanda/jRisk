

public interface Blossomable 
{

	public void makeAMove(Territory territories[][], int yChoice, int xChoice);
	
	public BlossomNode createPathList(int x, int y, double weight);
	
	public void deleteFromHead();
	
}
