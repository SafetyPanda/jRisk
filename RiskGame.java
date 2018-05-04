//James Gillman
//Dec 28th 2017
//Final Project
//Contains all of the rule set for jRisk and how its played.

import java.util.Scanner;

public class RiskGame
{
	
	public static int MAX = 42; //how many territories we have.
	public static int MAX_Y = 7;//size of array y axis of 2D array
	public static int MAX_X = 6;//size of array x axis of 2D array
	
	public static Scanner input = new Scanner (System.in);
	
	
	//Author: James Gillman
	//MethodName: playGame
	//Parameters: none
	//Return: N/A
	//Description: Plays a game of risk! Initializes to Arrays and goes into the methods to set up game and actually play game.
	public static void playGame()
	{
		char answer = 'n'; //Does the user want to see rules?
		int players = 0;
		
		
		
		Player[] playerList = new Player[2];
		Territory[][] territories = new Territory[MAX_Y][MAX_X];
		int [][]blossomArray = new int[MAX_Y][MAX_X];
		
		makeLogo();
		input.nextLine();
		System.out.println("Want to see the rules? (y/n)");
		answer = input.next().charAt ( 0 );
		if (answer == 'y')
		{
			rules();
		}
		
		//System.out.println("How many people are playing?");
		//players = input.nextInt ( );
		
		preGame(playerList, territories, players);
		actualGame(playerList, territories);
		credits();
		
	}
	
	//Author:James Gillman
	//MethodName:preGame
	//Parameters:playerList- array of players territories- Array of territories
	//Return: N/A
	//Description: Sets up the game, creates players, creates territories, and sets the initial armies.
	public static void preGame(Player[] playerList, Territory[][] territories, int players)
	{
		createPlayers(playerList, players);
		createTerritory(playerList, territories);
		setInitialArmies(playerList[0], playerList[1], territories);
	}
	
	//Author: Isaac Haas
	//Modified by James Gillman - 
	//MethodName: createPlayers
	//Parameters: playerList
	//Return:N/A
	//Description: Creates an array of players.
	public static void createPlayers(Player []playerList, int players)
	{	
		//int playerNum = 1;
		//for (int i = 0; i < players; i++)
		//{
			//playerList[0] = new Player(playerNum);
			//playerNum++;
		//}
			
		playerList [0] = new Player (1);
		playerList[1] = new Player (2);
	}
	
	//Author: James Gillman
	//MethodName: getPlayer
	//Parameters:player - who is playing? playerList - array of players.
	//Return: int player
	//Description: Switches players!
	public static int getPlayer(Player []playerList, int player)
	{
		
		if (player != 1)
		{
			player = playerList[0].getPlayerNumber();
		}
		else
		{
			player = playerList[1].getPlayerNumber ();
		}
		return player;
	}
	
	//Author: James Gillman
	//Modified by: Isaac Haas
	//MethodName: createTerritory
	//Parameters:playerList-  array of players. Territories- array of territories.
	//Return:N/A
	//Description:Fills the array with the class Territory
	public static void createTerritory(Player []playerlist, Territory [][]territories)
	{
		for (int y = 0; y < MAX_Y; y++)
		{
			for (int x = 0; x < MAX_X; x++)
			{
				territories[y][x] = new Territory();
			}
		}	
	}
	
	//Author:Isaac Haas
	//Modified by James Gillman
	//MethodName:setInitialArmies
	//Parameters: p1 - player1 info, p2 - player2 info, territories- array of territories.
	//Return: None.
	//Description: Assigns each player a territory, by randomly choosing a set of coordinates, alternating between the players, then giving each territory one army, and setting what the coordinates are.
	public static void setInitialArmies(Player p1, Player p2, Territory [][] territories)
	{
		int randX; //randomly chosen x coordinate
		int randY; //randomly chosen y coordinate
		
		for (int i = 1; i <= MAX; i++)
		{
			randY = (int)(Math.random() * MAX_Y); 
			randX = (int)(Math.random() * MAX_X);
			
			while(territories[randY][randX].getPlayerOwns ( ) != null)
			{
				randY = (int)(Math.random() * MAX_Y);
				randX = (int)(Math.random() * MAX_X);
			}
			territories[randY][randX].setPlayerOwns( (i%2 == 0)? p1:p2);//i%2 == 0)? p1:p2
			territories[randY][randX].setArmies ( (int) (Math.random()* 8) );
			territories[randY][randX].setxCoord ( randX); // Added by James
			territories[randY][randX].setyCoord ( randY); // Added by James
		
			//System.out.println ( territories[randY][randX].getPlayerOwns ( ).getPlayerNumber ( ) + " " + territories[randY][randX].getArmies ( ));
		}	
	}
	
