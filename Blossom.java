import java.util.Scanner;

public class Blossom 
{	
	public static Scanner input = new Scanner (System.in);
		//Authored By: James Gillman
		//MethodName: projectBlossom
		//Parameters:
		//Return: moveChoice
		//Description: AI using weight to determine a move.
		public static void projectBlossom(Territory [][]territories, int armies, int turn)
		{
			int [][]blossomArray = new int [RiskGame.MAX_Y][RiskGame.MAX_X];
			cloneArray(blossomArray,territories);	
			distributeArmies(blossomArray, territories, armies);
			
			if (turn > 1)
			{
				createStem(blossomArray, territories);
			}
		}
		
		public static void distributeArmies(int [][]blossomArray, Territory [][]territories, int armies)
		{
			while (armies != 0)
			{
				for( int y = 0; y < RiskGame.MAX_Y; y++ ) //filling player 1 spots with army size.
				{	
					for ( int x = 0; x < RiskGame.MAX_X; x++)
					{	
						if (blossomArray[y][x] == -1)
						{
							if (armies > 0)
							{
								territories[y][x].setArmies(territories[y][x].getArmies() + 1);
								armies--;
							}
						}
					}
				}
			}
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
			int bestY = 0;
			int bestX = 0;
			RiskGame.printBoard(blossomArray);
			BlossomList tempList= new BlossomList();
			BlossomList finalList = new BlossomList();

			//BlossomList secondMove = new BlossomList();
			for(int y = 0; y < RiskGame.MAX_Y; y++)
			{
				for (int x = 0; x < RiskGame.MAX_X; x++)
				{
					cloneArray(blossomArray, territories);
					if (blossomArray[y][x] == -1)
					{
						bArmies = territories[y][x].getArmies();
						prob = growPetals(y, x, blossomArray, territories, bArmies, tempList);					
						if (value < prob)
						{
							System.out.println("(" + x + "," + y + ") Move Path Weight: " + prob);
							value = prob;
							bestY = y;
							bestX = x;
							
							input.nextLine();
						}
					}
				}
			}
			cloneArray(blossomArray, territories);
			prob = growPetals(bestY, bestX, blossomArray, territories, territories[bestY][bestX].getArmies(), finalList);
			finalList.makeAMove(territories, bestY, bestX);
		}
		
		
		public static double growPetals(int y, int x, int [][]blossomArray, Territory [][]territories, int bArmies, BlossomList firstMove)
		{
			int possibleLoss = 3;
			
			double prob;
			
			if(territories[y][x].getArmies() == 0)
			{
				prob = 1;
			}
			else
			{
				
				prob = (bArmies / territories[y][x].getArmies() -.10);
			}
			
			firstMove.createPathList(x,y,prob);
			
			blossomArray[y][x] = -1;
			
			if(y != 6 && blossomArray[y+1][x] != -1 && bArmies > 3)
			{
				prob += growPetals(y+1, x, blossomArray, territories, bArmies - possibleLoss, firstMove);
			}
			else if (y != 0 && blossomArray[y-1][x] != -1 && bArmies > possibleLoss)
			{
				prob += growPetals(y-1, x, blossomArray, territories, bArmies-possibleLoss, firstMove);
			}
			else if(x != 5 && blossomArray[y][x + 1] != -1 && bArmies > possibleLoss)
			{
				prob += growPetals(y, x+1, blossomArray, territories, bArmies-possibleLoss, firstMove);
			}
			else if(x != 0 && blossomArray[y][x-1] != -1 && bArmies > possibleLoss)
			{
				prob += growPetals(y, x-1, blossomArray, territories, bArmies-possibleLoss, firstMove);
			}
			else if(x != 0 && y!=0 && blossomArray[y-1][x-1] != -1 && bArmies > possibleLoss)
			{
				prob += growPetals(y-1, x-1, blossomArray, territories, bArmies-possibleLoss, firstMove);
			}
			else if(x != 5 && y != 6 && blossomArray[y+1][x+1] != -1 && bArmies > possibleLoss)
			{
				prob += growPetals(y+1, x+1, blossomArray, territories, bArmies-possibleLoss, firstMove);
			}
			else if(x != 0 && y!=6 && blossomArray[y+1][x-1] != -1 && bArmies > possibleLoss)
			{
				prob += growPetals(y+1, x-1, blossomArray, territories, bArmies-possibleLoss, firstMove);
			}
			else if(x != 5 && y != 0 && blossomArray[y-1][x+1] != -1 && bArmies > possibleLoss)
			{
				prob += growPetals(y-1, x+1, blossomArray, territories, bArmies-possibleLoss, firstMove);
			}
			else
			{
				return 0.0;
			}
			System.out.println(prob);
			return prob;
			
		}
			
}
