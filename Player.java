//Author: James Gillman
//Final Project
//12/11/17
//Keeps track of player, what their number is, how many armies they have and what their occupied territories are.

public class Player
{
	
	private int playerNumber; //Which player.
	private int occupiedTerritories; //What territories are they occupying?
	private int armies; //how many they have on hand to distribute
	

	public Player()
	{
		armies = -1;
		occupiedTerritories = -1;
		playerNumber = -1;
	}

	public Player(int pNum)
	{
		armies = -1;
		occupiedTerritories = -1;
		playerNumber = pNum;

	}

	
	
	//GETTERS AND SETTERS BOIII
	/**
	 * @return the playerNumber
	 */
	public int getPlayerNumber( )
	{
		return playerNumber;
	}

	/**
	 * @return the occupiedTerritories
	 */
	public int getOccupiedTerritories( )
	{
		return occupiedTerritories;
	}

	/**
	 * @return the armies
	 */
	public int getArmies( )
	{
		return armies;
	}

	/**
	 * @param playerNumber the playerNumber to set
	 */
	public void setPlayerNumber( int playerNumber )
	{
		this.playerNumber = playerNumber;
	}

	/**
	 * @param occupiedTerritories the occupiedTerritories to set
	 */
	public void setOccupiedTerritories( int occupiedTerritories )
	{
		this.occupiedTerritories = occupiedTerritories;
	}

	/**
	 * @param armies the armies to set
	 */
	public void setArmies( int armies )
	{
		this.armies = armies;
	}

}