	//Author: James Gillman
	//MethodName: actualGame
	//Parameters: playerList- Array of Players. territories- Array of territories.
	//Return: N/A
	//Description: Runs the actual game! Lets player get into makeAMove method, checks to see if game is done and prints out board.
	public static void actualGame(Player[] playerList, Territory[][] territories)
	{
		boolean gameOver = false; // Is game over?
		int player = 0; //Which player is playing?
		int turn = 1; //Used to prevent players from doing anything else other than place armies on their first turn useless after the fact.
		do
		{
			if (player == 2)
			{
				turn++;
				Blossom.projectBlossom(territories);
				
			}
			printBoard(territories);
			player = getPlayer(playerList,player);
			distributeArmies(playerList, territories, turn);
			
			//Time to run through a players move!
			chooseTerritory(player, territories, playerList, turn);
			
			gameOver = gameOverCheck(territories, turn);
			
		}while (gameOver == false);	
	}
	
	//Author: James Gillman
	//Original Implementation: Isaac Haas
	//MethodName: printBoard
	//Parameters: territories - array of territories
	//Return: None
	//Description: Prints a very basic board. Just to show that game works. Eventually will be replaced into a GUI.
	public static void printBoard(Territory [][]territories)
	{
		System.out.print ( "     ");
		for( int x = 0; x < MAX_X; x++)
		{
			System.out.print (x + "   ");
		}
		System.out.println();
		System.out.println("___________________________");
		for( int y = 0; y < MAX_Y; y++ )
		{
			System.out.print(y + " | ");
			
			for ( int x = 0; x < MAX_X; x++)
			{
				
				System.out.print(territories[y][x].getPlayerOwns().getPlayerNumber() + "," + territories[y][x].getArmies() + " ");
			}
			
			System.out.println ( );
		}	
	}
	
	public static void printBoard(int [][]territories)
	{
		System.out.print ( "     ");
		for( int x = 0; x < MAX_X; x++)
		{
			System.out.print (x + "   ");
		}
		System.out.println();
		System.out.println("___________________________");
		for( int y = 0; y < MAX_Y; y++ )
		{
			System.out.print(y + " | ");
			
			for ( int x = 0; x < MAX_X; x++)
			{
				
				System.out.print(territories[y][x]+ "  ");
			}
			
			System.out.println ( );
		}	
	}
	
	//Author: James Gillman
	//MethodName: distributeArmies
	//Parameters: playerlist - array of players. territories- Array of territories turn - what turn is it?
	//Return: None
	//Description: Gives Armies to players.
	public static void distributeArmies(Player []playerList, Territory [][]territories, int turn)
	{
		int whoOwns; //Who owns the territory?
		int player1Owns = 0; // PLayer 1's amount of territory they own.
		int player2Owns = 0; // Player 2's amount of territory they own.
		
		int player1Divided; //Players armies divided  into the right reinforcement rate.
		int player2Divided; //Players armies divided into the right reinforcement rate.
		
		for( int y = 0; y < MAX_Y; y++ )
		{	
			for ( int x = 0; x < MAX_X; x++)
			{	
				whoOwns = territories[y][x].getPlayerOwns().getPlayerNumber();
				
				if (whoOwns == 1)
				{
					player1Owns ++;
				}
				else
				{
					player2Owns++;
				}
			}
			
		}	
		
		player1Divided = player1Owns / 3;
		player2Divided = player2Owns / 3;
		
		if (player1Divided < 3)
		{
			player1Divided = 3;
		}
		
		if(player2Divided  < 3)
		{
			player2Divided =3;
		}
		
		if (turn == 1)
		{
			player1Divided = 40;
			player2Divided = 40;
		}
		
		playerList[0].setArmies ( player1Divided);
		playerList[1].setArmies ( player2Divided);	
	}
	
