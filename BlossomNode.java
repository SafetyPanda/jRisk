
public class BlossomNode 
{
	private int xCoord;
	private int yCoord;
	private double weight;
	private BlossomNode link;
	
	BlossomNode(int x, int y, double weight)
	{
		xCoord = x;
		yCoord = y;
		this.weight = weight;
		link = null;
	}
	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
	
	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public BlossomNode getLink() {
		return link;
	}

	public void setLink(BlossomNode link) {
		this.link = link;
	}
}
