
public class Blossom 
{	
	
		//Authored By: James Gillman
		//MethodName: projectBlossom
		//Parameters:
		//Return: moveChoice
		//Description: AI using weight to determine a move.
		public static void projectBlossom(Territory [][]territories)
		{
			int [][]blossomArray = new int [RiskGame.MAX_Y][RiskGame.MAX_X];
					
			cloneArray(blossomArray,territories);
			createStem(blossomArray, territories);
			
				
	
		}
		
		public static void cloneArray(int [][]blossomArray, Territory [][]territories)
		{
			for( int y = 0; y < RiskGame.MAX_Y; y++ ) //Get player owns from array.
			{	
				
				for ( int x = 0; x < RiskGame.MAX_X; x++)
				{	
					blossomArray[y][x] = territories[y][x].getPlayerOwns().getPlayerNumber();
				}
		
			}
			
			for( int y = 0; y < RiskGame.MAX_Y; y++ ) //filling player 1 spots with army size.
			{	
				for ( int x = 0; x < RiskGame.MAX_X; x++)
				{	
					if (blossomArray[y][x] == 2)
					{
						blossomArray[y][x] = -1;
					}
					else
					{
						blossomArray[y][x] = territories[y][x].getArmies();
					}
				}
			}
			
		}
		
		public static void createStem(int [][]blossomArray, Territory [][]territories)
		{
			double value = 0.0;
			double prob = 0.0;
			int bArmies;
			RiskGame.printBoard(blossomArray);
			for(int y = 0; y < RiskGame.MAX_Y; y++)
			{
				for (int x = 0; x < RiskGame.MAX_X; x++)
				{
					cloneArray(blossomArray, territories);
					if (blossomArray[y][x] == -1)
					{
						bArmies = territories[y][x].getArmies();
						prob = growPetals(y, x, blossomArray, territories, bArmies);
						System.out.println("(" + x + "," + y + ") Move Path Weight: " + prob);
						if (value < prob)
						{
							System.out.println("(" + x + "," + y + ") Move Path Weight: " + prob);
							value = prob;
						}
					}
				}
			}
		}
		
		
		public static double growPetals(int y, int x, int [][]blossomArray, Territory [][]territories, int bArmies)
		{
			
			double prob;
			
			if(territories[y][x].getArmies() == 0)
			{
				prob = 1;
			}
			else
			{
				
				prob = (bArmies / territories[y][x].getArmies() -.10);
			}
			
			blossomArray[y][x] = -1;
			
			if(y != 6 && blossomArray[y+1][x] != -1 && bArmies > 5)
			{
				System.out.println("GOING UP!");
				prob =+ growPetals(y+1, x, blossomArray, territories, bArmies -5);
			}
			else if (y != 0 && blossomArray[y-1][x] != -1 && bArmies > 5)
			{
				System.out.println("GOING DOWN!");
				prob += growPetals(y-1, x, blossomArray, territories, bArmies-5);
			}
			else if(x != 5 && blossomArray[y][x + 1] != -1 && bArmies > 5)
			{
				System.out.println("GOING RIGHT!");
				prob += growPetals(y, x, blossomArray, territories, bArmies-5);
			}
			else if(x != 0 && blossomArray[y][x-1] != -1 && bArmies > 5)
			{
				System.out.println("GOING LEFT!");
				prob += growPetals(y, x, blossomArray, territories, bArmies-5);
			}
			else if(x != 0 && y!=0 && blossomArray[y-1][x-1] != -1 && bArmies > 5)
			{
				System.out.println("GOING DOWN DIAGNOL!");
				prob += growPetals(y-1, x-1, blossomArray, territories, bArmies-5);
			}
			else if(x != 5 && y != 6 && blossomArray[y+1][x+1] != -1 && bArmies > 5)
			{
				System.out.println("GOING UP DIAGBNOL!");
				prob =+ growPetals(y+1, x+1, blossomArray, territories, bArmies-5);
			}
			else
			{
				return 0.0;
			}
			System.out.println(prob);
			return prob;
			
		}
			
		
}