	//Author:James Gillman
	//MethodName: chooseTerritory
	//Parameters: player - which player is playing? territories - array of territories, playerlist - array of players, turn - what turn is it?
	//Return: None
	//Description: Lets player choose territory. Checks to make sure it's actually theirs, if it is their territort lets them access makeAMove.
	public static void chooseTerritory(int player, Territory [][]territories, Player []playerList, int turn)
	{
		int yChoice; // What Y-Coordinate did they choose?
		int xChoice; //What X-coordinate did they choose?
		char mChoice = 'c'; //What move choice have they chosen?
		System.out.println("Player " + player + " It is your turn! You're on turn " + turn + ".");
		System.out.println("You're territories have trained " + playerList[player - 1].getArmies ( ) + " armies for you to reinforce our Motherland with.");
		do
		{
			
			System.out.println("Choose x-Axis.");
			xChoice = input.nextInt();
			
			System.out.println("Choose y-Axis.");
			yChoice = input.nextInt ( );
			
			if( xChoice < MAX_X && xChoice > -1)
			{
				if(yChoice < MAX_Y && yChoice > -1)
				{
					if(player == territories[yChoice][xChoice].getPlayerOwns().getPlayerNumber ( ))
					{
						mChoice = makeAMove(yChoice, xChoice, player, territories, playerList, turn);
					}
			
					else
			
					{
						System.out.println("Thats not yours sir!, try again");
						printBoard(territories);
					}
				}
				else
				{
					System.out.println("Not a valid, input!");
				}
			}
			else
			{
				System.out.println("Not a valid input!");
			}
		}while (mChoice != 'q' || mChoice == 'c');	
	}

	//Author:James Gillman
	//MethodName:makeAMove
	//Parameters:ychoice - y coordinate user chose. xChoice - x coordinate user chose. player - which player is playing? territories- array of territories, playerList - array of players. turn - what turn is it. 
	//Return: char mChoice - what move did they make?
	//Description: Lets user choose what moves they want to do. On first turn only allows placement. Gives a menu of options.
	public static char makeAMove(int yChoice, int xChoice, int player, Territory [][]territories, Player [] playerList, int turn)
	{
		
		char mChoice = 'a'; // Player move choice.
		
		if (turn == 1)
		{
			System.out.println("This is your first turn sir! You can only place armies and choose other territories at the moment!"); 
		}
		
		while(mChoice != 'c' && mChoice != 'q')
		{
			System.out.println ( "What do you want to do? Place Armies? (p) Move (m), Attack (a), choose another territory? (c) or end turn? (q)");
			System.out.println("Currenly selected coordinates is: (" + xChoice + ", " + yChoice + ")");
			mChoice = input.next ( ).charAt ( 0 );
			if(mChoice == 'p')
			{
				placeArmies(yChoice, xChoice, player, territories, playerList);
			}
			else if(mChoice == 'm' && turn != 1)
			{
				System.out.println("Move armies to where?");
				moveArmies(yChoice, xChoice, player, territories, playerList);
					
			}
			else if(mChoice == 'a' && turn != 1)
			{
				System.out.println("Attack which territory?");
				attackOpponent(yChoice, xChoice, player, territories, playerList);	
			}
			else if (mChoice != 'q' && mChoice != 'c')
			{
				if(turn == 1)
				{
					System.out.println("You're still on turn one! You can't do that just yet! Either place your armies (p), or end your turn! (q)");
				}
				else 
				{
					System.out.println("NOT A VALID MOVE. Try Again.");
				}
			
			}
			printBoard(territories);
		}
		return mChoice;	
	}

