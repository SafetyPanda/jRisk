
public class BlossomList implements Blossomable
{
	private BlossomNode head;
	private int moveAmount = 0;
	
	BlossomList ()
	{
		head = null;
	}
	
	
	public BlossomNode createPathList(int x, int y, double weight)
	{
		BlossomNode newNode = new BlossomNode (x, y, weight);
		newNode.setLink(head);
		head = newNode;
		System.out.println(newNode.getxCoord() + "," + newNode.getyCoord());
		this.moveAmount++;
		return head;
	}
	
	public void deleteFromHead()
	{
		head.getLink();
	}
	
	
	public void makeAMove(Territory territories[][], int yChoice, int xChoice)
	{
		int attacker;
		int defender;
		char attack = 'y';
		char defend = 'n';
		int dyingArmies;
		int remainingArmy;
		Player blossom = new Player(2); //temporary player for player 2
		
		BlossomNode temp = head;
		System.out.println(temp.getxCoord());
		while (temp != null)
		{	
				
			attacker = RiskGame.itsTimeToDuel(attack);
			defender = RiskGame.itsTimeToDuel(defend);
		
			if(attacker > defender)
			{
				RiskGame.absorbTerritory(blossom, territories, temp.getyCoord(), temp.getxCoord(), yChoice, xChoice);
				System.out.println("Blossom won: " + temp.getxCoord() + "," + temp.getyCoord());
			}
			else
			{
				System.out.println("Defender won!");
				
				dyingArmies = territories[yChoice][xChoice].getArmies();
				remainingArmy = dyingArmies - 5;
				if (remainingArmy < 1)
				{
					remainingArmy = 1;
				}
				territories[yChoice][xChoice].setArmies ( remainingArmy );	
			}
			moveAmount--;
			temp = temp.getLink();
			head.getLink();
		}
	}
	
}
