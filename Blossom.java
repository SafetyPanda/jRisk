
public class Blossom 
{	
	
		//Authored By: James Gillman
		//MethodName: projectBlossom
		//Parameters:
		//Return: moveChoice
		//Description: AI using weight to determine a move.
		public static int projectBlossom(int [][]blossomArray, Territory [][]territories)
		{
			int moveChoice = 0;
					
			cloneArray(blossomArray,territories);
			createStem(blossomArray, territories);
			
				
			return moveChoice;
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
			RiskGame.printBoard(blossomArray);
			for(int y = 0; y < RiskGame.MAX_Y; y++)
			{
				for (int x = 0; x < RiskGame.MAX_X; x++)
				{
					if (blossomArray[y][x] == -1)
					{
						prob = growPetals(y, x, blossomArray, territories);
						if (value < prob)
						{
							value = prob;
						}
					}
				}
			}
		}
		
		
		public static int growPetals(int y, int x, int [][]blossomArray, Territory [][]territories)
		{
			double prob = 0 - .10;
			
			//if(territories[][] != -1 && )
			
			
			return 0;
		}
			
		
}