	//Author: James Gillman
	//MethodName: placeArmies
	//Parameters:yChoice - y coordinate user chose. xChoice - x coordinate user chose. player - which player is playing? territories- array of territories, playerList - array of players.
	//Return: N/A
	//Description: Lets user place armies in the territory they previously selected. If they don't have any lefyt tells them that they don't and kicks them back to the choice menu.
	public static void placeArmies(int yChoice, int xChoice, int player, Territory [][]territories, Player [] playerList)
	{
		int placeArmies; //Place armies into Territory
		int currentArmies; //Find out currentArmies on territory.
		int removeArmies; //Remvove armies from territory.
		player--;
		printBoard(territories);
		if (playerList[player].getArmies() != 0)
		{
			System.out.println ( "How Many do you want to place here? Remaining: " + playerList[player].getArmies());
			currentArmies = territories[yChoice][xChoice].getArmies();
			placeArmies = input.nextInt ( );
		
			while(placeArmies > playerList[player].getArmies ( ) + 1)
			{
				System.out.println("Choose again! You have: " + playerList[player].getArmies() + " Armies.");
				placeArmies = input.nextInt ( );
			}
			removeArmies = playerList[player].getArmies ( );
		
			removeArmies = removeArmies - placeArmies;
			placeArmies = placeArmies + currentArmies;
		
			playerList[player].setArmies ( removeArmies );
			territories[yChoice][xChoice].setArmies(placeArmies);
			printBoard(territories);
		}
		
		else
		{
			System.out.println("You have no Armies available to place!");
		}
	}

	//Author: James Gillman
	//MethodName: moveArmies
	//Parameters: yChoice - y coordinate user chose. xChoice - x coordinate user chose. player - which player is playing? territories- array of territories, playerList - array of players.
	//Return: N/A
	//Description: Lets player move armies from previously selected territory to a new one thats theirs ensuring that they keep at least one army in the old territory.
	public static void moveArmies(int yChoice, int xChoice, int player, Territory [][]territories, Player [] playerList)
	{
		int moveArmies; //How many armies are we moving?
		int currentArmies; //Find out currentArmies on territory.
		int removeArmies; //Remvove armies from territory.
		int y = -1; //Targeted y Coordinate
		int x = -1; //Targeted x coordinate.
		player--;
		
		printBoard(territories);
		if (playerList[player].getArmies() != 0)
		{
			currentArmies = territories[yChoice][xChoice].getArmies() - 1;
			System.out.println ( "How Many do you want to move? You have to keep at least one army on your Occupied Territory. Total Available: " + currentArmies );
			
			System.out.println(currentArmies);
			moveArmies = input.nextInt ( );
		
			while(moveArmies > currentArmies + 1)
			{
				System.out.println("Choose again! You have: " + currentArmies +  " Armies.");
				moveArmies = input.nextInt ( );
			}
			removeArmies = currentArmies;
			removeArmies = removeArmies - moveArmies;
			
			System.out.println("Move to where?");
			printBoard(territories);
			System.out.println("X-Axis?");
			x = input.nextInt ( );
			
			System.out.println("Y-Axis?");
			y = input.nextInt ( );
			while(player + 1 != territories[y][x].getPlayerOwns().getPlayerNumber ( ))
			{
				System.out.println("Thats not your Territory!, choose again!");
				printBoard(territories);
				System.out.println("X-Axis?");
				x = input.nextInt ( );
				System.out.println("Y-Axis?");
				y = input.nextInt ( );
			}
			
			currentArmies = territories[y][x].getArmies();
			
			moveArmies = moveArmies + currentArmies;
		
			territories[yChoice][xChoice].setArmies (removeArmies);
			territories[y][x].setArmies(moveArmies);

		}
		
		else
		{
			System.out.println("You have no Armies available to place!");
		}
		
	}

	//Author:James Gillman
	//MethodName: attackOpponent
	//Parameters:yChoice - y coordinate user chose. xChoice - x coordinate user chose. player - which player is playing? territories- array of territories, playerList - array of players.
	//Return: N/A
	//Description: Attacks Opponent. Check to see if owner of chosen territory is enemy.
	public static void attackOpponent(int yChoice, int xChoice, int player, Territory [][]territories, Player [] playerList) 
	{

		Player playerNumber; //Which player is playing?
		char attack = 'y'; //For timeToDuel method to figure out how many dice is needed. 
		char defend = 'n'; //For timeToDuel method to figure out how many dice is needed.
		int x = -1; //Chosen x Coordinate
		int y = -1; //Chosen y coordinate
		
		player--;
		int attacker = -1; //Dice roll of attacker
		int defender = -1; //Dice roll of defender
		int dyingArmies; //How many armies died?
		int remainingArmy; //How many armies remained?
		boolean adjacent = false; //Coordinates chosen is adjacent?
		
		playerNumber = playerList[player];
		printBoard(territories);
		do
		{
			System.out.println("X-Axis?");
			x = input.nextInt ( );
		
			System.out.println("Y-Axis?");
			y = input.nextInt ( );
			adjacent = isAdjacent(territories, xChoice, yChoice, x, y);
			System.out.println(adjacent);
			
		}while(adjacent == false);
		
		while(player + 1 == territories[y][x].getPlayerOwns().getPlayerNumber ( ))
		{
			System.out.println("Thats your own Territory!, choose again!");
			printBoard(territories);
			System.out.println("X-Axis?");
			x = input.nextInt ( );
			System.out.println("Y-Axis?");
			y = input.nextInt ( );
		}
		attacker = itsTimeToDuel(attack);
		defender = itsTimeToDuel(defend);
		
		System.out.println("Sum of Dice Roll for Attacker: " + attacker);
		System.out.println("Sum of Dice Roll for Defender: " + defender);
		
		if(attacker > defender)
		{
			assimilateTerritory(playerNumber, territories, y, x, yChoice, xChoice);
		}
		else
		{
			System.out.println("Defender won!");
			System.out.println ("Attacker Loses Half his army!" );
			dyingArmies = territories[yChoice][xChoice].getArmies();
			remainingArmy = dyingArmies / 2;
			territories[yChoice][xChoice].setArmies ( remainingArmy );	
		}
	}

	
	//Author: James Gillman
	//MethodName: itsTimeToDuel
	//Parameters: attackDefend. Who is attacking and who is defending.
	//Return: int diceRoll
	//Description: Rolls dice based on how many armies they have and if they are an attack or defender.
	public static int itsTimeToDuel(char attackDefend)
	{
		int dice = 0; //a dye to be rolled.
		int diceRoll = 0; // sum of all dice rolls.
		
		dice = (int) (Math.random() *12);
		diceRoll = diceRoll + dice;
		
		
		if(attackDefend == 'y')
		{
			dice = (int) (Math.random() * 6);
			diceRoll = diceRoll + dice;
		}
			
		
		return diceRoll;
	}

	//Author: James Gillman
	//MethodName: gameOverCheck
	//Parameters: territories - array of territories
	//Return: boolean gameOver
	//Description: Checks if one player occupies the whole board.
	public static boolean gameOverCheck(Territory[][]territories, int turns)
	{
		int whoOwns; //Who owns the territory?
		int player1Owns = 0; // PLayer 1's amount of territory they own.
		int player2Owns = 0; // Player 2's amount of territory they own.
		
		boolean gameOver = false;
		
		for( int y = 0; y < MAX_Y; y++ )
		{	
			for ( int x = 0; x < MAX_X; x++)
			{	
				whoOwns = territories[y][x].getPlayerOwns().getPlayerNumber();
				
				if (whoOwns == 1)
				{
					player1Owns ++;
				}
				else
				{
					player2Owns++;
				}
			}
			
		}	
		if (player1Owns == MAX )
		{
			System.out.println("Player1 Won in " + turns + " turns!");
			gameOver = true;
		}
		else if (player2Owns == MAX)
		{
			System.out.println("Player2 Won in " + turns + " turns!");
			gameOver = true;
		}
		return gameOver;
	}

	//Author: James Gillman
	//MethodName: assimilateTerritory
	//Parameters: playerNumber - which player is playing currently. territories - array of territories, y - opponents y coordinate, x - opponents coordinate, ychoice - players starting coordinate, xChoice - players starting coordinate.
	//Return: NONE
	//Description: Takes Over opponents territory, and allows player to move some of their armies there.
	public static void assimilateTerritory(Player playerNumber, Territory [][]territories, int y, int x, int yChoice, int xChoice)
	{
		int moveArmies; // How many armies are we moving.
		int removeArmies; // remove armies from OG territory.
		int currentArmies; // Current armies we have.
		
		System.out.println("Attacker won!");
		
		territories[y][x].setPlayerOwns(playerNumber);
		territories[y][x].setArmies ( 0 );
		System.out.println("How many armies do you want to move here?");
		moveArmies = input.nextInt();
		currentArmies = territories[yChoice][xChoice].getArmies ( );
		
		
		
		while(moveArmies > currentArmies + 1)
		{
			System.out.println("Choose again! You have: " + currentArmies +  " Armies.");
			moveArmies = input.nextInt ( );
		}
		removeArmies = currentArmies;
		removeArmies = removeArmies - moveArmies;
				
		currentArmies = territories[y][x].getArmies();
		
		moveArmies = moveArmies + currentArmies;
	
		territories[yChoice][xChoice].setArmies (removeArmies);
		territories[y][x].setArmies(moveArmies);

	}
	
	//Author:James Gillman
	//MethodName:credits
	//Parameters:none
	//Return:none
	//Description: Credits and special thanks. Only shown end of game/ a good changelog.
	public static void credits()
	{
		System.out.println("jRisk: Concept Version! To be added: GUI, Better Classes, Cards, Linked Lists. ");
		System.out.println("A special thanks to Isaac for his help and explaining over and over about link lists, even if I didn't use it in this version. They will be used soon enough!");
		System.out.println("Thanks for a fantastic first semester!");
		System.out.println("Goodbye~~");
	}
	
	//Author: James Gillman
	//MethodName: rules
	//Parameters: NONE
	//Return: NONE
	//Description: Shows rules for current version of jRisk. Seperate method just for ease of change.
	public static void rules()
	{
		char ready = 'n'; //Players ready to start game?
		while(ready == 'n')
		{
			System.out.println("Each territory has 1 army placed on each at the start which is randomly assigned.");
			System.out.println("Every turn each player is given their territories they own divided by 3, or a minimum of 3, whichever is higher.");
			System.out.println("Turn 1 is dedicated to adding armies to board. Afterwards you can move, attack, etc. ");
			System.out.println("Attack is in one turn. If attacker loses they lose half of their armies that were in the attack, if defender wins they lose no armies. Defenders always win ties.");
			System.out.println("Attckers have to attack a territory adjacent to you..");
			System.out.println("Using Airlift Variant on movement, player came move armies to any of their territories, 1 has to stay on orignal terrtory.");
			System.out.println("The winner is given when one player captures the whole map. Game will end.");
			System.out.println("Change these rules to if you see fit.");
			System.out.println("Ready? (y/n)");
			ready = input.next ( ).charAt ( 0 );
		}
	}
	
	//AUTHOR:James Gillman
	//MethodName: makeLogo
	//Parameters: None
	//Return: None
	//Descritption: Makes the jRisk logo.
	public static void makeLogo()
	{
		System.out.print("   _ _____  _     _    \n" + 
				"  (_)  __ \\(_)   | |   \n" + 
				"   _  |__) |_ ___| | __\n" + 
				"  | |  _  /| / __| |/ /\n" + 
				"  | | | \\ \\| \\__ \\   < \n" + 
				"  | |_|  \\_\\_|___/_|\\_\\\n" + 
				" _/ |                  \n" + 
				"|__/ Concept Version 1.2 \n");
		System.out.println("Press Enter to start the game...");
	}
	
	//Author: James Gillman
	//Method Name: isAdjacent
	//Parameters:territories, xChoice, yChoice, x, y
	//Return:boolean 
	//Description: Checks and See's if territory is adjacent
	public static boolean isAdjacent(Territory [][]territories, int xChoice, int yChoice, int x, int y)
	{
		boolean adjacent = false;
		boolean checkX = false;
		boolean checkY = false;
		
		if (xChoice == x ||xChoice + 1 == x || xChoice -1 == x || (xChoice == 5 && x == 0) || (xChoice == 0 && x ==5))
		{
			checkX = true;
		}
		
		if(yChoice == y || yChoice + 1 == y || yChoice -1 == y||(yChoice == 6 && y == 0) || (yChoice == 0 && y ==6))
		{
			checkY = true;
		}
		
		if(checkX == true && checkY == true)
		{
			adjacent = true;
		}
		
		else
		{
			System.out.println("That territory isn't next to you!");
		}
		
		return adjacent;
	}
}
